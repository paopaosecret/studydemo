package com.xbing.com.viewdemo.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.xbing.com.viewdemo.AIDLService;

/**
 * Created by bing.zhao on 2017/4/15.
 */

public class AIDLServiceImp extends Service {

    private static final String TAG = AIDLServiceImp.class.getSimpleName();

    AIDLService.Stub mBinder = new AIDLService.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void printString() throws RemoteException {
            Log.i(TAG,"aidl----> hello word");
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

}
