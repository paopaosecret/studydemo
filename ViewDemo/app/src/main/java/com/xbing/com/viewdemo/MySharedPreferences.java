package com.xbing.com.viewdemo;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.designpattern.Singleton.Singleton2;

/**
 * Created by bing.zhao on 2017/3/22.
 */

public class MySharedPreferences {

    /**--------------------------------key start----------------------------------*/

    /**
     * debug 开关
     * true:打开
     * false:关闭
     */
    public static final String SP_KEY_DEBUG_SWITCH = "sp_key_debug_switch";
    /**--------------------------------key end----------------------------------*/

    private static final String mFileName = "userId_sharedpreferences";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private MySharedPreferences(Context context) {
        mSharedPreferences = context.getSharedPreferences(mFileName, context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public volatile static MySharedPreferences instances;

    public static MySharedPreferences getInstances(Context context){
        if(instances == null){
            synchronized(MySharedPreferences.class){
                if(instances == null){
                    instances = new MySharedPreferences(context);
                }
            }
        }
        return instances;
    }

    /**
     * 向SP存入指定key对应的数据
     * 其中value可以是String、boolean、float、int、long等各种基本类型的值
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public void putBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public void putFloat(String key, float value) {
        mEditor.putFloat(key, value);
        mEditor.commit();
    }

    public void putInt(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public void putLong(String key, long value) {
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    /**
     * 获取SP数据里指定key对应的value。如果key不存在，则返回默认值defValue。
     * @param key
     * @param defValue
     * @return
     */
    public String getString(String key, String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return mSharedPreferences.getFloat(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }

    /**
     * 判断SP是否包含特定key的数据
     * @param key
     * @return
     */
    public boolean contains(String key){
        return mSharedPreferences.contains(key);
    }

    /**
     * 清空SP里所以数据
     */
    public void clear() {
        mEditor.clear();
        mEditor.commit();
    }

    /**
     * 删除SP里指定key对应的数据项
     * @param key
     */
    public void remove(String key) {
        mEditor.remove(key);
        mEditor.commit();
    }

}
