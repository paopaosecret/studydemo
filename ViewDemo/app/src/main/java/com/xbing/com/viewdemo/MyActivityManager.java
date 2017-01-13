package com.xbing.com.viewdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.xbing.com.viewdemo.MyApplication;

import java.util.Stack;

/**
 * Created by bing.zhao on 2016/12/5.
 */

public class MyActivityManager implements Application.ActivityLifecycleCallbacks {

    private Stack<Activity> mActivityStack = new Stack<Activity>();

    private static MyActivityManager instance = new MyActivityManager();

    private MyActivityManager(){
    }

    public static MyActivityManager getInstance(){
        return  instance;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        mActivityStack.add(activity);
        Log.i("ActivityManager","ActivityManager.mActivityStack.size = " + mActivityStack.size());
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        mActivityStack.remove(activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
