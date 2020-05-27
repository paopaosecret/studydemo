package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.service.FloatService;

public class FloatActivity  extends Activity implements View.OnClickListener{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float);

        findViewById(R.id.btn_start_float_cus).setOnClickListener(this);
        findViewById(R.id.btn_stop_float_cus).setOnClickListener(this);
//        findViewById(R.id.btn_start_float_thr).setOnClickListener(this);
//        findViewById(R.id.btn_stop_float_thr).setOnClickListener(this);
    }

    private void startFloatCus() {
        if (Build.VERSION.SDK_INT >= 23) {
            Log.d("FloatActivity","FloatService application init1");
            if (Settings.canDrawOverlays(this)) {
                Log.d("FloatActivity","FloatService application init2");
                Intent intent = new Intent(this, FloatService.class);
                startService(intent);
            } else {
                Log.d("FloatActivity","FloatService application init3");
                //若没有权限，提示获取.
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(intent);
            }
        } else {
            Log.d("FloatActivity","FloatService application init4");
            //SDK在23以下，不用管.
            Intent intent = new Intent(this, FloatService.class);
            startService(intent);
        }
    }

    public void stopFloatCus(){
        Log.d("FloatActivity","stop FloatService application init4");
        Intent intent = new Intent(this,FloatService.class);
        stopService(intent);
    }
//
//    ViewStateListener mViewStateListener = new ViewStateListener() {
//        @Override
//        public void onPositionUpdate(int i, int i1) {
//            Log.d("FloatActivity","onPositionUpdate: i = " + i + ",i1 = " + i1);
//        }
//
//        @Override
//        public void onShow() {
//            Log.d("FloatActivity","onShow ");
//        }
//
//        @Override
//        public void onHide() {
//            Log.d("FloatActivity","onHide ");
//        }
//
//        @Override
//        public void onDismiss() {
//            Log.d("FloatActivity","onDismiss ");
//        }
//
//        @Override
//        public void onMoveAnimStart() {
//            Log.d("FloatActivity","onMoveAnimStart ");
//        }
//
//        @Override
//        public void onMoveAnimEnd() {
//            Log.d("FloatActivity","onMoveAnimEnd ");
//        }
//
//        @Override
//        public void onBackToDesktop() {
//            Log.d("FloatActivity","onBackToDesktop ");
//        }
//    };
//
//    PermissionListener mPermissionListener = new PermissionListener() {
//        @Override
//        public void onSuccess() {
//            Log.d("FloatActivity","onSuccess ");
//        }
//
//        @Override
//        public void onFail() {
//            Log.d("FloatActivity","onFail ");
//        }
//    };
//    private void startFloatThr() {
//        ImageView imageView = new ImageView(getApplicationContext());
//        imageView.setImageResource(R.drawable.ic_launcher);
//        FloatWindow
//                .with(getApplicationContext())
//                .setView(imageView)
//                .setWidth(DpPxUtil.dip2px(this,50))                               //设置控件宽高
//                .setHeight(DpPxUtil.dip2px(this,50))
//                .setX(100)                                   //设置控件初始位置
//                .setY(Screen.height,0.3f)
//                .setMoveType(MoveType.slide,100,-100)
//                .setMoveStyle(500, new BounceInterpolator())
//                .setDesktopShow(true)                        //桌面显示
//                .setViewStateListener(mViewStateListener)    //监听悬浮控件状态改变
//                .setPermissionListener(mPermissionListener)  //监听权限申请结果
//                .build();
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(FloatActivity.this, "onClick", Toast.LENGTH_SHORT).show();
//            }
//        });
//        //手动控制
//        FloatWindow.get().show();
//    }
//
//    public void stopFloatThr(){
//        FloatWindow.get().hide();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_float_cus:
                startFloatCus();
                break;

            case R.id.btn_stop_float_cus:
                stopFloatCus();
                break;
        }

    }
}
