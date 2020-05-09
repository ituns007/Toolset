package org.ituns.toolset.html.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class DrawableWrapper extends BitmapDrawable {
    private Drawable mDrawable;

    public void refreshDrawable(Drawable drawable) {
        mDrawable = drawable;
        if(drawable != null) {
            super.setBounds(drawable.getBounds());
        }
    }

    @Override
    public void draw(Canvas canvas) {
        Drawable drawable = mDrawable;
        if(drawable != null) {
            drawable.draw(canvas);
        }
    }
}
