package termotomsk.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import termotomsk.manager.translator.ServerValueTranslator;
import termotomsk.model.dto.WeatherDto;

import java.util.ArrayList;
import java.util.Queue;

@Component
@RequiredArgsConstructor
public class WeatherTranslator {
    private final ServerValueTranslator serverValueTranslator;

    public WeatherDto businessToData(Weather weather) {
        var dto = new WeatherDto();
        businessToData(weather, dto);
        return dto;
    }

    public void businessToData(Weather weather, WeatherDto dto) {
        dto.getUpdated().setLocalDateTime(weather.getUpdated());
        dto.setServerTermo(serverValueTranslator.businessToData(weather.getServerTermo()));
        dto.setServerIao(serverValueTranslator.businessToData(weather.getServerIao()));
    }

    public ArrayList<Integer> oldValuesToData(Queue<Weather> weatherList, ServerType serverType) {
        ArrayList<Integer> oldValues = new ArrayList<>();
        for (Weather itr : weatherList) {
            switch (serverType) {
                case Termo -> oldValues.add(itr.getServerTermo().getTemp());
                case Iao -> oldValues.add(itr.getServerIao().getTemp());
                case Yandex -> oldValues.add(itr.getServerYandex().getTemp());
            }
        }
        return oldValues;
    }
}
