package termospring.model.dto;

import termospring.model.type.JsonDateTime;
import termospring.model.type.ServerType;

public class ServerValueDto {
    private ServerType serverType;
    private boolean isActual;
    private Integer temp;

    public ServerType getServerType() {
        return serverType;
    }

    public void setServerType(ServerType serverType) {
        this.serverType = serverType;
    }

    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean actual) {
        isActual = actual;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }
}
