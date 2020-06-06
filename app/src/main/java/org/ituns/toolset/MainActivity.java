package org.ituns.toolset;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import org.ituns.toolset.loger.LogerClient;
import org.ituns.toolset.loger.LogerProxy;
import org.ituns.toolset.loger.Priority;

import java.util.List;

import static android.content.pm.PackageManager.MATCH_DEFAULT_ONLY;

public class MainActivity extends AppCompatActivity {
    private AppCompatTextView mTextView;

    private LogerClient logerClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textview);

        logerClient = new LogerClient("Loger");
        logerClient.setDebug(true);
        logerClient.setPriority(Priority.VERBOSE);
    }

    @Override
    protected void onDestroy() {
        logerClient.release();
        logerClient = null;
        super.onDestroy();
    }

    public void clickTestCore(View view) {
        Uri uri = Uri.parse("http://www.baidu.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        PackageManager packageManager = this.getPackageManager();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, MATCH_DEFAULT_ONLY);
        for(ResolveInfo info : resolveInfos) {
            ActivityInfo activityInfo = info.activityInfo;
            if(activityInfo == null) {
                continue;
            }

            Log.e("wangxiulong", activityInfo.packageName + "-" + activityInfo.name + " exported:" + activityInfo.exported + " enabled:" + activityInfo.enabled);
        }
    }

    public void clickTestHtml(View view) {
//        mTextView.setText(HtmlCompat.fromHtml("<font color=\"#1A73E8\">No more results, something similiar</font>"));
        logcat("clickTestHtml", 0);
    }

    public void clickTestLoger(View view) {
//        LogerProxy.e("wangxiulong", "LogerProxy");
//        logerClient.d("wangxiulong", "LogerClient");
//        logerClient.print(Priority.ERROR, "", "LogerClient1", null);
        logcat("clickTestHtml", 1);
    }

    private void logcat(String msg, int stackDepth) {
//        LogerProxy.print(Priority.ERROR, "wangxiulong", msg, null, stackDepth);
        logerClient.print(Priority.ERROR, "wangxiulong", msg, null, stackDepth);
    }
}
