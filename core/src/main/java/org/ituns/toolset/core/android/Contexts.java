package org.ituns.toolset.core.android;

import android.content.Context;
import android.content.res.ColorStateList;

public class Contexts {

    public static int getDimen(Context context, int resId) {
        return context.getResources().getDimensionPixelSize(resId);
    }

    public static ColorStateList getColorStateList(Context context, int resId) {
        return context.getResources().getColorStateList(resId);
    }
}
