package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.xbing.com.viewdemo.R;

/**
 * Created by bing.zhao on 2017/1/3.
 */

class JniTestActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        TextView mShow = (TextView)findViewById(R.id.tv_show);
        mShow.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}


