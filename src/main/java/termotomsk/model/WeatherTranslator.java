package termotomsk.model;

import org.springframework.stereotype.Component;
import termotomsk.model.dto.WeatherDto;
import termotomsk.model.type.ServerType;

import java.util.ArrayList;
import java.util.Queue;

@Component
public class WeatherTranslator {
    private ServerValueTranslator serverValueTranslator;

    public WeatherTranslator(ServerValueTranslator serverValueTranslator) {
        this.serverValueTranslator = serverValueTranslator;
    }

    public WeatherDto businessToData(Weather weather) {
        WeatherDto dto = new WeatherDto();
        dto.getUpdated().SetDateTime(weather.getUpdated());
        dto.setServerTermo(serverValueTranslator.businessToData(weather.getServerTermo()));
        dto.setServerIao(serverValueTranslator.businessToData(weather.getServerIao()));
        return dto;
    }

    public ArrayList<Integer> oldValuesToData(Queue<Weather> weatherList, ServerType serverType) {
        ArrayList<Integer> oldValues = new ArrayList<>();
        for (Weather itr : weatherList) {
            switch (serverType) {
                case Termo:
                    oldValues.add(itr.getServerTermo().getTemp());
                    break;
                case Iao:
                    oldValues.add(itr.getServerIao().getTemp());
                    break;
            }
        }
        return oldValues;
    }
}
