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
import termotomsk.model.WeatherTranslator;
import termotomsk.model.dto.WeatherDto;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherContainer weatherContainer;
    private final WeatherTranslator weatherTranslator;
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
        if (weatherContainer.getWeather() == null) {
            return new WeatherDto();
        }
        var dto = weatherTranslator.businessToData(weatherContainer.getWeather());
        dto.setOldValues(weatherTranslator.oldValuesToData(weatherContainer.getWeatherList(), ServerType.Termo));
        dto.setOldValuesIao(weatherTranslator.oldValuesToData(weatherContainer.getWeatherList(), ServerType.Iao));
//        dto.setOldValuesYandex(weatherTranslator.oldValuesToData(weatherContainer.getWeatherList(), ServerType.Yandex));
        return dto;
    }

    @RequestMapping(path = "/iao", method = RequestMethod.GET)
    public String iao() {
        log.info("/iao");
        return Optional.ofNullable(downloadIaoImage.getIao()).map(Objects::toString).orElse("null");
    }
}
