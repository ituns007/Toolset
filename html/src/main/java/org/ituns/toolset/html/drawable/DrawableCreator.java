package org.ituns.toolset.html.drawable;

import android.graphics.drawable.Drawable;
import android.util.LruCache;

import java.util.LinkedList;

public class DrawableCreator {
    private static volatile DrawableCreator mInstance = null;

    private LruCache<String, DrawableCache> mDrawableCache;

    public static DrawableCreator instance() {
        if(mInstance == null) {
            synchronized (DrawableCreator.class) {
                if(mInstance == null) {
                    mInstance = new DrawableCreator();
                }
            }
        }
        return mInstance;
    }

    private DrawableCreator() {
        mDrawableCache = new LruCache<>(4 * 1024 * 1024);
    }

    public void create(String source, Callback callback) {
        if(source == null || callback == null) {
            return;
        }

        DrawableCache drawableCache = mDrawableCache.get(source);
        if(drawableCache == null) {
            drawableCache = new DrawableCache(source);
            mDrawableCache.put(source, drawableCache);
        }
        drawableCache.attach(callback);
    }

    private static class DrawableCache implements DrawableTaskManager.Callback {
        private String mSource;
        private Drawable mDrawable;
        private LinkedList<Callback> mCallbackList;

        public DrawableCache(String source) {
            this.mSource = source;
            this.mDrawable = null;
            this.mCallbackList = new LinkedList<>();
        }

        public void attach(Callback callback) {
            Drawable drawable = mDrawable;
            if(drawable != null) {
                callback.onDrawable(drawable);
                return;
            }

            mCallbackList.addLast(callback);
            DrawableTaskManager.getInstance().execute(mSource, this);
        }

        @Override
        public void onDrawable(Drawable drawable) {
            mDrawable = drawable;

            Callback callback = null;
            while ((callback = mCallbackList.poll()) != null) {
                callback.onDrawable(drawable);
            }
        }
    }

    public interface Callback {
        void onDrawable(Drawable drawable);
    }
}