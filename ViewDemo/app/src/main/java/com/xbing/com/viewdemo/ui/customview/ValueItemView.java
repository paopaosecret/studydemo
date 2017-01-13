package com.xbing.com.viewdemo.ui.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.service.utils.Utils;

/**
 * Created by bing.zhao on 2016/12/26.
 */

public class ValueItemView extends View {

    private Context mContext;

    private float mBgProgressValue;  //background 值
    private float mFgProgressValue;   //foreground 值

    private float mWidth;

    private float mHeight;

    private String mText="seq";

    private Paint mBgPaint;
    private Paint mFgPaint;

    private Paint mTextPaint;

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }



    public float getmFgProgressValue() {
        return mFgProgressValue;
    }

    public void setmFgProgressValue(float mFgProgressValue) {
        this.mFgProgressValue = mFgProgressValue;
    }

    public float getmBgProgressValue() {
        return mBgProgressValue;
    }

    public void setmBgProgressValue(float mBgProgressValue) {
        this.mBgProgressValue = mBgProgressValue;
    }



    public ValueItemView(Context context) {
        this(context,null);

    }

    public ValueItemView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ValueItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mBgPaint = new Paint();
        mFgPaint = new Paint();
        mTextPaint = new Paint();

        mBgPaint.setAntiAlias(true);
        mFgPaint.setAntiAlias(true);
        mTextPaint.setAntiAlias(true);

        mBgPaint.setColor(getResources().getColor(R.color.hor_bg));
        mFgPaint.setColor(getResources().getColor(R.color.hor_fg));
        mTextPaint.setColor(getResources().getColor(R.color.hor_txt));
        mTextPaint.setTextSize(Utils.sp2px(getContext(),14));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = this.getMeasuredWidth();
        mHeight = this.getMeasuredHeight()-Utils.dip2Px(getContext(),30.0f);

        RectF bgRect = new RectF(mWidth/4.0f*1.5f,mHeight*(1.0f-getmBgProgressValue()),mWidth/4.0f*2.5f,mHeight);
        RectF fgRect = new RectF(mWidth/4.0f*1.5f,mHeight*(1.0f-getmFgProgressValue()),mWidth/4.0f*2.5f,mHeight);

        canvas.drawRoundRect(bgRect,mWidth/8.0f,mWidth/8.0f,mBgPaint);
        canvas.drawRoundRect(fgRect,mWidth/8.0f,mWidth/8.0f,mFgPaint);
        canvas.drawText(mText,mWidth*0.5f,this.getMeasuredHeight()-Utils.dip2Px(getContext(),10.0f),mTextPaint);
    }

    public void setValue(float bgValue, float fgValue, String txt){
        setmBgProgressValue(bgValue);
        setmFgProgressValue(fgValue);
        setmText(txt);
        invalidate();
    }
}
