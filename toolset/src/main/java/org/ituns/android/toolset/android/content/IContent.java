package org.ituns.android.toolset.android.content;

import android.content.Context;
import android.net.Uri;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

public class IContent {

    public static InputStream input(Context context, Uri uri) {
        try {
            return context.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static OutputStream output(Context context, Uri uri) {
        try {
            return context.getContentResolver().openOutputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
