package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.xbing.com.viewdemo.R;

/**
 * Created by zhaobing on 2016/6/21.
 */
public class ParamActivity extends Activity {

    public ParamActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.param_activity);
        Log.d("liftcycle",getClass().getSimpleName()+":onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("liftcycle",getClass().getSimpleName()+":onstart()");
        Intent intent = new Intent();
        intent.setAction("");
        intent.addCategory("");
        intent.setDataAndType(Uri.parse(""),"");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("liftcycle",getClass().getSimpleName()+":onrestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("liftcycle",getClass().getSimpleName()+":onresume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("liftcycle",getClass().getSimpleName()+":onpause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("liftcycle",getClass().getSimpleName()+":onstop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("liftcycle",getClass().getSimpleName()+":onDestroy()");
    }
}
