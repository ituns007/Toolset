package org.ituns.toolset.loger;

import android.util.Log;

import org.ituns.system.concurrent.BackTask;

import static org.ituns.toolset.loger.LogerService.TAG;

public class LogerClient {
    private String mTag;
    private boolean isDebug;
    private Priority mPriority;

    public LogerClient(String tag) {
        mTag = tag;
        isDebug = true;
        mPriority = Priority.VERBOSE;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public void setPriority(Priority priority) {
        mPriority = priority;
    }

    public void v(String msg) {
        log(Priority.VERBOSE, "", msg, null, 1);
    }

    public void v(String tag, String msg) {
        log(Priority.VERBOSE, tag, msg, null, 1);
    }

    public void d(String msg) {
        log(Priority.DEBUG, "", msg, null, 1);
    }

    public void d(String tag, String msg) {
        log(Priority.DEBUG, tag, msg, null, 1);
    }

    public void i(String msg) {
        log(Priority.INFO, "", msg, null, 1);
    }

    public void i(String tag, String msg) {
        log(Priority.INFO, tag, msg, null, 1);
    }

    public void w(String msg) {
        log(Priority.WARN, "", msg, null, 1);
    }

    public void w(String tag, String msg) {
        log(Priority.WARN, tag, msg, null, 1);
    }

    public void e(String msg) {
        log(Priority.ERROR, "", msg, null, 1);
    }

    public void e(String tag, String msg) {
        log(Priority.ERROR, tag, msg, null, 1);
    }

    public void e(Throwable tr) {
        log(Priority.ERROR, "", "", tr, 1);
    }

    public void e(String tag, Throwable tr) {
        log(Priority.ERROR, tag, "", tr, 1);
    }

    public void e(String tag, String msg, Throwable tr) {
        log(Priority.ERROR, tag, msg, tr, 1);
    }

    public void print(Priority priority, String tag, String msg, Throwable throwable) {
        log(priority, tag, msg, throwable, 1);
    }

    public void print(Priority priority, String tag, String msg, Throwable throwable, int stackDepth) {
        stackDepth = stackDepth < 0 ? 1 : stackDepth + 1;
        log(priority, tag, msg, throwable, stackDepth);
    }

    private void log(Priority priority, String tag, String msg, Throwable throwable, int stackDepth) {
        if(priority == Priority.NONE || priority.value() < mPriority.value()) {
            Log.i(TAG, "priority is invalid.");
            return;
        }

        final int depth = stackDepth < 0 ? 3 : stackDepth + 3;
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        if(!BackTask.post(() -> log(priority, tag, msg, throwable, depth, elements))) {
            Log.e(TAG, "async task is not executed!");
        }
    }

    private void log(Priority priority, String tag, String msg, Throwable throwable, int stackDepth, StackTraceElement[] elements) {
        String realTag = LogerUtils.buildTag(mTag, tag);
        String realMsg = LogerUtils.buildMsg(msg, throwable, stackDepth, elements);
        LogerService.instance().log(isDebug, priority, realTag, realMsg);
    }

    public void release() {
        mTag = null;
        isDebug = false;
        mPriority = Priority.NONE;
    }
}
