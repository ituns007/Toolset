package org.ituns.toolset.core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TTime {
    private static SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat FORMAT_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat FORMAT_TIME_MILLIS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    public static String formatDate() {
        return formatDate(new Date());
    }

    public static String formatDate(Date date) {
        return FORMAT_DATE.format(date);
    }

    public static String formatTime() {
        return formatTime(new Date());
    }

    public static String formatTime(Date date) {
        return FORMAT_TIME.format(date);
    }

    public static String formatTimeMillis() {
        return formatTimeMillis(new Date());
    }

    public static String formatTimeMillis(Date date) {
        return FORMAT_TIME_MILLIS.format(date);
    }
}
