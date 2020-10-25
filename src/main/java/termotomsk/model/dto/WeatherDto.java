package termotomsk.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class WeatherDto {
    private DateTimeDto updated = new DateTimeDto();
    private ServerValueDto serverTermo = new ServerValueDto();
    private ServerValueDto serverIao = new ServerValueDto();
    private ArrayList<Integer> oldValues;
    private ArrayList<Integer> oldValuesIao;
}
