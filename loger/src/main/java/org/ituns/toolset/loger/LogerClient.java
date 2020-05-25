package org.ituns.toolset.loger;

import android.util.Log;

import org.ituns.system.concurrent.BackTask;

public class LogerClient {
    private String mTag;
    private int mPriority;
    private boolean isDebug;
    private final BackTask mBackTask;

    public LogerClient(String tag) {
        mTag = tag;
        isDebug = false;
        mPriority = Log.VERBOSE;
        mBackTask = new BackTask();
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public void setPriority(int priority) {
        mPriority = priority;
    }

    public void v(String msg) {
        log(Log.VERBOSE, "", msg, null);
    }

    public void v(String tag, String msg) {
        log(Log.VERBOSE, tag, msg, null);
    }

    public void d(String msg) {
        log(Log.DEBUG, "", msg, null);
    }

    public void d(String tag, String msg) {
        log(Log.DEBUG, tag, msg, null);
    }

    public void i(String msg) {
        log(Log.INFO, "", msg, null);
    }

    public void i(String tag, String msg) {
        log(Log.INFO, tag, msg, null);
    }

    public void w(String msg) {
        log(Log.WARN, "", msg, null);
    }

    public void w(String tag, String msg) {
        log(Log.WARN, tag, msg, null);
    }

    public void e(String msg) {
        log(Log.ERROR, "", msg, null);
    }

    public void e(String tag, String msg) {
        log(Log.ERROR, tag, msg, null);
    }

    public void e(Throwable tr) {
        log(Log.ERROR, "", "", tr);
    }

    public void e(String tag, Throwable tr) {
        log(Log.ERROR, tag, "", tr);
    }

    public void e(String tag, String msg, Throwable tr) {
        log(Log.ERROR, tag, msg, tr);
    }

    private void log(int priority, String tag, String msg, Throwable throwable) {
        if(priority < mPriority) {
            return;
        }

        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        mBackTask.post(() -> log(priority, tag, msg, throwable, elements));
    }

    private void log(int priority, String tag, String msg, Throwable throwable, StackTraceElement[] elements) {
        String realTag = LogerUtils.buildTag(mTag, tag);
        String realMsg = LogerUtils.buildMsg(msg, throwable, elements);
        LogerService.instance().log(isDebug, priority, realTag, realMsg);
    }

    public void release() {
        mBackTask.release();
    }
}
