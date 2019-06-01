package termotomsk.model;

import termotomsk.model.type.ServerType;

import java.time.OffsetDateTime;

public class ServerValue {
    private ServerType serverType;
    private OffsetDateTime updated = OffsetDateTime.now();
    private int temp = 0;
    private boolean actual = false;

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

    int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
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
