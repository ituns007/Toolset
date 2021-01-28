package org.ituns.android.toolset.android.content;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class IContext {
    private static final String TAG = "IContext";

    public static Context applicationContext(Context context) {
        if(context instanceof Application) {
            return context;
        }
        return context == null ? null : context.getApplicationContext();
    }

    public static int versionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                return (int)packageInfo.getLongVersionCode();
            } else {
                return packageInfo.versionCode;
            }
        } catch (Exception e) {
            Log.i(TAG, "version code exception:", e);
            return 1;
        }
    }

    public static String versionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            Log.i(TAG, "version name exception:", e);
            return "1.0.0";
        }
    }

    public static int dimen(Context context, int resId) {
        return context.getResources().getDimensionPixelSize(resId);
    }

    public static ColorStateList colorStateList(Context context, int resId) {
        return context.getResources().getColorStateList(resId);
    }

    public static String cachePath(Context context, String dir) {
        String cachePath = cachePath(context);
        if(cachePath != null) {
            return cachePath + File.separator + dir;
        }
        return null;
    }

    public static File cacheDir(Context context, String dir)  {
        String cachePath = cachePath(context);
        if(cachePath != null) {
            return new File(cachePath + File.separator + dir);
        }
        return null;
    }

    public static String cachePath(Context context) {
        File cacheDir = cacheDir(context);
        if(cacheDir != null) {
            return cacheDir.getAbsolutePath();
        }
        return null;
    }

    public static File cacheDir(Context context) {
        File cacheDir = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cacheDir = context.getExternalCacheDir();
        }
        if(cacheDir == null) {
            cacheDir = context.getCacheDir();
        }
        return cacheDir;
    }
}
