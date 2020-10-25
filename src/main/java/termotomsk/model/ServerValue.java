package termotomsk.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter @Setter @Builder
public class ServerValue {
    private ServerType serverType;
    @Builder.Default
    private OffsetDateTime updated = OffsetDateTime.now();
    @Builder.Default
    private int temp = 0;
    @Builder.Default
    private boolean actual = false;

    public void assign(ServerValue serverValue) {
        this.setUpdated(serverValue.getUpdated());
        this.setTemp(serverValue.getTemp());
        this.setActual(serverValue.isActual());
    }
}
