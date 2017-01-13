package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.service.utils.NotifyUtils;

/**
 * Created by zhaobing on 2016/8/11.
 */
public class NotifyActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify_activity);

        findViewById(R.id.btn_showNOtifyOld).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                NotifyUtils.showNotifyOld(getApplicationContext());
            }
        });

        findViewById(R.id.btn_showNOtify11).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                NotifyUtils.showNotify11(getApplicationContext());

            }
        });

        findViewById(R.id.btn_showNOtify16).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                NotifyUtils.showNotify16(getApplicationContext());

            }
        });

        findViewById(R.id.btn_showNOtifyDefine).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                NotifyUtils.showNotifyDefine(getApplicationContext());

            }
        });

        findViewById(R.id.btn_cancelNotifyById).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                NotifyUtils.cancelNotify(getApplicationContext(), 1);

            }
        });

        findViewById(R.id.btn_cancelNotifyAll).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                NotifyUtils.cancelAllNotify(getApplicationContext());

            }
        });
    }
}
