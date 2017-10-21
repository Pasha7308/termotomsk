package termospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import termospring.manager.WeatherContainer;
import termospring.model.Weather;
import termospring.model.WeatherTranslator;
import termospring.model.dto.WeatherDto;
import termospring.model.type.JsonDateTime;

@RestController
public class WeatherController {
    @Autowired private WeatherContainer weatherContainer;
    @Autowired private WeatherTranslator weatherTranslator;

    @RequestMapping(path = "/weather", method = RequestMethod.GET)
    public WeatherDto greeting(
            @RequestParam(value="forceRefresh", defaultValue="false") boolean forceRefresh,
            @RequestParam(value="lastUpdate", defaultValue="") JsonDateTime lastUpdate) {
        WeatherDto dto = new WeatherDto();
        if (weatherContainer.getWeather() != null) {
            dto = weatherTranslator.businessToData(weatherContainer.getWeather());
            dto.setOldValues(weatherTranslator.oldValuesToData(weatherContainer.getWeatherList()));
        }
        return dto;
    }
}
