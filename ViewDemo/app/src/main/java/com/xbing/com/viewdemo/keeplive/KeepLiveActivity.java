package com.xbing.com.viewdemo.keeplive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class KeepLiveActivity extends AppCompatActivity{

    private static final String TAG = KeepLiveActivity.class.getName();

    public static void launch(){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: 关闭KeepLiveActivity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: 开启 KeepLiveActivity");

    }
}
