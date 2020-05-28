package org.ituns.toolset;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import org.ituns.toolset.html.HtmlCompat;
import org.ituns.toolset.loger.LogerClient;
import org.ituns.toolset.loger.LogerProxy;

public class MainActivity extends AppCompatActivity {
    private AppCompatTextView mTextView;

    private LogerClient logerClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textview);

        logerClient = new LogerClient("Loger");
    }

    @Override
    protected void onDestroy() {
        logerClient.release();
        logerClient = null;
        super.onDestroy();
    }

    public void clickTestCore(View view) {
        mTextView.setText(HtmlCompat.fromHtml("<font style=\"color:#FF0000\">No more results, something similiar</font>"));
    }

    public void clickTestHtml(View view) {
        mTextView.setText(HtmlCompat.fromHtml("<font color=\"#1A73E8\">No more results, something similiar</font>"));
    }

    public void clickTestLoger(View view) {
        LogerProxy.e("wangxiulong", "LogerProxy");
        logerClient.e("wangxiulong", "LogerClient");
    }
}
