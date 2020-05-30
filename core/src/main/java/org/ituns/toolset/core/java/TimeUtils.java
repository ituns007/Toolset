package org.ituns.toolset.core.java;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String MILLIS_PATTERN = "yyyy-MM-dd HH:mm:ss SSS";

    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault());
    private static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(TIME_PATTERN, Locale.getDefault());
    private static SimpleDateFormat MILLIS_FORMAT = new SimpleDateFormat(MILLIS_PATTERN, Locale.getDefault());

    public static String currentDate() {
        return formatDate(new Date());
    }

    public static String currentTime() {
        return formatTime(new Date());
    }

    public static String currentTimeMillis() {
        return formatTimeMillis(new Date());
    }

    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static String formatTime(Date date) {
        return TIME_FORMAT.format(date);
    }

    public static String formatTimeMillis(Date date) {
        return MILLIS_FORMAT.format(date);
    }
}
