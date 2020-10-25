package termotomsk.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import termotomsk.manager.downloader.DownloadIao;
import termotomsk.manager.downloader.DownloadTermo;
import termotomsk.model.Weather;

import java.time.OffsetDateTime;

import static java.time.temporal.ChronoUnit.MILLIS;

@Component
@RequiredArgsConstructor
public class Scheduler {
    final static private int period = 60 * 1000;
    private final WeatherContainer weatherContainer;

    @SuppressWarnings("BusyWait")
    @Scheduled(initialDelay = 10 * 1000, fixedRate = period)
    public void reportCurrentTime() {
        var weather = weatherContainer.getWeather();

        weather.setUpdated(OffsetDateTime.now());

        new Thread(new DownloadTermo(weather)).start();
        new Thread(new DownloadIao(weather)).start();
        try {
            while (!weather.getServerTermo().isActual() && !weather.getServerIao().isActual() && !isTooLong(weather.getUpdated())) {
                Thread.sleep(2000);
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
