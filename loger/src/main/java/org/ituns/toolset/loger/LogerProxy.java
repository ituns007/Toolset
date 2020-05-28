package org.ituns.toolset.loger;

public class LogerProxy {

    public static void v(String msg) {
        log(Priority.VERBOSE, "", msg, null);
    }

    public static void v(String tag, String msg) {
        log(Priority.VERBOSE, tag, msg, null);
    }

    public static void d(String msg) {
        log(Priority.DEBUG, "", msg, null);
    }

    public static void d(String tag, String msg) {
        log(Priority.DEBUG, tag, msg, null);
    }

    public static void i(String msg) {
        log(Priority.INFO, "", msg, null);
    }

    public static void i(String tag, String msg) {
        log(Priority.INFO, tag, msg, null);
    }

    public static void w(String msg) {
        log(Priority.WARN, "", msg, null);
    }

    public static void w(String tag, String msg) {
        log(Priority.WARN, tag, msg, null);
    }

    public static void e(String msg) {
        log(Priority.ERROR, "", msg, null);
    }

    public static void e(String tag, String msg) {
        log(Priority.ERROR, tag, msg, null);
    }

    public static void e(Throwable tr) {
        log(Priority.ERROR, "", "", tr);
    }

    public static void e(String tag, Throwable tr) {
        log(Priority.ERROR, tag, "", tr);
    }

    public static void e(String tag, String msg, Throwable tr) {
        log(Priority.ERROR, tag, msg, tr);
    }

    public static void print(Priority priority, String tag, String msg, Throwable throwable) {
        log(priority, tag, msg, throwable);
    }

    private static void log(Priority priority, String tag, String msg, Throwable throwable) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        String realTag = LogerUtils.buildTag("", tag);
        String realMsg = LogerUtils.buildMsg(msg, throwable, elements);
        LogerService.instance().log(true, priority, realTag, realMsg);
    }
}
