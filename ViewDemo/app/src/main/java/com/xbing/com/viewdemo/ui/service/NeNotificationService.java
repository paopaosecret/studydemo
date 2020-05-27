package com.xbing.com.viewdemo.ui.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import com.xbing.com.viewdemo.ui.activity.MainActivity;

/**
 * Created by bing.zhao on 2017/8/8.
 */

public class NeNotificationService extends AccessibilityService {
    private static final String TAG = NeNotificationService.class.getSimpleName();

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        //判断辅助服务触发的事件是否是通知栏改变事件
        Log.i(TAG, "package name" + event.getPackageName());
        if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {

            //获取Parcelable对象
            Parcelable data = event.getParcelableData();
            Log.i(TAG, "package name" + event.getPackageName());
            //判断是否是Notification对象
            if (data instanceof Notification) {

                Notification notification = (Notification) data;
                Log.i(TAG, "NotifyData" + notification);
                Log.i(TAG, "packageName" + event.getPackageName());
            } else {
                Log.i(TAG, "packageName null");
            }
        }
    }

    @Override
    public void onInterrupt() {

    }

    /**
     *Service被启动的时候会调用这个API
     */
    @Override
    protected void onServiceConnected() {

        //设置关心的事件类型
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED |
                AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED |
                AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        info.notificationTimeout = 100;//两个相同事件的超时时间间隔
        setServiceInfo(info);
        Log.i(TAG,"service connected");
    }
}
