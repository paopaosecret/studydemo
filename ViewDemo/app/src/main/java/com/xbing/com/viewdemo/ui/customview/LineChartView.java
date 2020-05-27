package com.xbing.com.viewdemo.ui.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.xbing.com.viewdemo.service.utils.Utils;
import com.xbing.com.viewdemo.ui.customview.entity.Dot;

import java.util.ArrayList;
import java.util.List;

//import com.facebook.stetho.common.LogUtil;

/**
 * Created by zhaobing on 2016/8/15.
 */
public class LineChartView extends View {

    private int xPoint = 60;    //坐标原点x
    private int yPoint = 200;   //坐标原点y

    private int xScale = 12;     //x间隔
    private int yScale = 30;    //y间隔

    private int xLength = 240;  //x长度
    private int yLength = 180;  //y长度

    public static enum Linestyle
    {
        Line, Curve
    }

    public void setmStyle(Linestyle mStyle) {
        this.mStyle = mStyle;
    }

    private Linestyle mStyle = Linestyle.Curve;


    public int getxMaxCount() {
        return xMaxCount;
    }

    public int getyMaxCount() {
        return yMaxCount;
    }

    private int xMaxCount = xLength / xScale;   //x轴刻度最大个数
    private int yMaxCount = yLength / yScale;   //Y轴刻度最大个数

    private String[] yLabel = new String[yMaxCount];
    private List<Dot> mData = new ArrayList<Dot>();


    public LineChartView(Context context) {
        super(context);
        init(context);

    }

    public void init(Context context){
        xPoint = Utils.dip2Px(context,60);
        yPoint = Utils.dip2Px(context,200);

        xScale = Utils.dip2Px(context,12);
        yScale = Utils.dip2Px(context,30);

        xLength = Utils.dip2Px(context,240);
        yLength = Utils.dip2Px(context,180);
        for(int i = 0; i< yMaxCount; i++){
            yLabel[i] = (i+1)+"M/S";
        }
    }

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    //保存刚开始按下的点
    private PointF startPoinF = new PointF();
    //旋转和缩放的中点
    private PointF midP = new PointF();

    private Float startDistances = 0f;

    private Matrix matrix = new Matrix();

    private long i = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();
        switch (eventaction & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: // touch down so check if the
                float event_x = (int) event.getX();
                float event_y = (int) event.getY();
                startPoinF.set(event_x, event_y);// 保存刚开始按下的坐标
                Log.i("onTouchEvent","event_x:" + event_x + "，event_y:" + event_y);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                float x = (int) event.getX() - startPoinF.x;
                float y = (int) event.getY() - startPoinF.y;
//                startDistances = (float) Math.sqrt(x * x + y * y);
                Log.i("onTouchEvent","event_x1:" + event.getX() + "，event_y1:" + event.getX() + "，startDistances：" + startDistances);
                break;

            case MotionEvent.ACTION_MOVE: // touch drag with the ball
                //如果是双指点中
                if(++i % 4 == 0){
                    if (event.getPointerCount() == 2) {
                        float x1 = event.getX(0) - event.getX(1);
                        float y1 = event.getY(0) - event.getY(1);
                        float endDistances = (float) Math.sqrt(x1 * x1 + y1 * y1);// 计算两点的距离
                        if(startDistances == 0){
                            startDistances = endDistances;
                        }else{
                            if (Math.abs(endDistances - startDistances) >= 10) {
                                float scale = (endDistances+320)/(startDistances+320);
                                startDistances = 0f;
                                matrix.postScale(scale,scale);
                                Log.i("onTouchEvent","startDistances:" + startDistances + "，endDistances:" + endDistances + ",scale:" + scale);
                                postInvalidate();
                            }

                        }

                    }else{
                        float ex = event.getX();
                        float ey = event.getY();
                        float x1 = ex - startPoinF.x;
                        float y1 = ey - startPoinF.y;
                        startPoinF.set(ex,ey);
                        matrix.postTranslate(x1,y1);
                        postInvalidate();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                startDistances = 0f;
                break;
        }
        return true;
    }



    public List<Dot> getData(){
        return mData;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(matrix != null){
            this.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            canvas.concat(matrix);
            this.setLayerType(View.LAYER_TYPE_NONE, null);
        }



        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);

        drawAxis(canvas, paint);
        drawScrollLine(canvas, paint);
    }

