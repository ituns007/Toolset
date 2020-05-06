package org.ituns.toolset.core;

public class TString {

    public static String emptyIfNull(String str) {
        return str == null ? "" : str;
    }
}
