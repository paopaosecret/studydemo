package com.xbing.com.viewdemo.maidian;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.View;

/**
 * Created by zhaobing04 on 2018/5/3.
 */

public class Listener {

    public static class OnClickListener implements View.OnClickListener{
        String tag = "OnClickListener";

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public void onClick(View v) {
            Log.e(tag,"view.name:" + v.getTag());
        }


    }
}
