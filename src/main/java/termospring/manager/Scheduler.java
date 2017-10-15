package termospring.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Scheduler {
    @Autowired private WeatherContainer weatherContainer;

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        weatherContainer.getWeather().getUpdated().SetDateTime(LocalDateTime.now());
    }
}
