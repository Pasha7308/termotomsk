package termotomsk.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import termotomsk.api.yandex.api.YandexWeatherApi;
import termotomsk.model.Weather;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class SchedulerYanadex {
    final static private int period = 30 * 60 * 1000;
    private final WeatherContainer weatherContainer;
    private final YandexWeatherApi yandexWeatherApi;

    @Scheduled(initialDelay = 11 * 1000, fixedRate = period)
    public void reportCurrentTime() {
        var weather = weatherContainer.getWeather();

        weather.setUpdated(OffsetDateTime.now());

        var yandexWeather = yandexWeatherApi.getWeather(56.4822624, 84.9818893);
        weather.getServerIao().setTemp(Double.valueOf(yandexWeather.getFact().getTemp() * 10.0).intValue());
        weather.getServerIao().setUpdated(OffsetDateTime.now());
        if (weather.getServerTermo().isActual() || weather.getServerIao().isActual()) {
            Weather weatherToSave = new Weather();
            weatherToSave.assign(weather);
            weatherContainer.addToQueue(weatherToSave);
        }
    }
}
