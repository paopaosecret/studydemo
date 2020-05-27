package com.xbing.com.viewdemo.service.cpsp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.xbing.com.viewdemo.MyApplication;
import android.content.SharedPreferences.Editor;

/**
 * Created by bing.zhao on 2017/3/28.
 */
public class TestSharedPreferences {

    private final static String TAG = "SyncSettingsDataPre";
    private CPSharedPreferences mPreferences;
    private Editor mEditor;

    private TestSharedPreferences(Context context)
    {
        mPreferences = new CPSharedPreferences(context, "test_sharedpreferences");
        mEditor = mPreferences.edit();
    }
    /**
     * Get the single object instance.
     */
    public static synchronized TestSharedPreferences getInstance(Context context)
    {
        return new TestSharedPreferences(context);
    }

    public void putString(String key, String value){
        mEditor.putString(key,value);
        mEditor.commit();
    }

    public String getString(String key, String defauleValue){
        return mPreferences.getString(key, defauleValue);
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

    public boolean getBoolean(String key, boolean defValue) {
        return mPreferences.getBoolean(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return mPreferences.getFloat(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mPreferences.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return mPreferences.getLong(key, defValue);
    }

    /**
     * 判断SP是否包含特定key的数据
     * @param key
     * @return
     */
    public boolean contains(String key){
        return mPreferences.contains(key);
    }

    /**
     * 删除SP里指定key对应的数据项
     * @param key
     */
    public void remove(String key) {
        mEditor.remove(key);
        mEditor.commit();
    }

    /**
     * 清空SP里所以数据
     */
    public void clear() {
        mEditor.clear();
        mEditor.commit();
    }

}
