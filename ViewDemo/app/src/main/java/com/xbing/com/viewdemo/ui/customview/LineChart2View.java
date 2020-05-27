package com.xbing.com.viewdemo.ui.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.service.utils.Utils;
import com.xbing.com.viewdemo.ui.customview.entity.Dot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bing.zhao on 2017/5/16.
 */
public class LineChart2View extends View implements ScaleGestureDetector.OnScaleGestureListener{

    private static  final String TAG = LineChart2View.class.getSimpleName();

    private int xPoint = 60;    //坐标原点x

    public OnPositonChangeListener getmPositionChangeListener() {
        return mPositionChangeListener;
    }

    public void setmPositionChangeListener(OnPositonChangeListener mPositionChangeListener) {
        this.mPositionChangeListener = mPositionChangeListener;
    }

    private OnPositonChangeListener mPositionChangeListener;

    public int getxPoint() {
        return xPoint;
    }

    public void setxPoint(int xPoint) {
        this.xPoint = xPoint;
    }

    public int getyPoint() {
        return yPoint;
    }

    public void setyPoint(int yPoint) {
        this.yPoint = yPoint;
    }

    public int getmViewWidth() {
        return mViewWidth;
    }

    public void setmViewWidth(int mViewWidth) {
        this.mViewWidth = mViewWidth;
    }

    private int yPoint = 250;   //坐标原点y

    private float xScale = 12;     //x间隔
    private float yScale = 60;    //y间隔

    private int xLength = 240;  //x长度
    private int yLength = 240;  //y长度

    private int mFlagPosition = 0;
    private int mFlagHeartRate = 0;
    private int mFlagX = xPoint;

    private String startTime = "07:00";
    private String endTime   = "08:00";

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        return false;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return false;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    public static enum Linestyle
    {
        Line, Curve
    }

    public String[] levelArray = {
            "level1","level2","level3","level4"
    };

    public void setmStyle(LineChart2View.Linestyle mStyle) {
        this.mStyle = mStyle;
    }

    private LineChart2View.Linestyle mStyle = LineChart2View.Linestyle.Curve;


    public int getxMaxCount() {
        return xMaxCount;
    }

    public int getyMaxCount() {
        return yMaxCount;
    }

    private int xMaxCount = 120;   //x轴刻度最大个数
    private int yMaxCount = 120;   //Y轴刻度最大个数

    private String[] yLabel = new String[]{"60","116","135","172","180"};
    private int[] yLevel = new int[]{60,116,135,172,180};
    private int[] yLevelSrc = new int[]{60,116,135,172,180};
    private List<Dot> mData = new ArrayList<Dot>();
    private Map<String,Paint> mPaintMap = new HashMap<>();

    private int mViewWidth = 320;
    private int mViewHeight = 260;
    private Context mContext;

    private long mFilterCount = 0;

    public LineChart2View(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mViewWidth  = right - left;
        mViewHeight = bottom - top;
        Log.i(TAG,"mViewWidth = " + mViewWidth + ",mViewHeight = " + mViewHeight );
        init(this.getContext());
        initPaint(this.getContext());

    }

    public void init(Context context){
        xPoint = Utils.dip2Px(context,30);
        mFlagX = xPoint;
        yPoint = (int)(mViewHeight) - Utils.dip2Px(context,40);
        xLength = mViewWidth * 3 - Utils.dip2Px(context,40);
        yLength = (int)(mViewHeight) - Utils.dip2Px(context,100);
        yScale = (float)(yLength / (yMaxCount* 1.0));
        xScale = (float)(xLength / (xMaxCount* 1.0));
        for(int i = 0; i< yLevel.length; i++){
            yLevel[i] = yPoint - (int)((yLevelSrc[i] - 60) * yScale);
            Log.i(TAG,"yLEvel" + i + " = " + yLevel[i] + ",yponit = " + yPoint +  ",yScale =" + yScale + ",yLength =" + yLength);
        }
    }

    private void initPaint(Context context) {
        mPaintMap.clear();
        for(String str : levelArray){
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(Utils.dip2Px(context,2));
            if(str.equals("level1")){
                paint.setColor(getResources().getColor(R.color.fat_burn));
            }else if(str.equals("level2")){
                paint.setColor(getResources().getColor(R.color.fat_high));
            }else if(str.equals("level3")){
                paint.setColor(getResources().getColor(R.color.cardio));
            }else {
                paint.setColor(getResources().getColor(R.color.peak));
            }
            mPaintMap.put(str,paint);
        }
    }

    public LineChart2View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public List<Dot> getData(){
        return mData;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(matrix != null){
//            this.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            canvas.concat(matrix);
//            this.setLayerType(View.LAYER_TYPE_NONE, null);
        }
        long start = SystemClock.currentThreadTimeMillis();
//        drawFlag(canvas);
        drawAxis(canvas);
        drawScrollLine(canvas, mPaintMap);
        long end = SystemClock.currentThreadTimeMillis();
        Log.i(TAG,"ondraw_timeLength = " + (end-start));
    }

