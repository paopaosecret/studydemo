package com.xbing.com.viewdemo.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/11/19.
 */

public class HeartService extends Service {

    private static final String TAG = HeartService.class.getSimpleName();
    private Timer mTimer;

    @Override
    public void onCreate() {
        super.onCreate();
        mTimer = new Timer();
        Log.d(TAG,"onCreate()");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG,"onStart()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand()");
        super.onStartCommand(intent, flags, startId);
        mTimer.schedule(new HeartTask(),1000,5000);
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class HeartTask extends TimerTask {

        @Override
        public void run() {
            Log.d(TAG,"heartTask run + " + new Date().toGMTString());
        }
    }
}
