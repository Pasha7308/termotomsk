package termospring.utils;


import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    private static final String DATE_TIME_PATTERN_SEC = "dd-MM-yyyy-HH-mm-ss";

    public static String DateToString(LocalDateTime date) {
        if (date == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_SEC, Locale.ENGLISH);
        return date.format(formatter);
    }

    public static LocalDateTime StringToDate(String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_SEC, Locale.ENGLISH);
        LocalDateTime date = null;
        try {
            date = LocalDateTime.parse(string, formatter);
        } catch (Exception e) {
            //
        }
        return date;
    }
}
