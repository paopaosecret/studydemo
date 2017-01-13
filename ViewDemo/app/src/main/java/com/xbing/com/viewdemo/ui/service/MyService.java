package com.xbing.com.viewdemo.ui.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by zhaobing on 2016/10/24.
 */
public class MyService extends Service {

    public static final String TAG = MyService.class.getSimpleName();

    private MyBinder mBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind");
        return mBinder;
    }
    @Override
    public void onCreate() {
        Log.i(TAG,"onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"onDestroy");
        mBinder = null;
        super.onDestroy();
    }



    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG,"onRebind");
        super.onRebind(intent);
    }


    public class MyBinder extends Binder{
        public void println(){
            Log.i(TAG,"hello  I am binder");
        };
        public void sayHello(){
            Log.i(TAG,"say hello:" + TAG);
        }
    }
}
