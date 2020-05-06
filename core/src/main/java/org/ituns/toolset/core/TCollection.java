package org.ituns.toolset.core;

import java.util.Collection;

public class TCollection {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
