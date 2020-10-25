package termotomsk.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter @Setter
public class DateTimeDto {
    private OffsetDateTime localDateTime = OffsetDateTime.MIN;
}
