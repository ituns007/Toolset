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

public class IntentUtils {

//    public static boolean resolveApp(Context context, String packageId, String url) {
//        if (context == null || TextUtils.isEmpty(packageId) || TextUtils.isEmpty(url)) {
//            Log.d("context or package id or url is null or empty.");
//            return false;
//        }
//
//        PackageManager packageManager = context.getPackageManager();
//        if(packageManager == null) {
//            QuryLog.d("package manager is null.");
//            return false;
//        }
//
//        Uri uri = NetUtils.safeUri(url);
//        if(uri == null) {
//            QuryLog.d("uri is null for:" + url);
//            return false;
//        }
//
//        ActivityInfo activityInfo = resolveUri(packageManager, uri, packageId);
//        if(activityInfo == null) {
//            QuryLog.d("activity info is null for:" + packageId);
//            return false;
//        }
//
//        return true;
//    }
//
//    public static boolean openApp(Context context, String packageId, String url) {
//        if (context == null || TextUtils.isEmpty(packageId) || TextUtils.isEmpty(url)) {
//            QuryLog.d("context or package id or url is null or empty.");
//            return false;
//        }
//
//        PackageManager packageManager = context.getPackageManager();
//        if(packageManager == null) {
//            QuryLog.d("package manager is null.");
//            return false;
//        }
//
//        Uri uri = NetUtils.safeUri(url);
//        if(uri == null) {
//            QuryLog.d("uri is null for:" + url);
//            return false;
//        }
//
//        ActivityInfo activityInfo = resolveUri(packageManager, uri, packageId);
//        if(activityInfo == null) {
//            QuryLog.d("activity info is null for:" + packageId);
//            return false;
//        }
//
//        try {
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.setClassName(activityInfo.packageName, activityInfo.name);
//            context.startActivity(intent);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private static ActivityInfo resolveUri(PackageManager packageManager, Uri uri, String packageId) {
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
//        if(CollectionUtils.isEmpty(resolveInfos)) {
//            QuryLog.d("resole info list is null or empty.");
//            return null;
//        }
//
//        for(ResolveInfo info : resolveInfos) {
//            ActivityInfo activityInfo = info.activityInfo;
//            if(activityInfo == null) {
//                continue;
//            }
//
//            if(!activityInfo.exported) {
//                continue;
//            }
//
//            if(packageId.equals(activityInfo.packageName)) {
//                return activityInfo;
//            }
//        }
//
//        return null;
//    }
}