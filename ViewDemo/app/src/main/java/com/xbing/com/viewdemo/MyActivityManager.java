package com.xbing.com.viewdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.xbing.com.viewdemo.ui.service.FloatService;

import java.util.Stack;

/**
 * Created by bing.zhao on 2016/12/5.
 */

public class MyActivityManager implements Application.ActivityLifecycleCallbacks {
    private int frontRearCount = 0;//用于监听前后台切换
    public Stack<Activity> getmActivityStack() {
        return mActivityStack;
    }

    public void setmActivityStack(Stack<Activity> mActivityStack) {
        this.mActivityStack = mActivityStack;
    }

    private Stack<Activity> mActivityStack = new Stack<Activity>();

    private static MyActivityManager instance = new MyActivityManager();

    private MyActivityManager(){
    }

    public static MyActivityManager getInstance(){
        return  instance;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        frontRearCount ++ ;
        mActivityStack.add(activity);
        Log.i("ActivityManager","ActivityManager.mActivityStack.size = " + mActivityStack.size()
                + ",activity.name = " + activity.getComponentName());

        Log.i("ActivityManager", "onActivityStarted: " + activity.getPackageName());

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.i("FloatActivity","onActivityStopped :" + activity.getPackageName());
        frontRearCount--;
        if(frontRearCount == 0){
            Log.i("FloatActivity","stop FloatService :" + activity.getPackageName());
            Intent intent = new Intent(activity, FloatService.class);
            activity.stopService(intent);
        }
        mActivityStack.remove(activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
