package org.ituns.android.toolset.java;

public class IString {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isEmpty(String... strs) {
        if(strs == null) {
            return true;
        }

        for(String str : strs) {
            if(!isEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean notEmpty(String... strs) {
        if(strs == null) {
            return false;
        }

        for(String str : strs) {
            if(isEmpty(str)) {
                return false;
            }
        }
        return true;
    }


    public static String emptyIfNull(String str) {
        return str == null ? "" : str;
    }

    public static CharSequence emptyIfNull(CharSequence str) {
        return str == null ? "" : str;
    }

    public static int length(String s) {
        return s == null ? 0 : s.length();
    }
}
