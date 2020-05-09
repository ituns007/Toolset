package org.ituns.toolset.html.convert;

import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextPaint;

import java.lang.ref.WeakReference;

public class ConvertImageGetter implements Html.ImageGetter {
    private WeakReference<TextPaint> mTextPaint;

    public ConvertImageGetter(TextPaint textPaint) {
        mTextPaint = new WeakReference<>(textPaint);
    }

    @Override
    public Drawable getDrawable(String source) {
        return new ConvertDrawableCreator(mTextPaint.get()).create(source);
    }
}
