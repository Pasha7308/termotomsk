package termotomsk.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import termotomsk.manager.Scheduler;
import termotomsk.manager.WeatherContainer;
import termotomsk.model.WeatherTranslator;
import termotomsk.model.dto.WeatherDto;
import termotomsk.model.type.JsonDateTime;

@RestController
public class WeatherController {
    private WeatherContainer weatherContainer;
    private WeatherTranslator weatherTranslator;
    private Scheduler scheduler;

    public WeatherController(WeatherContainer weatherContainer, WeatherTranslator weatherTranslator, Scheduler scheduler) {
        this.weatherContainer = weatherContainer;
        this.weatherTranslator = weatherTranslator;
        this.scheduler = scheduler;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getString() {
        return "This is a server. Nothing to see";
    }

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

    @RequestMapping(path = "/schedule", method = RequestMethod.GET)
    public Integer cron() {
        return scheduler.reportCurrentTime();
    }
}
