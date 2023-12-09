package termotomsk.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import termotomsk.manager.translator.ServerValueTranslator;
import termotomsk.model.dto.WeatherDtoV2;

@Component
@RequiredArgsConstructor
public class WeatherTranslatorV2 {
    private final WeatherTranslator weatherTranslator;
    private final ServerValueTranslator serverValueTranslator;

    public WeatherDtoV2 businessToData(Weather weather) {
        var dto = new WeatherDtoV2();
        weatherTranslator.businessToData(weather, dto);
        dto.setServerYandex(serverValueTranslator.businessToData(weather.getServerYandex()));
        return dto;
    }
}
