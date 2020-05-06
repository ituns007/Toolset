package org.ituns.toolset.core;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class TDevice {

    /**
     * get screen width
     *
     * @param context
     * @return
     */
    public static float width(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    /**
     * get screen height
     *
     * @param context
     * @return
     */
    public static float height(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    /**
     * get screen density
     *
     * @param context
     * @return
     */
    public static float density(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.density;
    }

    /**
     * get display metrics
     *
     * @param context
     * @return
     */
    public static DisplayMetrics displayMetrics(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    public static String getAndroidId(Context context) {
        try {
            ContentResolver resolver = context.getContentResolver();
            return Settings.Secure.getString(resolver, Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            return "";
        }
    }
}
