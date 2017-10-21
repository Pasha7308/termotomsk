package termospring.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import termospring.manager.Downloader.Download;
import termospring.manager.Downloader.DownloadIao;
import termospring.manager.Downloader.DownloadTermo;
import termospring.model.Weather;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MILLIS;

@Component
public class Scheduler {
    final static private int period = 5*60*1000;
    @Autowired private WeatherContainer weatherContainer;

    @Scheduled(initialDelay=10*1000, fixedRate = period)
    public void reportCurrentTime() {
        Weather weather = weatherContainer.getWeather();

        weather.setUpdated(OffsetDateTime.now());

        new Thread(new DownloadTermo(weather)).start();
        new Thread(new DownloadIao(weather)).start();
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
    }

    private boolean isTooLong(OffsetDateTime started) {
        return started.plus(period, MILLIS).isBefore(OffsetDateTime.now());
    }
}