    /**
     * 画标记
     * @param canvas
     */
    private void drawFlag(Canvas canvas) {
        if(mFlagX == xPoint){
            mFlagPosition = 0;
            mFlagHeartRate = mData.get(0).getValue();
        }
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.chart_line));
        canvas.drawLine(mFlagX,yPoint,mFlagX,Utils.dip2Px(this.getContext(),20),paint);
        RectF r2 = new RectF();
        if(mFlagX < mViewWidth / 2){
            r2.left = mFlagX;
            r2.right = mFlagX + Utils.dip2Px(this.getContext(),80);
            r2.top = 1;
            r2.bottom = Utils.dip2Px(this.getContext(),30);
            paint.setColor(getResources().getColor(R.color.gray_9));
            canvas.drawRoundRect(r2, Utils.dip2Px(this.getContext(),5), Utils.dip2Px(this.getContext(),5), paint);

            paint.setTextAlign(Paint.Align.CENTER);
            paint.setColor(getResources().getColor(R.color.white));
            paint.setTextSize(getResources().getDimension(R.dimen.sp_14));
            canvas.drawText(mFlagHeartRate + "bmp",r2.left+Utils.dip2Px(this.getContext(),40), Utils.dip2Px(this.getContext(),15),paint);
            paint.setTextSize(getResources().getDimension(R.dimen.sp_10));
            canvas.drawText(mFlagPosition + "",r2.left+Utils.dip2Px(this.getContext(),40), Utils.dip2Px(this.getContext(),27),paint);
        }else{
            r2.left = mFlagX - Utils.dip2Px(this.getContext(),80);
            r2.right = mFlagX;
            r2.top = 1;
            r2.bottom = Utils.dip2Px(this.getContext(),30);
            paint.setColor(getResources().getColor(R.color.gray_9));
            canvas.drawRoundRect(r2, Utils.dip2Px(this.getContext(),5), Utils.dip2Px(this.getContext(),5), paint);

            paint.setTextAlign(Paint.Align.CENTER);
            paint.setColor(getResources().getColor(R.color.white));
            paint.setTextSize(getResources().getDimension(R.dimen.sp_14));
            canvas.drawText(mFlagHeartRate + "bmp",r2.left + Utils.dip2Px(this.getContext(),40), Utils.dip2Px(this.getContext(),15),paint);
            paint.setTextSize(getResources().getDimension(R.dimen.sp_10));
            canvas.drawText(mFlagPosition + "",r2.left + Utils.dip2Px(this.getContext(),40), Utils.dip2Px(this.getContext(),27),paint);
        }
    }


    private void drawAxis(Canvas canvas){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.chart_line));
        paint.setTextSize(getResources().getDimension(R.dimen.sp_10));
        //画y轴线
        for(int i = 0; i < yLevel.length; i++){
            canvas.drawLine(xPoint,yLevel[i],xPoint + xLength,yLevel[i],paint);
            canvas.drawText(yLabel[i],xPoint-Utils.dip2Px(this.getContext(),25),yLevel[i] + Utils.dip2Px(this.getContext(),4),paint);
        }
        //画X轴
        canvas.drawLine(xPoint,yPoint,xPoint+xLength,yPoint,paint);
        paint.setColor(getResources().getColor(R.color.chart_time));
        canvas.drawText(startTime,xPoint-Utils.dip2Px(this.getContext(),15),yLevel[0] + Utils.dip2Px(this.getContext(),12),paint);
        canvas.drawText(endTime,xPoint + xLength - Utils.dip2Px(this.getContext(),15),yLevel[0] + Utils.dip2Px(this.getContext(),12),paint);
    }

    private void drawScrollLine(Canvas canvas, Map<String,Paint> mPaint)
    {
        Point mStartPoint = new Point();          //记录每段曲线的开始点
        Point mEmdPoint = new Point();           //记录每段曲线的结束点
        Paint paint = new Paint();
        for (int i = 0; i < getData().size(); i++)
        {
            Dot curDot = mData.get(i);
            if(curDot.getType() == Dot.DotType.STATIC_DOT || i == 0){
                int x = (int)(xPoint + i * xScale);
                paint.setStyle(Paint.Style.FILL);
                paint.setAntiAlias(true);
                paint.setColor(getResources().getColor(R.color.heart_rate_title));
                canvas.drawCircle(x,convertTureYAxis(mData.get(i).getValue()),4,paint);
                i++;
            }else{
                mStartPoint.set((int)(xPoint + (i - 1) * xScale), convertTureYAxis(mData.get(i - 1).getValue()));
                mEmdPoint.set((int)(xPoint + i * xScale), convertTureYAxis(mData.get(i).getValue()));
                if(isNeedDevidePoint(mStartPoint,mEmdPoint)){
                    Log.i(TAG,"mEmdPoint.y = " + mEmdPoint.y);
                    int levelStart = getPointLevel(mStartPoint);
                    int levelEnd   = getPointLevel(mEmdPoint);
                    if(levelStart < levelEnd){
                        int deviceCount = levelEnd - levelStart;
                        Point[] middle = new Point[deviceCount + 1];
                        for(int j=0; j <=deviceCount; j++ ){
                            int x = mStartPoint.x + (j+1)*(mEmdPoint.x-mStartPoint.x)/(deviceCount+1);
                            int y = getYMaxByLevel(levelStart+j);
                            middle[j] = new Point(x,y);
                            if(j == 0){
                                drawLine(mStartPoint,middle[j],canvas,mPaintMap.get("level" + levelStart));
                            }else if(j == deviceCount){
                                drawLine(middle[j-1],mEmdPoint,canvas,mPaintMap.get("level" + levelEnd));
                            }else{
                                drawLine(middle[j-1],middle[j],canvas,mPaintMap.get("level" + (levelStart+j)));
                            }
                        }
                    }else{
                        int deviceCount = levelStart - levelEnd;
                        Point[] middle = new Point[deviceCount + 1];
                        for(int j=0; j <=deviceCount; j++ ){
                            int x = mStartPoint.x + (j+1)*(mEmdPoint.x-mStartPoint.x)/(deviceCount+1);
                            int y = getYMinByLevel(levelStart-j);
                            middle[j] = new Point(x,y);
                            if(j == 0){
                                drawLine(mStartPoint,middle[j],canvas,mPaintMap.get("level" + levelStart));
                            }else if(j == deviceCount){
                                drawLine(middle[j-1],mEmdPoint,canvas,mPaintMap.get("level" + levelEnd));
                            }else{
                                drawLine(middle[j-1],middle[j],canvas,mPaintMap.get("level" + (levelStart-j)));
                            }
                        }
                    }
                }else{
                    Log.i(TAG,"mEmdPoint.y = " + mEmdPoint.y);
                    drawLine(mStartPoint,mEmdPoint,canvas,mPaintMap.get("level" + getPointLevel(mStartPoint)));
                }
            }

        }

    }

    private int mActionDownX = 0;
    private int mOffsetX = 0;
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                mActionDownX = (int)event.getX();
//                mFlagX = (int)event.getX();
//                if(mFlagX >= xPoint && mFlagX <= xPoint+xLength){
//                    mFlagPosition = (int)((mFlagX - xPoint)/xScale);
//                    if(mFlagPosition >= 0 && mFlagPosition <mData.size()) {
//                        mFlagHeartRate = mData.get(mFlagPosition).getValue();
//                        Log.i(TAG, "position:" + mFlagPosition + ",value:" + mFlagHeartRate);
//                        if(mPositionChangeListener != null){
//                            mPositionChangeListener.onPositionChange(mFlagX,mFlagPosition, mFlagHeartRate);
//                        }
//                    }
//                }
//                LineChart2View.this.setTranslationX(mOffsetX);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                mFilterCount ++;
//                if(mFilterCount%4 == 0){
//                    mFlagX = (int)event.getX();
//                    if(mFlagX >= xPoint && mFlagX <= xPoint+xLength) {
//                        mFlagPosition = (int)((mFlagX - xPoint) / xScale);
//                        Log.i(TAG,"mFlagX = " + mFlagX + ",xPoint = " + xPoint + ",xScale =" + xScale);
//                        if (mFlagPosition >= 0 && mFlagPosition < mData.size()) {
//                            mFlagHeartRate = mData.get(mFlagPosition).getValue();
//                            Log.i(TAG, "position:" + mFlagPosition + ",value:" + mFlagHeartRate);
////                            postInvalidate();
//                            if(mPositionChangeListener != null){
//                                mPositionChangeListener.onPositionChange(mFlagX,mFlagPosition, mFlagHeartRate);
//                            }
//                        }
//                    }
//                }
//
//                int actionMoveX = (int)event.getX();
//                int scrollx = (actionMoveX - mActionDownX);
//                mOffsetX = (int)LineChart2View.this.getTranslationX();
//                Log.i(TAG,"actionMoveX = " + actionMoveX + ",scrollx = " + scrollx + ",mOffsetX = " + mOffsetX);
//                break;
//        }
//        return true;
//    }

    boolean isNeedDevidePoint(Point start, Point end){

        if(getPointLevel(start) == getPointLevel(end)){
            return false;
        }else{
            return true;
        }
    }

    private void drawLine(Point mStartPoint, Point mEmdPoint, Canvas canvas, Paint paint){
        Path mLinePath = new Path();
        Point mStartControlPoint = new Point(); //绘制曲线时控制点1
        Point mEndControlPoint = new Point();  //绘制曲线时控制点2
        mLinePath.moveTo(mStartPoint.x, mStartPoint.y);
        if(mStyle == LineChart2View.Linestyle.Curve){
            int wt = (mStartPoint.x + mEmdPoint.x) / 2;
            mStartControlPoint.y = mStartPoint.y;
            mStartControlPoint.x = wt;
            mEndControlPoint.y = mEmdPoint.y;
            mEndControlPoint.x = wt;
            mLinePath.cubicTo(mStartControlPoint.x,mStartControlPoint.y,mEndControlPoint.x,mEndControlPoint.y,mEmdPoint.x,mEmdPoint.y);  //其中p3和p4是控制点
        }else{
            mLinePath.lineTo(mEmdPoint.x, mEmdPoint.y);
        }
        canvas.drawPath(mLinePath, paint);
    }

    private int getPointLevel(Point point){
        int y = point.y;
        if(y >= yLevel[1]){
            return 1;
        }else if(y >= yLevel[2]){
            return 2;
        }else if(y >= yLevel[3]){
            return 3;
        }else {
            return 4;
        }
    }
    private int getYMinByLevel(int level){
        return yLevel[level-1]+1;
    }

    private int getYMaxByLevel(int level){
        return yLevel[level];
    }

    private int convertTureYAxis(int value){
        int yValue = yPoint - (int)((value - 60) * yScale);
        Log.i(TAG,"value =" +value + ",yvalue = " + yValue + ",ypoint =" + yPoint + ",yScale =" + yScale);
        return yValue;
    }


    //保存刚开始按下的点
    private PointF startPoinF = new PointF();
    //旋转和缩放的中点
    private PointF midP = new PointF();

    private Float startDistances = 0f;

    private Matrix matrix = new Matrix();

    private long i = 0;
    private boolean mIsRepeatActionDown = true;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();
        switch (eventaction & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: // touch down so check if the
                mIsRepeatActionDown = true;
                float event_x = (int) event.getX();
                float event_y = (int) event.getY();
                startPoinF.set(event_x, event_y);// 保存刚开始按下的坐标
                Log.i("onTouchEvent","event_x:" + event_x + "，event_y:" + event_y);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                mIsRepeatActionDown = true;
                float x = (int) event.getX() - startPoinF.x;
                float y = (int) event.getY() - startPoinF.y;
//                startDistances = (float) Math.sqrt(x * x + y * y);
                Log.i("onTouchEvent","event_x1:" + event.getX() + "，event_y1:" + event.getX() + "，startDistances：" + startDistances);
                break;


            case MotionEvent.ACTION_MOVE: // touch drag with the ball
                if(!mIsRepeatActionDown){
                    break;
                }
                //如果是双指点中
//                if(++i % 4 == 0){
                    if (event.getPointerCount() == 2) {
                        float x1 = event.getX(0) - event.getX(1);
                        float y1 = event.getY(0) - event.getY(1);
                        float middleX = (event.getX(0) + event.getX(1))/2;
                        float middleY = (event.getY(0) + event.getY(1))/2;
                        float endDistances = (float) Math.sqrt(x1 * x1 + y1 * y1);// 计算两点的距离
                        if(startDistances == 0){
                            startDistances = endDistances;
                        }else{
                            if (Math.abs(endDistances - startDistances) >= 30) {
                                float scale = (endDistances+320)/(startDistances+320);
                                startDistances = 0f;
                                matrix.postScale(scale,scale,middleX,middleY);
                                Log.i("onTouchEvent","scale->startDistances:" + startDistances + "，endDistances:" + endDistances + ",scale:" + scale);
                                postInvalidate();
                            }

                        }

                    }else{
                        float ex = event.getX();
                        float ey = event.getY();
                        float x1 = ex - startPoinF.x;
                        float y1 = ey - startPoinF.y;
                        float endDistances = (float) Math.sqrt(x1 * x1 + y1 * y1);// 计算两点的距离
                        if(endDistances - startDistances >10){
                            Log.i("onTouchEvent","translate -> startDistances:" + startDistances + "，endDistances:" + endDistances);
                            startPoinF.set(ex,ey);
                            matrix.postTranslate(x1,y1);
                            postInvalidate();
                        }

                    }
//                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                startDistances = 0f;
                mIsRepeatActionDown = false;
                Log.i("onTouchEvent","ACTION_POINTER_UP");
                return false;
            case MotionEvent.ACTION_UP:
                Log.i("onTouchEvent","ACTION_UP");
                startDistances = 0f;
                break;
        }
        return true;
    }

}
