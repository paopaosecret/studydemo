package com.xbing.com.viewdemo.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.xbing.com.viewdemo.R;

/**
 * Created by zhaobing04 on 2018/5/4.
 */

public class PopupWindowActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupwindow);
        immersionStatusBar();
//        findViewById(R.id.iv_navigation_right).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PopupWindow popupWindow = new PopupWindow(PopupWindowActivity.this);
//
//            }
//        });
    }

    private void immersionStatusBar() {
        if (hasKitKat() && !hasLollipop()) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else if (hasLollipop()) {
            moreThanLollipopStatusBar();
        }
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void moreThanLollipopStatusBar() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }
}