    private void drawAxis(Canvas canvas, Paint paint){
        //画Y轴
        canvas.drawLine(xPoint, yPoint - yLength, xPoint, yPoint, paint);

        //Y轴箭头
        canvas.drawLine(xPoint, yPoint - yLength, xPoint - 3, yPoint - yLength + 6, paint);
        canvas.drawLine(xPoint, yPoint - yLength, xPoint + 3, yPoint - yLength + 6, paint);

        //添加Y轴刻度和文字
        for(int i = 0; i< yMaxCount; i++){
            canvas.drawLine(xPoint,yPoint-i*yScale,xPoint+5,yPoint-i*yScale,paint);
            canvas.drawText(yLabel[i],xPoint-50,yPoint-i*yScale,paint);
        }

        //画X轴
        canvas.drawLine(xPoint,yPoint,xPoint+xLength,yPoint,paint);
        //画X轴箭头
        canvas.drawLine(xPoint+xLength,yPoint,xPoint+xLength-6,yPoint-3,paint);
        canvas.drawLine(xPoint+xLength,yPoint,xPoint+xLength-6,yPoint+3,paint);

        //添加X轴刻度和文字
        for(int i = 0; i< xMaxCount; i++){
            canvas.drawLine(xPoint+i*xScale,yPoint,xPoint+i*xScale,yPoint-5,paint);
        }
    }

    private void drawScrollLine(Canvas canvas, Paint mPaint)
    {
        Point mStartPoint = new Point();          //记录每段曲线的开始点
        Point mEmdPoint = new Point();           //记录每段曲线的结束点
        Point mStartControlPoint = new Point(); //绘制曲线时控制点1
        Point mEndControlPoint = new Point();  //绘制曲线时控制点2
        Path  mLinePath = new Path();         //绘制曲线时使用的绘制路径

        for (int i = 1; i < getData().size(); i++)
        {
            mStartPoint.set(xPoint + (i - 1) * xScale, yPoint - mData.get(i - 1).getValue() * yScale);
            mEmdPoint.set(xPoint + i * xScale, yPoint - mData.get(i).getValue() * yScale);
//            if(i == 1){
                mLinePath.moveTo(mStartPoint.x, mStartPoint.y);
//            }
            if(mStyle == Linestyle.Curve){
                int wt = (mStartPoint.x + mEmdPoint.x) / 2;
                mStartControlPoint.y = mStartPoint.y;
                mStartControlPoint.x = wt;
                mEndControlPoint.y = mEmdPoint.y;
                mEndControlPoint.x = wt;
                mLinePath.cubicTo(mStartControlPoint.x,mStartControlPoint.y,mEndControlPoint.x,mEndControlPoint.y,mEmdPoint.x,mEmdPoint.y);  //其中p3和p4是控制点

                int colorStart = getColorByPosition(mStartPoint.y);
                int colorEnd = getColorByPosition(mEmdPoint.y);
                Shader mShader = new LinearGradient(mStartPoint.x,mStartPoint.y, mEmdPoint.x,mEmdPoint.y,
                        new int[]{colorStart, colorEnd}, null, Shader.TileMode.REPEAT);
                mPaint.setShader(mShader);
//                canvas.drawPath(mLinePath, mPaint);
                canvas.drawLine(mStartPoint.x,mStartPoint.y,mEmdPoint.x,mEmdPoint.y,mPaint);
            }else{
                mLinePath.lineTo(mEmdPoint.x, mEmdPoint.y);
                int colorStart = getColorByPosition(mStartPoint.y);
                int colorEnd = getColorByPosition(mEmdPoint.y);
                Shader mShader = new LinearGradient(mStartPoint.x,mStartPoint.y, mEmdPoint.x,mEmdPoint.y,
                        new int[]{colorStart, colorEnd}, null, Shader.TileMode.REPEAT);
                mPaint.setShader(mShader);
                canvas.drawPath(mLinePath, mPaint);
            }

        }
//        int colorStart = getColorByPosition(mStartPoint.y);
//        int colorEnd = getColorByPosition(mEmdPoint.y);
//        Shader mShader = new LinearGradient(mStartPoint.x,mStartPoint.y, mEmdPoint.x,mEmdPoint.y,
//                new int[]{colorStart, colorEnd}, null, Shader.TileMode.REPEAT);
//        mPaint.setShader(mShader);
//        canvas.drawPath(mLinePath, mPaint);
    }

    /**
     * 160：最小点Y值
     * 180：最大点Y的值减去最小点Y的值（340-160）
     * @param yPos
     * @return
     */
    protected int getColorByPosition(int yPos){
        yPos = yPos <= 160 ? 160 : yPos;
        int yColor = Color.rgb(255,(int) (yPos-160)*255/180,0);
        return yColor;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthMeasureSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.i("onMeasure","widthMeasureMode = " + widthMeasureMode + ",widthMeasureSize = " + widthMeasureSize);
        Log.i("onMeasure","heightMeasureMode = " + heightMeasureMode + ",heightMeasureSize = " + heightMeasureSize);
        if(widthMeasureMode == MeasureSpec.AT_MOST){
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(widthMeasureSize,MeasureSpec.EXACTLY);
        }
        if(heightMeasureMode == MeasureSpec.AT_MOST){
            heightMeasureSpec = MeasureSpec.makeMeasureSpec((int)(widthMeasureSize*0.65),MeasureSpec.EXACTLY);
        }  //自定义View，让wrap_content 起作用，必须给其设置一个默认的值，否则是Match_parent的效果
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }
}
