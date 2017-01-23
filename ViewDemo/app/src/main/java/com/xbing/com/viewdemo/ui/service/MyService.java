package com.xbing.com.viewdemo.ui.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 使用NotificationListenerService需要注意的点：
 * app开启这个监听服务之后，会收到系统通知栏的通知消息
 *
 * 长时间不用或者app被杀死在后台之后该服务的方法不被回调
 * 解决方案：
 * 使用：adb shell dumpsys notification 命令
 * 查看手机当前启动该service的应用有哪些，以及那些是生存的
 * All notification listeners和Live notification listeners
 *
 * 判断是否使能：
 * 调用{@link  com.xbing.com.viewdemo.ui.activity.ServiceActivity.isEnabled} 先跳进该类在找方法
 * 如果没有使能
 * 调用{@link com.xbing.com.viewdemo.ui.activity.ServiceActivity.openNotificationAccess}
 *
 * 如果没有生存：
 * 调用{@link com.xbing.com.viewdemo.ui.activity.ServiceActivity.toggleNotificationListenerService方法}
 * 先disable再enable，即可触发系统rebind操作。
 *
 * Created by zhaobing on 2016/10/24.
 */
@SuppressLint("NewApi")
public class MyService extends NotificationListenerService {

    public static final String TAG = MyService.class.getSimpleName();

    private MyBinder mBinder = new MyBinder();

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        Log.i(TAG,"onNotificationPosted");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn, RankingMap rankingMap) {
        super.onNotificationPosted(sbn, rankingMap);
        Log.i(TAG,"onNotificationPosted2");
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        Log.i(TAG,"onNotificationRemoved");
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn, RankingMap rankingMap) {
        super.onNotificationRemoved(sbn, rankingMap);
        Log.i(TAG,"onNotificationRemoved2");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind");
        return super.onBind(intent);
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
