package org.ituns.android.toolset.security;

import java.security.MessageDigest;

public class Md5 {
    private static final String CHARSET = "UTF-8";

    public static String encrypt(String str) throws Exception {
        return encrypt(str, CHARSET);
    }

    public static String encrypt(String str, String charset) throws Exception {
        return encrypt(str.getBytes(charset));
    }

    public static String encrypt(byte[] bytes) throws Exception {
        byte[] hash = MessageDigest.getInstance("md5").digest(bytes);
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}