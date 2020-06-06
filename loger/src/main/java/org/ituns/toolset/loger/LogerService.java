package org.ituns.toolset.loger;

import android.content.Context;

import org.ituns.system.provider.iTunsProvider;

public class LogerService {
    public static final String TAG = "Loger";

    private static LogerService mInstance;

    private final ConsoleLoger mConsoleLoger;
    private final StorageLoger mStorageLoger;

    public synchronized static LogerService instance() {
        if(mInstance == null) {
            mInstance = new LogerService(iTunsProvider.applicationContext);
        }
        return mInstance;
    }

    private LogerService(Context context) {
        mConsoleLoger = new ConsoleLoger();
        mStorageLoger = new StorageLoger(context);
    }

    public void log(boolean debug, Priority priority, String tag, String msg) {
        if(debug) {
            mConsoleLoger.log(priority, tag, msg);
        } else {
            mStorageLoger.log(priority, tag, msg);
        }
    }
}