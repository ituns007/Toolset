package org.ituns.toolset.html.convert;

import android.graphics.drawable.Drawable;
import android.text.TextPaint;

import org.ituns.toolset.html.drawable.DrawableCreator;
import org.ituns.toolset.html.drawable.DrawableWrapper;

import java.lang.ref.WeakReference;

public class ConvertDrawableCreator implements DrawableCreator.Callback {
    private WeakReference<TextPaint> mTextPaint;
    private DrawableWrapper mDrawableWrapper;

    public ConvertDrawableCreator(TextPaint textPaint) {
        mTextPaint = new WeakReference<>(textPaint);
        mDrawableWrapper = new DrawableWrapper();
    }

    public Drawable create(String source) {
        DrawableCreator.instance().create(source, this);
        DrawableWrapper drawableWrapper = mDrawableWrapper;
        if(drawableWrapper == null) {
            drawableWrapper = new DrawableWrapper();
            mDrawableWrapper = drawableWrapper;
        }
        return drawableWrapper;
    }

    @Override
    public void onDrawable(Drawable drawable) {
        if(drawable == null) {
            return;
        }

        Drawable.ConstantState constantState = drawable.getConstantState();
        if(constantState != null) {
            drawable = constantState.newDrawable().mutate();
        }

        TextPaint textPaint = mTextPaint.get();
        if(textPaint == null) {
            int drawableWidth = drawable.getIntrinsicWidth();
            int drawableHeight = drawable.getIntrinsicHeight();
            drawable.setBounds(0,0, drawableWidth, drawableHeight);
        } else {
            float textHeight = textPaint.descent() - textPaint.ascent();
            float textScale = textHeight / drawable.getIntrinsicHeight();
            int drawableWidth = (int) (drawable.getIntrinsicWidth() * textScale);
            int drawableHeight = (int) (drawable.getIntrinsicHeight() * textScale);
            drawable.setBounds(0,0, drawableWidth, drawableHeight);
        }

        DrawableWrapper drawableWrapper = mDrawableWrapper;
        if(drawableWrapper != null) {
            drawableWrapper.refreshDrawable(drawable);
        }
    }
}
