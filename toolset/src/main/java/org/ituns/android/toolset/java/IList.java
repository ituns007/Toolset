package org.ituns.android.toolset.java;

import java.util.List;

public class IList {

    public static boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    public static int length(List<?> list) {
        return list == null ? 0 : list.size();
    }
}
