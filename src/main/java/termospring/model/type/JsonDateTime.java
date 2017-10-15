package termospring.model.type;

import termospring.utils.DateUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

public class JsonDateTime {
    private LocalDateTime localDateTime = null;

    public String getLocalDateTime() {
        return toString();
    }

    public void setLocalDateTime(String string) {
        localDateTime = DateUtils.StringToDate(string);
    }

    public LocalDateTime GetLocalDateTime() {
        return localDateTime;
    }

    public void SetLocalDateTime(LocalDateTime localDateTimeIn) {
        localDateTime = localDateTimeIn;
    }

    public LocalDateTime GetDateTime() {
        return localDateTime;
    }

    public void SetDateTime(LocalDateTime dateTime) {
        localDateTime = dateTime;
    }

    public JsonDateTime(String string) {
        localDateTime = DateUtils.StringToDate(string);
    }

    public JsonDateTime() {
        localDateTime = null;
    }

    public boolean IsEmpty() {
        return localDateTime == null;
    }

    @Override
    public String toString() {
        return DateUtils.DateToString(localDateTime);
    }
}
