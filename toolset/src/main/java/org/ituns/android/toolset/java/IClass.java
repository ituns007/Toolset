package org.ituns.android.toolset.java;

public class IClass {

    public static String className(Class<?> clazz) {
        return clazz != null ? clazz.getName() : null;
    }
}
