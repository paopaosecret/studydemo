package com.xbing.com.viewdemo.keeplive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class KeepLiveReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //屏幕点亮
        if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            KeepLiveManager.getOutInstance().finishKeepLiveActivity();
        }else if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
            KeepLiveManager.getOutInstance().startKeepLiveActivity();
        }
    }
}
