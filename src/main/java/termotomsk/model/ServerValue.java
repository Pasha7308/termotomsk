package termotomsk.model;

import termotomsk.model.type.ServerType;

import java.time.OffsetDateTime;

public class ServerValue {
    private ServerType serverType;
    private OffsetDateTime updated = OffsetDateTime.now();
    private Integer temp;
    private boolean actual;

    ServerValue(ServerType serverType) {
        this.serverType = serverType;
    }

    ServerType getServerType() {
        return serverType;
    }

    private OffsetDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(OffsetDateTime updated) {
        this.updated = updated;
    }

    Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public void assign(ServerValue serverValue) {
        this.setUpdated(serverValue.getUpdated());
        this.setTemp(serverValue.getTemp());
        this.setActual(serverValue.isActual());
    }
}
