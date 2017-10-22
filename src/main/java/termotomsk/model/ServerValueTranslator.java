package termotomsk.model;

import org.springframework.stereotype.Component;
import termotomsk.model.dto.ServerValueDto;

@Component
public class ServerValueTranslator {
    ServerValueDto businessToData(ServerValue serverValue) {
        ServerValueDto dto = new ServerValueDto();
        dto.setServerType(serverValue.getServerType());
        dto.setTemp(serverValue.getTemp());
        dto.setActual(serverValue.isActual());
        return dto;
    }
}
