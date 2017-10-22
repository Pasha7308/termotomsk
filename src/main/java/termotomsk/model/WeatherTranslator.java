package termotomsk.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import termotomsk.model.dto.WeatherDto;

import java.util.ArrayList;
import java.util.Queue;

@Component
public class WeatherTranslator {
    @Autowired ServerValueTranslator serverValueTranslator;

    public WeatherDto businessToData(Weather weather) {
        WeatherDto dto = new WeatherDto();
        dto.getUpdated().SetDateTime(weather.getUpdated());
        dto.setServerTermo(serverValueTranslator.businessToData(weather.getServerTermo()));
        dto.setServerIao(serverValueTranslator.businessToData(weather.getServerIao()));
        return dto;
    }

    public ArrayList<Integer> oldValuesToData(Queue<Weather> weatherList) {
        ArrayList<Integer> oldValues = new ArrayList<>();
        for (Weather itr : weatherList) {
            oldValues.add(itr.getServerTermo().getTemp());
        }
        return oldValues;
    }
}
