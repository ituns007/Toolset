package org.ituns.toolset.html.drawable;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.http.HttpResponseCache;

import java.io.InputStream;
import java.net.CacheResponse;
import java.net.URI;
import java.net.URL;

public class DrawableTask implements Runnable {
    private String mSource;
    private Callback mCallback;

    public DrawableTask(String source, Callback callback) {
        mSource = source;
        mCallback = callback;
    }

    @Override
    public void run() {
        Drawable drawable = readDiskDrawable(mSource);
        if(drawable == null) {
            drawable = readRemoteDrawable(mSource);
        }

        Callback callback = mCallback;
        if(callback != null) {
            callback.onDrawable(mSource, drawable);
            mCallback = null;
        }
    }

    private Drawable readDiskDrawable(String source) {
        try {
            HttpResponseCache httpResponseCache = HttpResponseCache.getInstalled();
            if(httpResponseCache == null) {
                return null;
            }

            URI uri = URI.create(source);
            CacheResponse cacheResponse = httpResponseCache.get(uri, "GET", null);
            if(cacheResponse == null) {
                return null;
            }

            return new BitmapDrawable(null, cacheResponse.getBody());
        } catch (Exception e) {
            return null;
        }
    }

    private Drawable readRemoteDrawable(String source) {
        try {
            InputStream is = new URL(source).openStream();
            return new BitmapDrawable(null, is);
        } catch (Exception e) {
            return null;
        }
    }

    public interface Callback {
        void onDrawable(String source, Drawable drawable);
    }
}
