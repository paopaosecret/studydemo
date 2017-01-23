package com.xbing.com.viewdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.service.MyService;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_luan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.btn_luan:

                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this,LuanActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;
                }
            }
        });

        findViewById(R.id.btn_animator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,AnimatorActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_param).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ParamActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_notify).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,NotifyActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_image).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ImageActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        findViewById(R.id.btn_twolist).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,TwoListActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_linechart).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,LineChartActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_chart).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ChartActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_db).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,DBActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_bluetooth).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,BluetoothActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_pickerview).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,PickViewActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_zip).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ZipActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_service).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ServiceActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_stop_service).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
            }
        });


        findViewById(R.id.btn_fragment).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,FragActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_gif).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,GitActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,JniTestActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_opengl_es).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,HelloOpenGlEsActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_rly).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,RLYActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }

}
