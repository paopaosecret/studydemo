package com.xbing.com.viewdemo.ui.activity.twolabel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.adapter.GridViewAdapter;

import java.io.File;
import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by zhaobing on 2016/8/24.
 */
public class ShareGridviewActivity extends Activity {

    GridView mView;
    private List<ResolveInfo> mData;

    private GridViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_activity);
        mView = (GridView) findViewById(R.id.gv_chart);
        mData = getShareApps(this);

        mAdapter = new GridViewAdapter(this,mData);
        mView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                shareToClickApp(mData.get(i).activityInfo);
            }
        });

    }

    public List<ResolveInfo> getShareApps(Context context) {
        Intent intent = new Intent(Intent.ACTION_SEND, null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setType("image/*");
        PackageManager pManager = context.getPackageManager();
        //PackageManager.COMPONENT_ENABLED_STATE_DEFAULT == 0;
        return pManager.queryIntentActivities(intent, 0);
    }


    private void shareToClickApp(ActivityInfo info) {

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "head.png";
        File file = new File(filePath);
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        shareIntent.setType("image/*");
        shareIntent.setPackage(info.packageName);
        shareIntent.setClassName(info.packageName,info.name);
        startActivity(shareIntent);
    }
}
