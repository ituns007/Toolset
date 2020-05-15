package org.ituns.toolset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;

import org.ituns.toolset.html.HtmlCompat;

public class MainActivity extends AppCompatActivity {
    private AppCompatTextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textview);
    }

    public void clickTestCore(View view) {
        mTextView.setText(HtmlCompat.fromHtml("<font style=\"color:#FF0000\">No more results, something similiar</font>"));
    }

    public void clickTestHtml(View view) {
        mTextView.setText(HtmlCompat.fromHtml("<font color=\"#1A73E8\">No more results, something similiar</font>"));
    }
}
