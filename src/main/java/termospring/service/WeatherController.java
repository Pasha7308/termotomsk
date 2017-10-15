package termospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import termospring.manager.WeatherContainer;
import termospring.model.Weather;
import termospring.model.type.JsonDateTime;

@RestController
public class WeatherController {
    @Autowired private WeatherContainer weatherContainer;

    @RequestMapping(path = "/weather", method = RequestMethod.GET)
    public Weather greeting(
            @RequestParam(value="forceRefresh", defaultValue="false") boolean forceRefresh,
            @RequestParam(value="lastUpdate", defaultValue="") JsonDateTime lastUpdate) {
        return weatherContainer.getWeather();
    }
}
