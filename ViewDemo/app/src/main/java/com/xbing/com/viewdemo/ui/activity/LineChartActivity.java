package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.customview.LineChartView;

/**
 * Created by zhaobing on 2016/8/15.
 */
public class LineChartActivity extends Activity {
    LineChartView mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linechart_activity);

        mLineChart = (LineChartView)findViewById(R.id.lcv_chart);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLineChart.setRunning(true);
            }
        });

        findViewById(R.id.btn_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLineChart.setRunning(false);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLineChart.setRunning(false);
    }
}
