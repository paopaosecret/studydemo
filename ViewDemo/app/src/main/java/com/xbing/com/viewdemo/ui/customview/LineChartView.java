package com.xbing.com.viewdemo.ui.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xbing.com.viewdemo.service.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zhaobing on 2016/8/15.
 */
public class LineChartView extends View {

    private int xPoint = 60;    //坐标原点x
    private int yPoint = 200;   //坐标原点y

    private int xScale = 4;     //x间隔
    private int yScale = 30;    //y间隔

    private int xLength = 240;  //x长度
    private int yLength = 180;  //y长度

    private int xMaxCount = xLength / xScale;   //x轴刻度最大个数
    private int yMaxCount = yLength / yScale;   //Y轴刻度最大个数

    private String[] yLabel = new String[yMaxCount];
    private List<Integer> data = new ArrayList<Integer>();

    private boolean isRunning = true;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            Log.e("sendMsg","sendMsg-->recv");
            if (msg.what == 0x1234) {
                LineChartView.this.invalidate();
            }
        }
    };

    public LineChartView(Context context) {
        super(context);
        init(context);

    }

    public void init(Context context){
        xPoint = Utils.dip2Px(context,60);
        yPoint = Utils.dip2Px(context,200);

        xScale = Utils.dip2Px(context,4);
        yScale = Utils.dip2Px(context,30);

        xLength = Utils.dip2Px(context,240);
        yLength = Utils.dip2Px(context,180);
    }
    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        getData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRunning()){
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    if(data.size()>= xMaxCount){
                        data.remove(0);
                    }
                    data.add(new Random().nextInt(4) + 1);
                    Log.e("sendMsg","sendMsg-->send");
                    mHandler.sendEmptyMessage(0x1234);
                }
            }
        }).start();
    }

    public void getData(){
        for(int i = 0; i< yMaxCount; i++){
            yLabel[i] = (i+1)+"M/S";
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);

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

        if(data.size()>1){
            for (int i = 1;i<data.size();i++){
                canvas.drawLine(xPoint+(i-1)*xScale,yPoint-data.get(i-1)*yScale,
                        xPoint+i*xScale,yPoint-data.get(i)*yScale,paint);
            }
        }


    }
}
