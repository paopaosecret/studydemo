package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.customview.LineChart2View;
import com.xbing.com.viewdemo.ui.customview.LineChartView;
import com.xbing.com.viewdemo.ui.customview.entity.Dot;

import java.util.Random;

/**
 * Created by zhaobing on 2016/8/15.
 */
public class LineChartActivity extends Activity {
    LineChartView mLineChart;
    LineChart2View mLineChart2;
    private boolean isRunning = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linechart_activity);

        mLineChart = (LineChartView) findViewById(R.id.lcv_chart);
        mLineChart.setmStyle(LineChartView.Linestyle.Curve);

        mLineChart2 = (LineChart2View) findViewById(R.id.lcv_chart2);
        mLineChart2.setmStyle(LineChart2View.Linestyle.Curve);

        for(int i = 0; i < 120; i++){
            Dot dot = new Dot(new Random().nextInt(120) + 60);
            if(i%14 == 0){
                dot.setType(Dot.DotType.STATIC_DOT);
            }
            mLineChart2.getData().add(dot);
        }

        mLineChart2.postInvalidate();
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isRunning = true;
                isRunning = true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (isRunning) {
                            try {
                                Thread.sleep(100);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (mLineChart.getData().size() >= mLineChart.getxMaxCount()) {
                                mLineChart.getData().remove(0);
                            }
                            mLineChart.getData().add(new Dot(new Random().nextInt(4) + 1));
                            mLineChart.postInvalidate();
                        }
                    }
                }).start();

            }
        });

        findViewById(R.id.btn_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning = false;
            }
        });

        findViewById(R.id.btn_enlarge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLineChart2.getData().clear();
                for(int i = 0; i < 120; i++){
                    Dot dot = new Dot(new Random().nextInt(120) + 60);
                    if(i%20 == 0){
                        dot.setType(Dot.DotType.STATIC_DOT);
                    }
                    mLineChart2.getData().add(dot);
                }
                mLineChart2.postInvalidate();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
    }
}
