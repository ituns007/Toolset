package org.ituns.toolset.loger;

import org.ituns.system.concurrent.BackTask;

public class LogerClient {
    private String mTag;
    private boolean isDebug;
    private Priority mPriority;
    private final BackTask mBackTask;

    public LogerClient(String tag) {
        mTag = tag;
        isDebug = true;
        mPriority = Priority.VERBOSE;
        mBackTask = new BackTask();
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public void setPriority(Priority priority) {
        mPriority = priority;
    }

    public void v(String msg) {
        log(Priority.VERBOSE, "", msg, null);
    }

    public void v(String tag, String msg) {
        log(Priority.VERBOSE, tag, msg, null);
    }

    public void d(String msg) {
        log(Priority.DEBUG, "", msg, null);
    }

    public void d(String tag, String msg) {
        log(Priority.DEBUG, tag, msg, null);
    }

    public void i(String msg) {
        log(Priority.INFO, "", msg, null);
    }

    public void i(String tag, String msg) {
        log(Priority.INFO, tag, msg, null);
    }

    public void w(String msg) {
        log(Priority.WARN, "", msg, null);
    }

    public void w(String tag, String msg) {
        log(Priority.WARN, tag, msg, null);
    }

    public void e(String msg) {
        log(Priority.ERROR, "", msg, null);
    }

    public void e(String tag, String msg) {
        log(Priority.ERROR, tag, msg, null);
    }

    public void e(Throwable tr) {
        log(Priority.ERROR, "", "", tr);
    }

    public void e(String tag, Throwable tr) {
        log(Priority.ERROR, tag, "", tr);
    }

    public void e(String tag, String msg, Throwable tr) {
        log(Priority.ERROR, tag, msg, tr);
    }

    public void print(Priority priority, String tag, String msg, Throwable throwable) {
        log(priority, tag, msg, throwable);
    }

    private void log(Priority priority, String tag, String msg, Throwable throwable) {
        if(priority == Priority.NONE || priority.value() < mPriority.value()) {
            return;
        }

        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        mBackTask.post(() -> log(priority, tag, msg, throwable, elements));
    }

    private void log(Priority priority, String tag, String msg, Throwable throwable, StackTraceElement[] elements) {
        String realTag = LogerUtils.buildTag(mTag, tag);
        String realMsg = LogerUtils.buildMsg(msg, throwable, elements);
        LogerService.instance().log(isDebug, priority, realTag, realMsg);
    }

    public void release() {
        mBackTask.release();
    }
}
