package termotomsk.manager.translator;

import org.springframework.stereotype.Component;
import termotomsk.model.ServerValue;
import termotomsk.model.dto.ServerValueDto;

@Component
public class ServerValueTranslator {
    public ServerValueDto businessToData(ServerValue serverValue) {
        ServerValueDto dto = new ServerValueDto();
        dto.setServerType(serverValue.getServerType());
        dto.setTemp(serverValue.getTemp());
        dto.setActual(serverValue.isActual());
        return dto;
    }
}
