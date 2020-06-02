package org.ituns.toolset.core.android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

import static android.content.pm.PackageManager.MATCH_DEFAULT_ONLY;

public class Applications {
    private static final String TAG = "Applications";

    public static boolean resolveUrl(Context context, String packageId, String url) {
        if (context == null || TextUtils.isEmpty(packageId) || TextUtils.isEmpty(url)) {
            Log.d(TAG, "invalid params.");
            return false;
        }

        try {
            return resolveActivityInfo(context, packageId, Uri.parse(url)) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean openWithUrl(Context context, String packageId, String url) {
        if (context == null || TextUtils.isEmpty(packageId) || TextUtils.isEmpty(url)) {
            Log.d(TAG, "invalid params.");
            return false;
        }

        try {
            Uri uri = Uri.parse(url);
            ActivityInfo activityInfo = resolveActivityInfo(context, packageId, uri);
            if(activityInfo == null) {
                return false;
            }

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setClassName(activityInfo.packageName, activityInfo.name);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static ActivityInfo resolveActivityInfo(Context context, String packageId, Uri uri) throws Exception {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, MATCH_DEFAULT_ONLY);

        for(ResolveInfo info : resolveInfos) {
            ActivityInfo activityInfo = info.activityInfo;
            if(activityInfo == null) {
                continue;
            }

            if(!activityInfo.exported || !activityInfo.enabled) {
                continue;
            }

            if(packageId.equals(activityInfo.packageName)) {
                return activityInfo;
            }
        }
        return null;
    }
}