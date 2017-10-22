package termotomsk.manager;

import com.google.appengine.api.ThreadManager;
import org.springframework.stereotype.Component;
import termotomsk.manager.Downloader.DownloadIao;
import termotomsk.manager.Downloader.DownloadTermo;
import termotomsk.model.Weather;

import java.time.OffsetDateTime;

import static java.time.temporal.ChronoUnit.MILLIS;

@Component
public class Scheduler {
    final static private int period = 5*60*1000;
    private WeatherContainer weatherContainer;

    public Scheduler(WeatherContainer weatherContainer) {
        this.weatherContainer = weatherContainer;
    }

    public int reportCurrentTime() {
        Weather weather = weatherContainer.getWeather();

        weather.setUpdated(OffsetDateTime.now());

        ThreadManager.createThreadForCurrentRequest(new DownloadTermo(weather)).start();
        ThreadManager.createThreadForCurrentRequest(new DownloadIao(weather)).start();
        try {
            while (!weather.getServerTermo().isActual() && !weather.getServerIao().isActual() && !isTooLong(weather.getUpdated())) {
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            // not important
        }
        if (weather.getServerTermo().isActual() || weather.getServerIao().isActual()) {
            Weather weatherToSave = new Weather();
            weatherToSave.assign(weather);
            weatherContainer.addToQueue(weatherToSave);
        }
        int ret = 300;
        if (weather.getServerTermo().isActual() && weather.getServerIao().isActual()) {
            ret = 200;
        } else if (weather.getServerTermo().isActual() || weather.getServerIao().isActual()) {
            ret = 201;
        }
        return ret;
    }

    private boolean isTooLong(OffsetDateTime started) {
        return started.plus(period, MILLIS).isBefore(OffsetDateTime.now());
    }
}
