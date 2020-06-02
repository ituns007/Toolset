package org.ituns.toolset.core.layout;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class LLLayoutParams extends LinearLayout.LayoutParams {

    public LLLayoutParams(Context c, AttributeSet attrs) {
        super(c, attrs);
    }

    public LLLayoutParams(int width, int height) {
        super(width, height);
    }

    public LLLayoutParams(int width, int height, float weight) {
        super(width, height, weight);
    }

    public LLLayoutParams(ViewGroup.LayoutParams p) {
        super(p);
    }

    public LLLayoutParams(ViewGroup.MarginLayoutParams source) {
        super(source);
    }

    public void marginLeft(int left) {
        leftMargin = left;
    }

    public void marginTop(int top) {
        topMargin = top;
    }

    public void marginRight(int right) {
        rightMargin = right;
    }

    public void marginBottom(int bottom) {
        bottomMargin = bottom;
    }

    public void marginStart(int start) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            setMarginStart(start);
        }
    }

    public void marginEnd(int end) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            setMarginEnd(end);
        }
    }
}
