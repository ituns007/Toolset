package org.ituns.toolset.html;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.widget.TextView;

import org.ituns.toolset.html.convert.ConvertImageGetter;
import org.ituns.toolset.html.inject.InjectImageGetter;

public class THtml {
    private static final String IMG_TAG = "<img";

    public static Spanned convertHtml(String html, TextPaint textPaint) {
        if(TextUtils.isEmpty(html)) {
            return new SpannableString("");
        }

        if(html.contains(IMG_TAG)) {
            return HtmlCompat.fromHtml(html, new ConvertImageGetter(textPaint));
        } else {
            return HtmlCompat.fromHtml(html);
        }
    }

    public static void injectHtml(String html, TextView textView) {
        if(textView == null) {
            return;
        }

        if(TextUtils.isEmpty(html)) {
            textView.setText(new SpannableString(""));
            return;
        }

        if(html.contains(IMG_TAG)) {
            textView.setText(HtmlCompat.fromHtml(html, new InjectImageGetter(textView)));
        } else {
            textView.setText(HtmlCompat.fromHtml(html));
        }
    }
}
