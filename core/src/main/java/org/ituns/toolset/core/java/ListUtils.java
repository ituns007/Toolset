package org.ituns.toolset.core.java;

import java.util.Collection;

public class ListUtils {

    public static boolean isEmpty(Collection<?> list) {
        return list == null || list.isEmpty();
    }
}
