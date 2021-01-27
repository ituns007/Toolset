package org.ituns.android.toolset.java.io;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class IStream {

    public static String readText(InputStream is) {
        byte[] bytes = readBytes(is);
        if(bytes != null) {
            return new String(bytes, StandardCharsets.UTF_8);
        }
        return null;
    }

    public static byte[] readBytes(InputStream is) {
        try {
            int length;
            byte[] buffer = new byte[8192];
            ByteArrayOutputStream baos = new ByteArrayOutputStream(2048);
            while ((length = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, length);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }
}
