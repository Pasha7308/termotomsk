package termotomsk.manager;

import org.springframework.stereotype.Component;
import termotomsk.model.Weather;

import java.util.ArrayDeque;
import java.util.Queue;

@Component
public class WeatherContainer {
    private Weather weather = new Weather();
    private Queue<Weather> weatherList = new ArrayDeque<>();

    public Weather getWeather() {
        return weather;
    }

    void setWeather(Weather weather) {
        this.weather = weather;
    }

    void addToQueue(Weather weather) {
        weatherList.add(weather);
        if (weatherList.size() > 24) {
            weatherList.poll();
        }
    }

    public Queue<Weather> getWeatherList() {
        return weatherList;
    }
}
