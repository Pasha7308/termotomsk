package termotomsk.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import termotomsk.manager.downloader.DownloadIaoImage;
import termotomsk.model.Weather;

import java.time.OffsetDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulerIao {
    final static private int PERIOD = 60 * 1000;
    private final WeatherContainer weatherContainer;
    private final DownloadIaoImage downloadIaoImage;

    @Scheduled(initialDelay = 12 * 1000, fixedRate = PERIOD)
    public void reportCurrentTime() {
        log.info("Iao start");
        var weather = weatherContainer.getWeather();

        weather.setUpdated(OffsetDateTime.now());

        var tempIao = downloadIaoImage.getIao();
        weather.getServerIao().setTemp((int)Math.round(tempIao * 10.0));
        weather.getServerIao().setUpdated(OffsetDateTime.now());
        weather.getServerIao().setActual(tempIao != 0.0);
        if (weather.getServerTermo().isActual() || weather.getServerIao().isActual()) {
            Weather weatherToSave = new Weather();
            weatherToSave.assign(weather);
            weatherContainer.addToQueue(weatherToSave);
        }
        log.info("Iao finish. Temp: {}", weather.getServerIao().getTemp());
    }
}
