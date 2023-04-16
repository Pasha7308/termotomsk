package termotomsk.api.yandex.dto;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter @Setter
public class YandexWeatherFact {
    @NotNull
    private Double temp; //20
}
