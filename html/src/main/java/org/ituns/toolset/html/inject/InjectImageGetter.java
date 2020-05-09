package org.ituns.toolset.html.inject;

import android.graphics.drawable.Drawable;
import android.text.Html;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class InjectImageGetter implements Html.ImageGetter {
    private final WeakReference<TextView> mTextView;

    public InjectImageGetter(TextView textView) {
        mTextView = new WeakReference<>(textView);
    }

    @Override
    public Drawable getDrawable(String source) {
        return new InjectDrawableCreator(mTextView.get()).create(source);
    }
}
