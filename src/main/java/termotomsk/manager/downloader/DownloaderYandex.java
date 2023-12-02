package termotomsk.manager.downloader;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import termotomsk.api.yandex.api.YandexWeatherApi;
import termotomsk.api.yandex.dto.YandexWeather;

@Component
@RequiredArgsConstructor
public class DownloaderYandex  {
    private final YandexWeatherApi yandexWeatherApi;

    public YandexWeather getYandexWeather() {
        return yandexWeatherApi.getWeather(56.4822624, 84.9818893);
    }
}
