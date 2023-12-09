package termotomsk.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class WeatherDtoV2 extends WeatherDto {
    private ServerValueDto serverYandex = new ServerValueDto();
    private ArrayList<Integer> oldValuesYandex;
}
