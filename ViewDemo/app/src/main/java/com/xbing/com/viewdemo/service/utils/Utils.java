package com.xbing.com.viewdemo.service.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by zhaobing on 2016/7/1.
 */
public class Utils {
    /**
     * @param dip
     * @description: dip转px
     * @return: int
     * @Created
     * @Modify
     */
    public static int dip2Px(Context context, float dip)
    {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density + (dip > 0 ? 0.5f : -0.5f));
    }

    /**
     * @param px
     * @description: px转dip
     * @return: int
     * @Created
     * @modify
     */
    public static int px2Dip(Context context, float px)
    {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + (px > 0 ? 0.5f : -0.5f));
    }

    /**
     * @param spValue
     * @description: sp转px
     * @return: int
     * @Created
     * @modify
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static String getImagePath(String fileName){
        String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "Pictures" + File.separator;
        String path = rootPath + fileName;
        Log.i("Utils","getImagePath:" + path);
        return path;
    }
}
