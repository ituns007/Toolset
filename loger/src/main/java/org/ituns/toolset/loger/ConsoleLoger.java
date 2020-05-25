package org.ituns.toolset.loger;

import android.util.Log;

import java.io.ByteArrayInputStream;

public class ConsoleLoger extends BaseLoger {
    private static final int LOG_BUFFER_LENGTH = 4000;

    @Override
    public void log(int priority, String tag, String msg) {
        if(tag == null || msg == null) {
            return;
        }

        try {
            printLog(priority, tag, msg);
        } catch (Exception e) {}
    }

    private void printLog(int priority, String tag, String msg) throws Exception {
        int length = 0;
        byte[] bytes = new byte[LOG_BUFFER_LENGTH];
        ByteArrayInputStream bis = new ByteArrayInputStream(msg.getBytes());
        while ((length = bis.read(bytes)) != -1) {
            String splitMsg = new String(bytes, 0, length);
            Log.println(priority, tag, splitMsg);
        }
        bis.close();
    }
}
