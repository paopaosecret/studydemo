package com.xbing.com.viewdemo.ui.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.utils.DpPxUtil;

public class FloatService extends Service implements View.OnTouchListener{

    ConstraintLayout constraintLayout;
    WindowManager.LayoutParams params;
    WindowManager windowManager;

    ImageButton imageButton;

    //状态栏高度
    int statusBarHeight = -1;


    //不与activity进行绑定
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或onBind() 之前）。
     * 如果服务已在运行，则不会调用此方法，该方法只调用一次
     */
    @Override
    public void onCreate()
    {
        super.onCreate();
        //OnCreate中来生成悬浮窗.
        Log.d("FloatService","FloatService onCreate()");

    }

    /**
     * 当另一个组件（如 Activity）通过调用 startService() 请求启动服务时，系统将调用此方法。一旦执行此方法，
     * 服务即会启动并可在后台无限期运行。 如果自己实现此方法，则需要在服务工作完成后，
     * 通过调用 stopSelf() 或 stopService() 来停止服务。（在绑定状态下，无需实现此方法。）
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("FloatService","FloatService onStartCommand()");
        addFloatView();
        return super.onStartCommand(intent, flags, startId);
    }




    private void addFloatView() {
        Log.d("FloatService","FloatService addFloatView()");
        if(windowManager == null){
            Log.d("FloatService","FloatService windowManager == null");
            windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        }
        if(params == null){
            Log.d("FloatService","FloatService params == null");
            //赋值WindowManager&LayoutParam.
            params = new WindowManager.LayoutParams();
            //设置type.系统提示型窗口，一般都在应用程序窗口之上.
            params.type = WindowManager.LayoutParams.TYPE_TOAST;
            //设置效果为背景透明.
            params.format = PixelFormat.RGBA_8888;
            //设置flags.不可聚焦及不可使用按钮对悬浮窗进行操控.
            params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

            //设置窗口初始停靠位置.
            params.gravity = Gravity.LEFT | Gravity.TOP;
            params.x = 0;
            params.y = 0;

            //设置悬浮窗口长宽数据.
            //注意，这里的width和height均使用px而非dp.这里我偷了个懒
            //如果你想完全对应布局设置，需要先获取到机器的dpi
            //px与dp的换算为px = dp * (dpi / 160).
            params.width = DpPxUtil.dip2px(this, 50);
            params.height = DpPxUtil.dip2px(this, 50);
        }
        if(constraintLayout == null){
            Log.d("FloatService","FloatService constraintLayout == null");
            LayoutInflater inflater = LayoutInflater.from(getApplication());
            //获取浮动窗口视图所在布局.
            constraintLayout = (ConstraintLayout) inflater.inflate(R.layout.float_window, null);
            //添加toucherlayout
            windowManager.addView(constraintLayout,params);
        }
        //主动计算出当前View的宽高信息.
        constraintLayout.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
        //用于检测状态栏高度.
        int resourceId = getResources().getIdentifier("status_bar_height","dimen","android");
        if (resourceId > 0)
        {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            Log.d("FloatService","FloatService statusBarHeight =" + statusBarHeight);
        }

        if(imageButton == null){
            Log.d("FloatService","FloatService statusBarHeight =" + statusBarHeight);
            //浮动窗口按钮.
            imageButton = constraintLayout.findViewById(R.id.imageButton1);
            imageButton.setImageResource(R.drawable.ic_launcher);
            imageButton.setOnTouchListener(this);
        }
    }

    @Override
    public void onDestroy() {
        Log.d("FloatService", "onDestroy");
        windowManager.removeViewImmediate(constraintLayout);
        super.onDestroy();
    }

    int index = 0;
    void onClick(){
        Log.d("FloatService", "onClick: " + ++index);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                //ImageButton我放在了布局中心，布局一共300dp
                params.x = (int)event.getRawX();
                //这就是状态栏偏移量用的地方
                params.y = (int) event.getRawY() - DpPxUtil.dip2px(FloatService.this, 50);
                windowManager.updateViewLayout(constraintLayout,params);
                break;

            case MotionEvent.ACTION_UP:
                if(event.getRawX() < windowManager.getDefaultDisplay().getWidth()/2){
                    params.x = 0;
                }else{
                    params.x =  windowManager.getDefaultDisplay().getWidth() - DpPxUtil.dip2px(FloatService.this, 50);
                }

                //这就是状态栏偏移量用的地方
                params.y = (int) event.getRawY() - DpPxUtil.dip2px(FloatService.this, 50);
                windowManager.updateViewLayout(constraintLayout,params);
                onClick();
                break;


        }

        return false;
    }
}
