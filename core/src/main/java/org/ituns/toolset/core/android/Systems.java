package org.ituns.toolset.core.android;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;

public class Systems {

    public static String getAndroidId(Context context) {
        try {
            ContentResolver resolver = context.getContentResolver();
            return Settings.Secure.getString(resolver, Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            return "";
        }
    }
}
