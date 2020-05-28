package org.ituns.toolset.loger;

import android.content.Context;
import android.os.Process;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StorageLoger extends BaseLoger {
    private static final String FORMAT = "MM-dd HH:mm:ss.SSS";

    private final String mFilePath;
    private final DateFormat mDateFormat;

    public StorageLoger(Context context) {
        mFilePath = LogerUtils.buildLogerFilePath(context);
        mDateFormat = new SimpleDateFormat(FORMAT, Locale.getDefault());
        LogerUtils.clearLogerFile(mFilePath);
    }

    @Override
    public void log(Priority priority, String tag, String msg) {
        String content = buildLogContent(priority.value(), tag, msg);
        writeLogToFile(mFilePath, content);
    }

    private String buildLogContent(int priority, String tag, String msg) {
        StringBuilder builder = new StringBuilder();
        builder.append(mDateFormat.format(new Date()));
        builder.append(" ");
        builder.append(Process.myPid());
        builder.append("-");
        builder.append(Process.myTid());
        builder.append(" ");
        builder.append(parsePriority(priority));
        builder.append("/");
        builder.append(tag);
        builder.append(": ");
        builder.append(msg);
        return builder.toString();
    }

    private String parsePriority(int priority) {
        if(priority == Log.VERBOSE) {
            return "V";
        } else if(priority == Log.DEBUG) {
            return "D";
        } else if(priority == Log.INFO) {
            return "I";
        } else if(priority == Log.WARN) {
            return "W";
        } else if(priority == Log.ERROR) {
            return "E";
        } else if(priority == Log.ASSERT) {
            return "A";
        } else {
            return "";
        }
    }

    private void writeLogToFile(String filepath, String content) {
        FileWriter writer = null;

        try {
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new FileWriter(file, true);
            writer.write(content);
        } catch (Exception e) {
        } catch (Throwable throwable) {
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {}
        }
    }
}
