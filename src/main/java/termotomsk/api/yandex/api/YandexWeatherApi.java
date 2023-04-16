package termotomsk.api.yandex.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import termotomsk.api.yandex.dto.YandexWeather;

@FeignClient(value = "alb-ffg-server", url = "https://api.weather.yandex.ru/v2", configuration = YandexServerApiConfig.class)
public interface YandexWeatherApi {
    @GetMapping("informers")
    YandexWeather getWeather(@RequestParam("lat") Double lat, @RequestParam("lon") Double lon);
}
