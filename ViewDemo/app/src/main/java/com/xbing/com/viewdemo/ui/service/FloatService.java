package com.xbing.com.viewdemo.ui.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.utils.DpPxUtil;

public class FloatService extends Service{

    FrameLayout mLayout;
    WindowManager.LayoutParams params;
    WindowManager windowManager;

    TextView tv_title;

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

            //设置效果为背景透明.
            params.format = PixelFormat.RGBA_8888;
            // 当悬浮窗显示的时候可以获取到焦点
            params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR
                    | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

            // 需要适配 8.0，当 8.0 以上的版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            } else {
                params.type = WindowManager.LayoutParams.TYPE_PHONE;
            }

            //设置窗口初始停靠位置.
            params.gravity = Gravity.LEFT | Gravity.TOP;

            params.x = 0;

            params.y = 0;

            //设置悬浮窗口长宽数据.
            params.width = DpPxUtil.dip2px(this, windowManager.getDefaultDisplay().getWidth());
            params.height = DpPxUtil.dip2px(this, 69);
        }
        if(mLayout == null){
            Log.d("FloatService","FloatService mLayout == null");
            LayoutInflater inflater = LayoutInflater.from(getApplication());
            //获取浮动窗口视图所在布局.
            mLayout = (FrameLayout) inflater.inflate(R.layout.float_window, null);
            //添加toucherlayout
            windowManager.addView(mLayout,params);
        }
        //主动计算出当前View的宽高信息.
        mLayout.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
        //用于检测状态栏高度.
        int resourceId = getResources().getIdentifier("status_bar_height","dimen","android");
        if (resourceId > 0)
        {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            Log.d("FloatService","FloatService statusBarHeight =" + statusBarHeight);
        }

        if(tv_title == null){
            Log.d("FloatService","FloatService statusBarHeight =" + statusBarHeight);
            //浮动窗口按钮.
            tv_title = mLayout.findViewById(R.id.tv_title);
            tv_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FloatService.this,"跳转到接单页面", Toast.LENGTH_SHORT).show();
                }
            });
        }

        try {
            windowManager.updateViewLayout(mLayout, params);
        } catch (IllegalArgumentException e) {
            windowManager.addView(mLayout, params);
        }
    }

    @Override
    public void onDestroy() {
        Log.d("FloatService", "onDestroy");
        windowManager.removeViewImmediate(mLayout);
        super.onDestroy();
    }
}
