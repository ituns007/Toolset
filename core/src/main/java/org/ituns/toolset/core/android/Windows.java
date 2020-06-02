package org.ituns.toolset.core.android;

import android.content.Context;
import android.content.res.Resources;

public class Windows {

    public static float width(Context context) {
        Resources resources = context.getResources();
        return resources.getDisplayMetrics().widthPixels;
    }

    public static float height(Context context) {
        Resources resources = context.getResources();
        return resources.getDisplayMetrics().heightPixels;
    }

    public static float density(Context context) {
        Resources resources = context.getResources();
        return resources.getDisplayMetrics().density;
    }

    public static int dp2px(Context context, float dp) {
        Resources resources = context.getResources();
        float density = resources.getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    public static int px2dp(Context context, float px) {
        Resources resources = context.getResources();
        float density = resources.getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }

    public static int sp2px(Context context, float sp) {
        Resources resources = context.getResources();
        float density = resources.getDisplayMetrics().scaledDensity;
        return (int) (sp * density + 0.5f);
    }

    public static int px2sp(Context context, float px) {
        Resources resources = context.getResources();
        float density = resources.getDisplayMetrics().scaledDensity;
        return (int) (px / density + 0.5f);
    }
}