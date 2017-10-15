package termospring.model;

import termospring.model.type.JsonDateTime;

public class Weather {
    private JsonDateTime updated = new JsonDateTime();
    private Integer tempTermo;
    private Integer trmpIao;

    public JsonDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(JsonDateTime updated) {
        this.updated = updated;
    }

    public Integer getTempTermo() {
        return tempTermo;
    }

    public void setTempTermo(Integer tempTermo) {
        this.tempTermo = tempTermo;
    }

    public Integer getTrmpIao() {
        return trmpIao;
    }

    public void setTrmpIao(Integer trmpIao) {
        this.trmpIao = trmpIao;
    }
}
