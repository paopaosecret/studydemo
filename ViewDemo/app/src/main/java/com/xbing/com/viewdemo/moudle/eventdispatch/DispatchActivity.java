package com.xbing.com.viewdemo.moudle.eventdispatch;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xbing.com.viewdemo.R;

/**
 * Created by bing.zhao on 2017/4/20.
 *
 * View的事件分发:
 * dispatchTouchEvent()      ----->
 * mTouchListener.onTouch()  ----->   return false继续    true:结束
 * View.onTouchEvent()       ----->
 * Action_DOWN               ----->   postDelay_onlongClick   return true:可以继续接收action  false:后续的action不会再执行
 * Action_MOVE               ----->
 * Action_UP                 ----->   performClick()
 */

public class DispatchActivity extends Activity {

    private static final String TAG = DispatchActivity.class.getSimpleName();
    Button mBtnClick;
    LinearLayout mViewGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);

        mViewGroup = (LinearLayout) findViewById(R.id.ll_viewgroup);
        mViewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "view group onTouch execute, action " + event.getAction());
                return false;
            }
        });



        mBtnClick = (Button) findViewById(R.id.btn_click);
        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick execute");
            }
        });

        mBtnClick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i(TAG, "onLongClick execute");
                return false;
            }
        });

        mBtnClick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "onTouch execute, action " + event.getAction());
                return false;
            }
        });

        View view = new View(DispatchActivity.this);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "view onTouch execute, action " + event.getAction());
                return false;
            }
        });
        mBtnClick.setTouchDelegate(new TouchDelegate(new Rect(0,0,200,200), view));

    }
}
