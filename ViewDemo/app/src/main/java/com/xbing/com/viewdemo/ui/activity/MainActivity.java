package com.xbing.com.viewdemo.ui.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.keeplive.KeepLiveManager;
import com.xbing.com.viewdemo.maidian.Listener;
import com.xbing.com.viewdemo.moudle.eventdispatch.DispatchActivity;
import com.xbing.com.viewdemo.moudle.map.MapActivity;
import com.xbing.com.viewdemo.moudle.mvp.activity.LoginActivity;
import com.xbing.com.viewdemo.moudle.rx.RxActivity;
import com.xbing.com.viewdemo.service.cpsp.TestSharedPreferences;
import com.xbing.com.viewdemo.ui.activity.twolabel.CommandActivity;
import com.xbing.com.viewdemo.ui.customview.draglistview.DragListActivity;
import com.xbing.com.viewdemo.ui.service.MyService;
import com.xbing.com.viewdemo.ui.service.NeNotificationService;

import java.util.List;


public class MainActivity extends AppCompatActivity{


    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeepLiveManager.getOutInstance().unRegisterReciver();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KeepLiveManager.getOutInstance().registerReciver();
        findViewById(R.id.btn_luan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestSharedPreferences.getInstance(MainActivity.this).putString("name","zhangsan");
                String str = TestSharedPreferences.getInstance(MainActivity.this).getString("name","null");
                Log.i("str","str:" + str);
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

        findViewById(R.id.btn_recyclerview).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,RecyclerViewActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_wuziqi).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,WuziqiActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_drawer).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,DrawerActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_swip_menu_list_view).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SwipMenuActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_rx).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,RxActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_login).setOnClickListener(new Listener.OnClickListener(){
            @Override
            public void onClick(View view) {
                super.onClick(view);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,QQLoginActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_dispatch).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,DispatchActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_dragview).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,DragListActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_gallery).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,GalleryActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_life_cycle).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();          //隐式调用需要在Intent-filterh中定义<category android:name="android.intent.category.DEFAULT" />
                intent.setDataAndType(Uri.parse("file://abc"),"text/plain");            //Data匹配：如果Intent-filter中定义了，则Intent中至少需要匹配一个
                intent.setAction("android.intent.action.lifecycle1");//Action匹配       //必须有,Intent中至少需要匹配一个，如果Intent没有设置，则可能匹配多个data的mineType应用，让用户选择
                intent.addCategory("lifecycle2");                                       //可有可无，如果有，则需要匹配
                MainActivity.this.startActivity(intent);
            }
        });
        findViewById(R.id.btn_map).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,MapActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_bookService).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,TestBookSerivceActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        findViewById(R.id.btn_javajs_interactive).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,JavaJsInteractiveActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_mvp_login).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,LoginActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_viewpager).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ViewPagerActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_popupwindow).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,PopupWindowActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_floatwindow).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,FloatActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_command).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                gotoCommand();
            }
        });
        updateServiceStatus(true);

//        getInstallData();
//        OpenInstall.getWakeUp(getIntent(), wakeUpAdapter);
//        initFloatService();
    }

    private void gotoCommand() {
        Intent intent = new Intent(MainActivity.this, CommandActivity.class);
        startActivity(intent);
    }


    //这里防止多次启动服务，所以先判断服务是否在运行中
    private void updateServiceStatus(boolean start)
    {
        Intent upservice = new Intent(this, NeNotificationService.class);
        boolean bRunning = isServiceWork(this, "com.xbing.com.viewdemo.ui.service.NeNotificationService");

        //没有Running则启动
        if (start && !bRunning) {
            Log.i("NeNotificationService","start service");
            this.startService(upservice);
        } else if(!start && bRunning) {
            Log.i("NeNotificationService","stop service");
            this.stopService(upservice);
        }

    }

    /**
     * 判断某个服务是否正在运行的方法
     *
     * @param mContext
     * @param serviceName
     *            是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
     * @return true代表正在运行，false代表服务没有正在运行
     */
    public boolean isServiceWork(Context mContext, String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(40);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }



}
