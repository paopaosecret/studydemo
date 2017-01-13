package com.xbing.com.viewdemo.ui.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhaobing on 2016/6/30.
 */
public class AnimalView extends View {
    private float mWidth = 0;
    private float mHeight = 0;
    private Paint mPaint;
    private Context mContext;


    public AnimalView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public AnimalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public AnimalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public void init(){
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float xDelta = mWidth /6;
        float yDelta = mHeight/4;

        for(int i=0;i<5;i++){
            canvas.drawLine(100,100+yDelta*i-1,100+mWidth,100+yDelta*i-1,mPaint);
            for(int j=0;j<7;j++){
                canvas.drawLine(100+xDelta*j,100+yDelta*i,100+xDelta*j,100+yDelta*(i+1),mPaint);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = right - left-200;
        mHeight = bottom - top-100;
    }

    public static void dip2Px(int dip){

    }

}
