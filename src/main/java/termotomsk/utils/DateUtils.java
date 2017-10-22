package termotomsk.utils;


import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final String DATE_TIME_PATTERN_SEC = "ISO_OFFSET_DATE_TIME";

    public static String DateToString(OffsetDateTime date) {
        if (date == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return date.format(formatter);
    }

    public static OffsetDateTime StringToDate(String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime date = null;
        try {
            date = OffsetDateTime.parse(string, formatter);
        } catch (Exception e) {
            //
        }
        return date;
    }
}
