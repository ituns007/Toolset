package org.ituns.toolset.loger;

import android.util.Log;

public enum Priority {
    VERBOSE(Log.VERBOSE),
    DEBUG(Log.DEBUG),
    INFO(Log.INFO),
    WARN(Log.WARN),
    ERROR(Log.ERROR),
    ASSERT(Log.ASSERT),
    NONE(Integer.MAX_VALUE);

    private int value;

    private Priority(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
