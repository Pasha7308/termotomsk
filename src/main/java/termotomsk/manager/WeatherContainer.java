package termotomsk.manager;

import org.springframework.stereotype.Component;
import termotomsk.model.Weather;

import java.util.ArrayDeque;
import java.util.Queue;

@Component
public class WeatherContainer {
    private final int maxQueue = 24;

    private final Weather weather = new Weather();
    private final Queue<Weather> weatherList = new ArrayDeque<>();

    public WeatherContainer() {
        for (int i = 0; i < maxQueue; i++) {
            addToQueue(new Weather());
        }
    }

    public Weather getWeather() {
        return weather;
    }

    public Queue<Weather> getWeatherList() {
        return weatherList;
    }

    void addToQueue(Weather weather) {
        weatherList.add(weather);
        if (weatherList.size() > maxQueue) {
            weatherList.poll();
        }
    }
}
