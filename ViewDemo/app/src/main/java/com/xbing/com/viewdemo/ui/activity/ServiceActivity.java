package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.service.MyService;

/**
 * Created by zhaobing on 2016/10/24.
 */

public class ServiceActivity extends Activity implements View.OnClickListener{

    private static final String TAG = MyService.class.getSimpleName();

    private MyService.MyBinder mBinder;
//    private MyService mBinder;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Log.i(TAG,"onServiceConnected");
            mBinder = (MyService.MyBinder)service;
            mBinder.println();
//            mBinder = ((MyService.MyBinder)service).getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG,"onServiceDisconnected");
            mBinder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);

        findViewById(R.id.start_service).setOnClickListener(this);
        findViewById(R.id.stop_service).setOnClickListener(this);
        findViewById(R.id.bind_service).setOnClickListener(this);
        findViewById(R.id.unbind_service).setOnClickListener(this);
        findViewById(R.id.say_hello).setOnClickListener(this);
        findViewById(R.id.gc).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(ServiceActivity.this, MyService.class);
        switch (v.getId()){
            case R.id.start_service:
                startService(intent);
                break;
            case R.id.stop_service:
                stopService(intent);
                break;
            case R.id.bind_service:
                bindService(intent,serviceConnection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(serviceConnection);
                break;
            case R.id.say_hello:
                mBinder.sayHello();
                break;
            case R.id.gc:
                System.gc();
                break;
        }
    }
}
