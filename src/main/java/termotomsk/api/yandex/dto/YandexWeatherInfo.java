package termotomsk.api.yandex.dto;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter @Setter
public class YandexWeatherInfo {
    @NotNull
    private Double lat; //55.833333;

    @NotNull
    private Double lon; //37.616667;
}
