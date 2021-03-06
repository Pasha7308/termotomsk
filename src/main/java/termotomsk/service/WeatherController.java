package termotomsk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import termotomsk.manager.WeatherContainer;
import termotomsk.model.ServerType;
import termotomsk.model.WeatherTranslator;
import termotomsk.model.dto.WeatherDto;

@RestController
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherContainer weatherContainer;
    private final WeatherTranslator weatherTranslator;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getString() {
        return "This is a server. Nothing to see";
    }

    @RequestMapping(path = "/weather", method = RequestMethod.GET)
    public WeatherDto weather(
            @RequestParam(value="forceRefresh", defaultValue="false") boolean forceRefresh) {
        if (weatherContainer.getWeather() == null) {
            return new WeatherDto();
        }
        var dto = weatherTranslator.businessToData(weatherContainer.getWeather());
        dto.setOldValues(weatherTranslator.oldValuesToData(weatherContainer.getWeatherList(), ServerType.Termo));
        dto.setOldValuesIao(weatherTranslator.oldValuesToData(weatherContainer.getWeatherList(), ServerType.Iao));
        return dto;
    }
}
