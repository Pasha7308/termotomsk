package termotomsk.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import termotomsk.api.yandex.dto.YandexWeather;
import termotomsk.api.yandex.dto.YandexWeatherFact;
import termotomsk.api.yandex.dto.YandexWeatherInfo;
import termotomsk.manager.downloader.DownloaderYandex;
import termotomsk.model.Weather;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulerYanadex {
    final static private int PERIOD = 30 * 60 * 1000;
    private final WeatherContainer weatherContainer;
    private final DownloaderYandex downloaderYandex;

    @Scheduled(initialDelay = 11 * 1000, fixedRate = PERIOD)
    public void reportCurrentTime() {
        log.info("Yandex start");
        var weather = weatherContainer.getWeather();

        weather.setUpdated(OffsetDateTime.now());

//        var yandexWeather = getFakeYandex();
        var yandexWeather = downloaderYandex.getYandexWeather();
        weather.getServerYandex().setTemp(Double.valueOf(yandexWeather.getFact().getTemp() * 10.0).intValue());
        weather.getServerYandex().setUpdated(OffsetDateTime.now());
        if (weather.getServerTermo().isActual() || weather.getServerYandex().isActual()) {
            Weather weatherToSave = new Weather();
            weatherToSave.assign(weather);
            weatherContainer.addToQueue(weatherToSave);
        }
        log.info("Yandex finish. Temp: {}", weather.getServerYandex().getTemp());
    }

    private YandexWeather getFakeYandex() {
        var yandexWeather = new YandexWeather();
        yandexWeather.setNowDt(LocalDateTime.now());
        yandexWeather.setInfo(new YandexWeatherInfo());
        yandexWeather.setFact(new YandexWeatherFact());
        yandexWeather.getFact().setTemp(nextDoubleBetween(-30.0, 30.0));
        return yandexWeather;
    }

    public static double nextDoubleBetween(double min, double max) {
        return (ThreadLocalRandom.current().nextDouble() * (max - min)) + min;
    }
}
