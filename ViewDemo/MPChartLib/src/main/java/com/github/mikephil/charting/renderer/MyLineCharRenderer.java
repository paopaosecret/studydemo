package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.Log;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bing.zhao on 2017/8/22.
 */

public class MyLineCharRenderer extends LineChartRenderer {
    public String[] levelArray = {
            "level1","level2","level3","level4"
    };
    public MyLineCharRenderer(LineDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
        mPaintMap.clear();
        for(String str : levelArray){
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setAntiAlias(true);
            paint.setStrokeWidth(4);
            if(str.equals("level1")){
                paint.setColor(Color.RED);
            }else if(str.equals("level2")){
                paint.setColor(Color.YELLOW);
            }else if(str.equals("level3")){
                paint.setColor(Color.GREEN);
            }else {
                paint.setColor(Color.BLUE);
            }
            mPaintMap.put(str,paint);
        }
    }
    private Map<String,Paint> mPaintMap = new HashMap<>();


    private void drawLine(Point mStartPoint, Point mEmdPoint, Canvas canvas, Paint paint,Transformer transformer){
        Log.i("draw","draw---->5 paint.color:" + paint.getColor());
        Path mLinePath = new Path();
        Point mStartControlPoint = new Point(); //绘制曲线时控制点1
        Point mEndControlPoint = new Point();  //绘制曲线时控制点2
        mLinePath.moveTo(mStartPoint.x, mStartPoint.y);
        int wt = (mStartPoint.x + mEmdPoint.x) / 2;
        mStartControlPoint.y = mStartPoint.y;
        mStartControlPoint.x = wt;
        mEndControlPoint.y = mEmdPoint.y;
        mEndControlPoint.x = wt;
        mLinePath.cubicTo(mStartControlPoint.x,mStartControlPoint.y,mEndControlPoint.x,mEndControlPoint.y,mEmdPoint.x,mEmdPoint.y);  //其中p3和p4是控制点
        transformer.pathValueToPixel(mLinePath);
        canvas.drawPath(mLinePath, paint);
    }
    boolean isNeedDevidePoint(Point start, Point end){

        if(getPointLevel(start) == getPointLevel(end)){
            return false;
        }else{
            return true;
        }
    }
    private int[] yLevel = new int[]{60,116,135,172,180};
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
    private int getYMaxByLevel(int level){
        return yLevel[level];
    }

    protected void drawHorizontalBezier(Canvas canvas,ILineDataSet dataSet) {

        float phaseY = mAnimator.getPhaseY();

        Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());

        mXBounds.set(mChart, dataSet);


        if (mXBounds.range >= 1) {
            Log.i("draw","draw---->1");
            Entry prev = dataSet.getEntryForIndex(mXBounds.min);
            Entry cur = prev;
            // let the spline start
            Point mStartPoint = new Point();
            Point mEmdPoint = new Point();
            for (int i = mXBounds.min + 1; i <= mXBounds.range + mXBounds.min; i++) {
                prev = dataSet.getEntryForIndex(i - 1);
                cur = dataSet.getEntryForIndex(i);
                final float cpx = (prev.getX())
                        + (cur.getX() - prev.getX()) / 2.0f;
                mStartPoint.set((int) prev.getX(), (int)(prev.getY() * phaseY));
                mEmdPoint.set((int)cur.getX(), (int)(cur.getY() * phaseY));

                Log.i("draw","draw---->6 startX:" + mStartPoint.x + ",startY:" + mStartPoint.y + ",endX:" + mEmdPoint.x + ",endY:" + mEmdPoint.y);
                if(isNeedDevidePoint(mStartPoint,mEmdPoint)){
                    Log.i("draw","draw---->2");
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
                                drawLine(mStartPoint,middle[j],canvas,mPaintMap.get("level" + levelStart),trans);
                            }else if(j == deviceCount){
                                drawLine(middle[j-1],mEmdPoint,canvas,mPaintMap.get("level" + levelEnd),trans);
                            }else{
                                drawLine(middle[j-1],middle[j],canvas,mPaintMap.get("level" + (levelStart+j)),trans);
                            }
                        }
                    }else{
                        Log.i("draw","draw---->3");
                        int deviceCount = levelStart - levelEnd;
                        Point[] middle = new Point[deviceCount + 1];
                        for(int j=0; j <=deviceCount; j++ ){
                            int x = mStartPoint.x + (j+1)*(mEmdPoint.x-mStartPoint.x)/(deviceCount+1);
                            int y = getYMinByLevel(levelStart-j);
                            middle[j] = new Point(x,y);
                            if(j == 0){
                                drawLine(mStartPoint,middle[j],canvas,mPaintMap.get("level" + levelStart),trans);
                            }else if(j == deviceCount){
                                drawLine(middle[j-1],mEmdPoint,canvas,mPaintMap.get("level" + levelEnd),trans);
                            }else{
                                drawLine(middle[j-1],middle[j],canvas,mPaintMap.get("level" + (levelStart-j)),trans);
                            }
                        }
                    }
                }else{
                    Log.i("draw","draw---->4");
                    drawLine(mStartPoint,mEmdPoint,canvas,mPaintMap.get("level" + getPointLevel(mStartPoint)),trans);
                }

            }
        }


    }
    private int getYMinByLevel(int level){
        return yLevel[level-1]+1;
    }
}
