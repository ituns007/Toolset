package org.ituns.toolset.loger;

import android.util.Log;

public class LogerProxy {

    public static void v(String msg) {
        log(Log.VERBOSE, "", msg, null);
    }

    public static void v(String tag, String msg) {
        log(Log.VERBOSE, tag, msg, null);
    }

    public static void d(String msg) {
        log(Log.DEBUG, "", msg, null);
    }

    public static void d(String tag, String msg) {
        log(Log.DEBUG, tag, msg, null);
    }

    public static void i(String msg) {
        log(Log.INFO, "", msg, null);
    }

    public static void i(String tag, String msg) {
        log(Log.INFO, tag, msg, null);
    }

    public static void w(String msg) {
        log(Log.WARN, "", msg, null);
    }

    public static void w(String tag, String msg) {
        log(Log.WARN, tag, msg, null);
    }

    public static void e(String msg) {
        log(Log.ERROR, "", msg, null);
    }

    public static void e(String tag, String msg) {
        log(Log.ERROR, tag, msg, null);
    }

    public static void e(Throwable tr) {
        log(Log.ERROR, "", "", tr);
    }

    public static void e(String tag, Throwable tr) {
        log(Log.ERROR, tag, "", tr);
    }

    public static void e(String tag, String msg, Throwable tr) {
        log(Log.ERROR, tag, msg, tr);
    }

    private static void log(int priority, String tag, String msg, Throwable throwable) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        String realTag = LogerUtils.buildTag("", tag);
        String realMsg = LogerUtils.buildMsg(msg, throwable, elements);
        LogerService.instance().log(true, priority, realTag, realMsg);
    }
}
