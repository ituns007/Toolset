package org.ituns.toolset.core.java;

public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static String emptyIfNull(String str) {
        return str == null ? "" : str;
    }

    public static CharSequence emptyIfNull(CharSequence str) {
        return str == null ? "" : str;
    }
}
