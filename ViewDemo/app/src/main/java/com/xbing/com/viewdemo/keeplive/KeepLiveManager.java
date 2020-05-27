package com.xbing.com.viewdemo.keeplive;

import java.lang.ref.WeakReference;

public class KeepLiveManager {

    private static final KeepLiveManager outInstance = new KeepLiveManager();

    public static KeepLiveManager getOutInstance(){
        return outInstance;
    }

    private WeakReference<KeepLiveActivity> mRefrence;

    private KeepLiveReceiver mKeepLiveReceive;

    public void registerReciver(){

    }

    public void unRegisterReciver(){

    }

    public void startKeepLiveActivity(){

    }

    public void finishKeepLiveActivity(){

    }
}
