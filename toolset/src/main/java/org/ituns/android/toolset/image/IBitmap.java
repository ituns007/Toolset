package org.ituns.android.toolset.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

public class IBitmap {

    public static Creator creator(Context context, Uri uri) {
        return new Creator(context, uri);
    }

    public static Creator creator(byte[] bytes) {
        return new Creator(bytes);
    }

    public static Transform transform(Bitmap bitmap) {
        return null;
    }

    public static Parser parser(Bitmap bitmap) {
        return null;
    }

    public static class Creator {
        private Bitmap bitmap;

        public Creator(Context context, Uri uri) {

        }

        public Creator(byte[] bytes) {

        }

        public Creator(Bitmap bitmap) {

        }


    }

    public static class Transform {

    }

    public static class Parser {

    }
}
