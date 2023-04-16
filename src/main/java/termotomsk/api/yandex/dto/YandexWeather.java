package termotomsk.api.yandex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Getter @Setter
public class YandexWeather {
    @NotNull
    @JsonProperty("now_dt")
    private LocalDateTime nowDt;

    @NotNull
    @JsonProperty("info")
    private YandexWeatherInfo info;

    @NotNull
    @JsonProperty("fact")
    private YandexWeatherFact fact;
}
