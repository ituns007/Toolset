package org.ituns.toolset.core.layout;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class RLLayoutParams extends RelativeLayout.LayoutParams {

    public RLLayoutParams(Context c, AttributeSet attrs) {
        super(c, attrs);
    }

    public RLLayoutParams(int w, int h) {
        super(w, h);
    }

    public RLLayoutParams(ViewGroup.LayoutParams source) {
        super(source);
    }

    public RLLayoutParams(ViewGroup.MarginLayoutParams source) {
        super(source);
    }

    public void leftOf(int id) {
        addRule(RelativeLayout.LEFT_OF, id);
    }

    public void rightOf(int id) {
        addRule(RelativeLayout.RIGHT_OF, id);
    }

    public void above(int id) {
        addRule(RelativeLayout.ABOVE, id);
    }

    public void below(int id) {
        addRule(RelativeLayout.BELOW, id);
    }

    public void startOf(int id) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            addRule(RelativeLayout.START_OF, id);
        }
    }

    public void endOf(int id) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            addRule(RelativeLayout.END_OF, id);
        }
    }

    public void alignBaseline(int id) {
        addRule(RelativeLayout.ALIGN_BASELINE, id);
    }

    public void alignLeft(int id) {
        addRule(RelativeLayout.ALIGN_LEFT, id);
    }

    public void alignTop(int id) {
        addRule(RelativeLayout.ALIGN_TOP, id);
    }

    public void alignRight(int id) {
        addRule(RelativeLayout.ALIGN_RIGHT, id);
    }

    public void alignBottom(int id) {
        addRule(RelativeLayout.ALIGN_BOTTOM, id);
    }

    public void alignStart(int id) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            addRule(RelativeLayout.ALIGN_START, id);
        }
    }

    public void alignEnd(int id) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            addRule(RelativeLayout.ALIGN_END, id);
        }
    }

    public void alignParentLeft() {
        addRule(RelativeLayout.ALIGN_PARENT_LEFT);
    }

    public void alignParentTop() {
        addRule(RelativeLayout.ALIGN_PARENT_TOP);
    }

    public void alignParentRight() {
        addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    }

    public void alignParentBottom() {
        addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
    }

    public void alignParentStart() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            addRule(RelativeLayout.ALIGN_PARENT_START);
        }
    }

    public void alignParentEnd() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            addRule(RelativeLayout.ALIGN_PARENT_END);
        }
    }

    public void centerInParent() {
        addRule(RelativeLayout.CENTER_IN_PARENT);
    }

    public void centerHorizontal() {
        addRule(RelativeLayout.CENTER_HORIZONTAL);
    }

    public void centerVertical() {
        addRule(RelativeLayout.CENTER_VERTICAL);
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
