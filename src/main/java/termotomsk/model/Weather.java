package termotomsk.model;

import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class Weather {
    private OffsetDateTime updated = OffsetDateTime.now();
    private final ServerValue serverTermo = ServerValue.builder().serverType(ServerType.Termo).build();
    private final ServerValue serverIao = ServerValue.builder().serverType(ServerType.Iao).build();

    public void setUpdated(OffsetDateTime updated) {
        this.updated = updated;
        getServerTermo().setActual(false);
        getServerIao().setActual(false);
    }

    public void assign(Weather weather) {
        this.setUpdated(weather.getUpdated());
        this.getServerTermo().assign(weather.getServerTermo());
        this.getServerIao().assign(weather.getServerIao());
    }
}
