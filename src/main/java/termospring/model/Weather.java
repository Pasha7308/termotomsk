package termospring.model;

import termospring.model.type.JsonDateTime;
import termospring.model.type.ServerType;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class Weather {
    private OffsetDateTime updated = OffsetDateTime.now();
    private ServerValue serverTermo = new ServerValue(ServerType.Termo);
    private ServerValue serverIao = new ServerValue(ServerType.Iao);

    public OffsetDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(OffsetDateTime updated) {
        this.updated = updated;
        getServerTermo().setActual(false);
        getServerIao().setActual(false);
    }

    public ServerValue getServerTermo() {
        return serverTermo;
    }

    public ServerValue getServerIao() {
        return serverIao;
    }

    public void assign(Weather weather) {
        this.setUpdated(weather.getUpdated());
        this.getServerTermo().assign(weather.getServerTermo());
        this.getServerIao().assign(weather.getServerIao());
    }
}
