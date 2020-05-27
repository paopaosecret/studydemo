package com.xbing.com.viewdemo.service.utils;

import android.content.Context;
import android.widget.Toast;

import com.xbing.com.viewdemo.service.net.callback.Callback;

/**
 * Created by bing.zhao on 2017/1/25.
 *
 * 使用过程详见{@link com.xbing.com.viewdemo.ui.activity.BluetoothActivity.testReflex}
 */

public class ReflexDemo {

    private Context mContext;

    public ReflexDemo(Context context){
        mContext = context;
    }

    /**
     * {@hide}
     */
    public void sayHello(String str, int i){
        Toast.makeText(mContext,"hello word,hide Method:paras:str=" + str + ",i=" +i , Toast.LENGTH_LONG).show();
    }
}
