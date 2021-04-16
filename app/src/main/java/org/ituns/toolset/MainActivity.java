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

import org.ituns.android.toolset.android.content.DeepLink;
import org.ituns.android.toolset.android.lifecycle.AlignLiveData;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppCompatTextView mTextView;

    private AlignLiveData<String> liveData = new AlignLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textview);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void clickTestCore(View view) {
        liveData.postValue("test");
    }

    private void searchByNative() {
        Uri uri = Uri.parse("http://www.baidu.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        PackageManager packageManager = this.getPackageManager();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL);
        for(ResolveInfo info : resolveInfos) {
            ActivityInfo activityInfo = info.activityInfo;
            if(activityInfo == null) {
                continue;
            }

            Log.e("wangxiulong", activityInfo.packageName + "-" + activityInfo.name + " exported:" + activityInfo.exported + " enabled:" + activityInfo.enabled);
        }
    }

    private void searchByDeepLink() {
        Uri uri = Uri.parse("http://www.baidu.com/");
        List<ActivityInfo> activityInfos = DeepLink.activityInfos(this, uri);
        for(ActivityInfo activityInfo : activityInfos) {
            if(activityInfo == null) {
                continue;
            }
            Log.e("wangxiulong", activityInfo.packageName + "-" + activityInfo.name + " exported:" + activityInfo.exported + " enabled:" + activityInfo.enabled);
        }
    }
}
