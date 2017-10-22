package termotomsk.model.type;

import termotomsk.utils.DateUtils;

import java.time.OffsetDateTime;

public class JsonDateTime {
    private OffsetDateTime localDateTime = null;

    public String getLocalDateTime() {
        return toString();
    }

    public void setLocalDateTime(String string) {
        localDateTime = DateUtils.StringToDate(string);
    }

    public OffsetDateTime GetLocalDateTime() {
        return localDateTime;
    }

    public void SetLocalDateTime(OffsetDateTime localDateTimeIn) {
        localDateTime = localDateTimeIn;
    }

    public OffsetDateTime GetDateTime() {
        return localDateTime;
    }

    public void SetDateTime(OffsetDateTime dateTime) {
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
