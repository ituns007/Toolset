package org.ituns.toolset.loger;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LogerUtils {
    private static final int STACK_TRACE_INDEX = 4;

    public static String buildLogerFilePath(Context context) {
        try {
            StringBuilder builder = new StringBuilder();
            if(isExternalStorageWritable()) {
                builder.append(context.getExternalCacheDir().getAbsolutePath());
            } else {
                builder.append(context.getFilesDir().getAbsolutePath());
            }
            builder.append(File.separator);
            builder.append("iTuns");
            builder.append(File.separator);
            builder.append(context.getPackageName());

            //create dirs
            new File(builder.toString()).mkdirs();

            builder.append(File.separator);
            builder.append("ituns.log");
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static void clearLogerFile(String filepath) {
        try {
            new File(filepath).delete();
        } catch (Exception e) {}
    }

    public static String buildTag(String root, String child) {
        StringBuilder builder = new StringBuilder();
        if(TextUtils.isEmpty(root) || TextUtils.isEmpty(child)) {
           root = root == null ? "" : root;
           child = child == null ? "" : child;
           builder.append(root).append(child);
        } else {
            builder.append(root).append("#").append(child);
        }
        if(builder.length() > 23) {
            return builder.substring(0, 23);
        }
        return builder.toString();
    }

    public static String buildMsg(String msg, Throwable throwable, StackTraceElement[] elements) {
        StringBuilder builder = new StringBuilder();

        //build call stack infomation
        if(elements != null && elements.length > STACK_TRACE_INDEX) {
            StackTraceElement element = elements[STACK_TRACE_INDEX];
            builder.append("(");
            builder.append(element.getFileName());
            builder.append(":");
            builder.append(element.getLineNumber());
            builder.append(")").append("#");
            builder.append(element.getMethodName());
            builder.append("=>");
        }

        if(!TextUtils.isEmpty(msg)) {
            builder.append(msg);
            builder.append('\n');
        }

        if(throwable != null) {
            builder.append(throwableToString(throwable));
        }

        return builder.toString();
    }

    private static String throwableToString(Throwable throwable) {
        if (throwable == null) {
            return "";
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }
}
