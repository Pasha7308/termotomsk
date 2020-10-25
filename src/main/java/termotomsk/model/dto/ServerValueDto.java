package termotomsk.model.dto;

import lombok.Getter;
import lombok.Setter;
import termotomsk.model.ServerType;

@Getter @Setter
public class ServerValueDto {
    private ServerType serverType;
    private boolean actual;
    private Integer temp;
}
