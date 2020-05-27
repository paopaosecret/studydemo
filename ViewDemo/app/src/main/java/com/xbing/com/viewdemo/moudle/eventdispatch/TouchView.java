package com.xbing.com.viewdemo.moudle.eventdispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;

/**
 * Created by bing.zhao on 2017/4/20.
 */

public class TouchView extends View {

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("DispatchActivity", "onTouch ACTION_DOWN execute, action " + event.getAction());
                break;

            case MotionEvent.ACTION_MOVE:
                Log.i("DispatchActivity", "onTouch ACTION_MOVE execute, action " + event.getAction());
                break;

            case MotionEvent.ACTION_UP:
                Log.i("DispatchActivity", "onTouch up execute, action " + event.getAction());
                break;
        }
        return false;
    }
}
