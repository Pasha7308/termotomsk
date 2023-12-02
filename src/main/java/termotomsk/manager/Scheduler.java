package termotomsk.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import termotomsk.manager.downloader.DownloadTermo;
import termotomsk.model.Weather;

import java.time.OffsetDateTime;

import static java.time.temporal.ChronoUnit.MILLIS;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {
    final static private int PERIOD = 60 * 1000;
    private final WeatherContainer weatherContainer;

    @SuppressWarnings("BusyWait")
    @Scheduled(initialDelay = 10 * 1000, fixedRate = PERIOD)
    public void reportCurrentTime() {
        log.info("Termo start");
        var weather = weatherContainer.getWeather();

        weather.setUpdated(OffsetDateTime.now());

        new Thread(new DownloadTermo(weather)).start();
        try {
            while (!weather.getServerTermo().isActual() && !isTooLong(weather.getUpdated())) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // not important
        }
        if (weather.getServerTermo().isActual()) {
            Weather weatherToSave = new Weather();
            weatherToSave.assign(weather);
            weatherContainer.addToQueue(weatherToSave);
        }
        log.info("Termo finish. Temp: {}", weather.getServerTermo().getTemp());
    }

    private boolean isTooLong(OffsetDateTime started) {
        return started.plus(PERIOD, MILLIS).isBefore(OffsetDateTime.now());
    }
}
