package termotomsk.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import termotomsk.manager.WeatherContainer;
import termotomsk.manager.downloader.DownloadIaoImage;
import termotomsk.model.ServerType;
import termotomsk.model.WeatherOrderCreator;
import termotomsk.model.WeatherTranslator;
import termotomsk.model.WeatherTranslatorV2;
import termotomsk.model.dto.WeatherDto;
import termotomsk.model.dto.WeatherDtoV2;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherContainer weatherContainer;
    private final WeatherTranslator weatherTranslator;
    private final WeatherTranslatorV2 weatherTranslatorV2;
    private final DownloadIaoImage downloadIaoImage;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String get() {
        log.info("/");
        return "This is a server. Nothing to see";
    }

    @RequestMapping(path = "/weather", method = RequestMethod.GET)
    public WeatherDto weather(
            @RequestParam(value="forceRefresh", defaultValue="false") boolean forceRefresh) {
        log.info("/weather");
        var weather = weatherContainer.getWeather();
        if (weather == null) {
            return new WeatherDto();
        }
        var dto = weatherTranslator.businessToData(weather);
        dto.setOldValues(weatherTranslator.oldValuesToData(weatherContainer.getWeatherList(), ServerType.Termo));
        dto.setOldValuesIao(weatherTranslator.oldValuesToData(weatherContainer.getWeatherList(), ServerType.Iao));
        return dto;
    }

    @RequestMapping(path = "/weather_v2", method = RequestMethod.GET)
    public WeatherDtoV2 weatherV2(
            @RequestParam(value="forceRefresh", defaultValue="false") boolean forceRefresh) {
        log.info("/weather_v2");
        var weather = weatherContainer.getWeather();
        if (weather == null) {
            return new WeatherDtoV2();
        }
        var dto = weatherTranslatorV2.businessToData(weather);
        dto.setOldValues(weatherTranslator.oldValuesToData(weatherContainer.getWeatherList(), ServerType.Termo));
        dto.setOldValuesIao(weatherTranslator.oldValuesToData(weatherContainer.getWeatherList(), ServerType.Iao));
        dto.setOldValuesYandex(weatherTranslator.oldValuesToData(weatherContainer.getWeatherList(), ServerType.Yandex));
        dto.setPreferredOrder(WeatherOrderCreator.getOrder(weather));
        return dto;
    }

    @RequestMapping(path = "/iao", method = RequestMethod.GET)
    public String iao() {
        log.info("/iao");
        return Optional.ofNullable(downloadIaoImage.getIao()).map(Objects::toString).orElse("null");
    }
}
