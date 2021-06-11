package org.ituns.android.toolset.java;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class IStream {

    public static byte[] read(InputStream is) {
        try {
            int len = 0;
            byte[] bytes = new byte[8192];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = is.read(bytes)) != -1) {
                baos.write(bytes, 0,  len);
            }
            is.close();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean write(OutputStream os, byte[] bytes) {
        try {
            os.write(bytes);
            os.flush();
            os.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
