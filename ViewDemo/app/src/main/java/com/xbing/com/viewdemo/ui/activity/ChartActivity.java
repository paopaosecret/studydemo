package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.activity.twolabel.ShareGridviewActivity;
import com.xbing.com.viewdemo.ui.activity.twolabel.HorBarActivity;
import com.xbing.com.viewdemo.ui.activity.twolabel.HorListViewActivity;
import com.xbing.com.viewdemo.ui.activity.twolabel.LineActivity;

/**
 * Created by zhaobing on 2016/8/22.
 */
public class ChartActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_activity);

        findViewById(R.id.btn_mp_line).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ChartActivity.this,LineActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_mp_hor_bar).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ChartActivity.this,HorBarActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_gridview).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ChartActivity.this,ShareGridviewActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_hor_listview).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(ChartActivity.this,HorListViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
