package org.ituns.android.toolset.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;

import org.ituns.android.toolset.network.IUri;
import org.ituns.android.toolset.io.IStream;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class IBitmap {

    public static Creator creator(Context context, Uri uri) {
        return new Creator(context, uri);
    }

    public static Creator creator(byte[] bytes) {
        return new Creator(bytes);
    }

    public static Transform transform(Bitmap bitmap) {
        return new Transform(bitmap);
    }

    public static Parser parser(Bitmap bitmap) {
        return new Parser(bitmap);
    }

    public static class Creator {
        public static final int KB = 1024;
        public static final int MB = 1024 * 1024;
        public static final int GB = 1024 * 1024 * 1024;

        private byte[] data;
        private BitmapFactory.Options options;

        private Creator(Context context, Uri uri) {
           this(IStream.input(IUri.with(uri).input(context)).readBytes());
        }

        private Creator(byte[] data) {
            this.data = data;
            this.options = decodeOptions(data);
        }

        private BitmapFactory.Options decodeOptions(byte[] bytes) {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
                return options;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public Creator reset() {
            if(options != null) {
                options.inSampleSize = 1;
            }
            return this;
        }

        public Creator maxWidth(int maxWidth) {
            if(options != null && 0 < maxWidth) {
                int outWidth = options.outWidth;
                int sampleSize = Math.max(1, options.inSampleSize);
                while ((outWidth / sampleSize) > maxWidth) {
                    sampleSize *= 2;
                }
                options.inSampleSize = sampleSize;
            }
            return this;
        }

        public Creator maxHeight(int maxHeight) {
            if(options != null && 0 < maxHeight) {
                int outHeight = options.outHeight;
                int sampleSize = Math.max(1, options.inSampleSize);
                while ((outHeight / sampleSize) > maxHeight) {
                    sampleSize *= 2;
                }
                options.inSampleSize = sampleSize;
            }
            return this;
        }

        public Creator maxLength(int maxLength) {
            if(options != null && 0 < maxLength) {
                int outWidth = options.outWidth;
                int outHeight = options.outHeight;
                int pixelBytes = pixelBytes(options.inPreferredConfig);
                int sampleSize = Math.max(1, options.inSampleSize);
                while ((outWidth / sampleSize) * (outHeight / sampleSize) * pixelBytes > maxLength) {
                    sampleSize *= 2;
                }
                options.inSampleSize = sampleSize;
            }
            return this;
        }

        private int pixelBytes(Bitmap.Config config) {
            switch (config) {
                case ALPHA_8:
                    return 1;
                case RGB_565:
                    return 2;
                case ARGB_4444:
                    return 2;
                case ARGB_8888:
                    return 4;
                case RGBA_F16:
                    return 8;
                default:
                    return 1;
            }
        }

        public Bitmap bitmap() {
            if(data != null && options != null) {
                options.inJustDecodeBounds = false;
                return BitmapFactory.decodeByteArray(data, 0, data.length, options);
            }
            return null;
        }

        public Transform transform() {
            return new Transform(bitmap());
        }

        public Parser parser() {
            return new Parser(bitmap());
        }
    }

    public static class Transform {
        private Bitmap bitmap;
        private Matrix matrix;

        private Transform(Bitmap bitmap) {
            this.bitmap = bitmap;
            this.matrix = new Matrix();
        }

        public Transform reset() {
            this.matrix.reset();
            return this;
        }

        public Transform skew(float kx, float ky) {
            matrix.postSkew(kx, ky);
            return this;
        }

        public Transform skew(float kx, float ky, float px, float py) {
            matrix.postSkew(kx, ky, px, py);
            return this;
        }

        public Transform scale(float sx, float sy) {
            this.matrix.postScale(sx, sy);
            return this;
        }

        public Transform scale(float sx, float sy, float px, float py) {
            this.matrix.postScale(sx, sy, px, py);
            return this;
        }

        public Transform rotate(float degrees) {
            this.matrix.postRotate(degrees);
            return this;
        }

        public Transform rotate(float degrees, float px, float py) {
            this.matrix.postRotate(degrees, px, py);
            return this;
        }

        public Transform translate(float dx, float dy) {
            this.matrix.postTranslate(dx, dy);
            return this;
        }

        public Bitmap bitmap() {
            try {
                Bitmap result = Bitmap.createBitmap(bitmap,
                        0, 0,
                        bitmap.getWidth(),
                        bitmap.getHeight(),
                        matrix, false);
                if(!bitmap.isRecycled()) {
                    bitmap.recycle();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public Parser parser() {
            return new Parser(bitmap());
        }
    }

    public static class Parser {
        private Bitmap bitmap;

        private Parser(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public byte[] raw() {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            writeTo(baos);
            return baos.toByteArray();
        }

        public void writeTo(Context context, Uri uri) {
            writeTo(IUri.with(uri).output(context));
        }

        public void writeTo(OutputStream os) {
            try {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                if(!bitmap.isRecycled()) {
                    bitmap.recycle();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
