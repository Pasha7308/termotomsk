package termospring.manager;

import org.springframework.stereotype.Component;
import termospring.model.Weather;

@Component
public class WeatherContainer {
    private Weather weather = new Weather();

    public Weather getWeather() {
        return weather;
    }
}
