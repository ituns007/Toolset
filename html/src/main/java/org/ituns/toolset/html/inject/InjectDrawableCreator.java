package org.ituns.toolset.html.inject;

import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.widget.TextView;

import org.ituns.toolset.html.drawable.DrawableCreator;
import org.ituns.toolset.html.drawable.DrawableWrapper;

import java.lang.ref.WeakReference;

public class InjectDrawableCreator implements DrawableCreator.Callback {
    private WeakReference<TextView> mTextView;
    private DrawableWrapper mDrawableWrapper;

    public InjectDrawableCreator(TextView textView) {
        mTextView = new WeakReference<>(textView);
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

        TextView textView = mTextView.get();
        if(textView == null) {
            int drawableWidth = drawable.getIntrinsicWidth();
            int drawableHeight = drawable.getIntrinsicHeight();
            drawable.setBounds(0,0, drawableWidth, drawableHeight);
        } else {
            TextPaint textPaint = textView.getPaint();
            float textHeight = textPaint.descent() - textPaint.ascent();
            float textScale = textHeight / drawable.getIntrinsicHeight();
            int drawableWidth = (int) (drawable.getIntrinsicWidth() * textScale);
            int drawableHeight = (int) (drawable.getIntrinsicHeight() * textScale);
            drawable.setBounds(0, 0, drawableWidth, drawableHeight);
        }

        DrawableWrapper drawableWrapper = mDrawableWrapper;
        if(drawableWrapper != null) {
            drawableWrapper.refreshDrawable(drawable);
            if(textView != null) {
                textView.postInvalidate();
            }
        }
    }
}
