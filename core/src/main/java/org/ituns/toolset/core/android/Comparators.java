package org.ituns.toolset.core.android;

import android.content.Context;

public class Comparators {

    public static boolean equals(Context c1, Context c2) {
        if(c1 == null || c2 == null) {
            return false;
        } else {
            return c1.getClass().getName().equals(c2.getClass().getName());
        }
    }
}
