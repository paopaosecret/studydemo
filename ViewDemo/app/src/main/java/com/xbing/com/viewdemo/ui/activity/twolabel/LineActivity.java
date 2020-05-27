package com.xbing.com.viewdemo.ui.activity.twolabel;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.customview.MyMarkerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaobing on 2016/8/22.
 */
public class LineActivity extends Activity implements OnChartGestureListener,OnChartValueSelectedListener{

    private LineChart mChart;

    private LineData data;

    private int mCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mp_line_activity);

        findViewById(R.id.btn_add_point).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCount++;
                setData(mCount, 200);
                mChart.invalidate();

            }
        });

        mChart = (LineChart) findViewById(R.id.chart1);
        mChart.setOnChartGestureListener(this);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawGridBackground(false);

        // no description text
        mChart.setDescription("xbing-description");    //图右下角文字描述
        mChart.setNoDataTextDescription("xbing-nodatadescription");  //图表没数据显示的描述
        mChart.setNoDataText("xbing_nodatatext");

        // enable touch gestures
        mChart.setTouchEnabled(true);         //是否允许触摸,ture,可放大缩小图表

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);       //设置放大缩小图表
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);


        // 设置图表背景颜色
//         mChart.setBackgroundColor(Color.GRAY);

        // 当用户点击某个点的时候显示这个带你的标记信息
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart

        // x-axis limit line
//        LimitLine llXAxis = new LimitLine(10f, "Index 10");
//        llXAxis.setLineWidth(4f);
//        llXAxis.enableDashedLine(10f, 10f, 0f);
//        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
//        llXAxis.setTextSize(10f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);   //虚线设置
        xAxis.setAxisMaximum(20f);   //设置x轴最大刻度
        xAxis.setDrawGridLines(false);  //设置是否需要画网格线
//        xAxis.setDrawAxisLine(false);  //设置是否需要画X轴线
//        xAxis.disableGridDashedLine();
        //xAxis.setValueFormatter(new MyCustomXAxisValueFormatter());
        //xAxis.addLimitLine(llXAxis); // add x-axis limit line



        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
//        leftAxis.addLimitLine(ll1);
//        leftAxis.addLimitLine(ll2);
        leftAxis.setAxisMaximum(200f);  //设置轴上最大值
        leftAxis.setAxisMinimum(0f);    //设置轴上最小刻度
//        leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f); //设置轴线为虚线
//        leftAxis.disableGridDashedLine();//设置轴线为实线
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        mChart.setHardwareAccelerationEnabled(true);

        mChart.setBorderColor(Color.RED);

        //mChart.getViewPortHandler().setMaximumScaleY(2f);
        //mChart.getViewPortHandler().setMaximumScaleX(2f);

        // add data
        setData(mCount, 189);

//        mChart.setVisibleXRange(20);
//        mChart.setVisibleYRange(20f, AxisDependency.LEFT);
//        mChart.centerViewTo(20, 50, AxisDependency.LEFT);

//        mChart.animateX(2500);
        mChart.setHardwareAccelerationEnabled(true);
        mChart.invalidate();
        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
//        l.setEnabled(false);

        // modify the legend ...
//         l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);
//
////        Typeface mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
////        l.setTypeface(mTfLight);
        l.setTextSize(11f);
        l.setTextColor(Color.RED);
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);

        // // dont forget to refresh the drawing
        // mChart.invalidate();

    }


    private void setData(int count, float range) {

        ArrayList<Entry> values1 = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range) + 3;
            values1.add(new Entry(i, val));
        }

        ArrayList<Entry> values2 = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range) + 3;
            values2.add(new Entry(i, val));
        }
        LineDataSet set1,set2;



        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set1.setValues(values1);
            set2.setValues(values2);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values1, "x-data");

            // set the line to be drawn like this "- - - - - -"
            set1.disableDashedLine();
            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(Color.BLUE);
            set1.setCircleColor(Color.YELLOW);
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);
            set1.setDrawCircleHole(true);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(1f);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            //设置绘制的线条为虚线
//            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            set2 = new LineDataSet(values2, "y-data");

            // set the line to be drawn like this "- - - - - -"
            set2.disableDashedLine();
            set2.setAxisDependency(YAxis.AxisDependency.LEFT);
            set2.setColor(Color.GREEN);
            set2.setCircleColor(Color.RED);
            set2.setLineWidth(1f);
            set2.setCircleRadius(3f);
            set2.setDrawCircleHole(true);
            set2.setValueTextSize(9f);
            set2.setDrawFilled(true);
            set2.setFormLineWidth(1f);
            set2.setHighLightColor(Color.rgb(244, 117, 117));
            //置绘制的线条为虚线
//            set2.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set2.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
                set1.setFillDrawable(drawable);
                set2.setFillDrawable(drawable);
            }
            else {
                set1.setFillColor(Color.BLACK);
                set2.setFillColor(Color.BLACK);
            }

            set1.setDrawCircles(false);    //设置是否拐点处画圆
            set1.setMode(LineDataSet.Mode.LINEAR);    //设置线条模式HORIZONTAL_BEZIER:水平曲线  STEPPED:台阶式直线  CUBIC_BEZIER：三次贝塞尔曲线模式   LINEAR:默认直线
            set1.setCubicIntensity(0.9f);
            set1.setDrawFilled(true);
            set2.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets
            dataSets.add(set2);
            // create a data object with the datasets
            data = new LineData(dataSets);

            // set data
            mChart.setData(data);
        }
    }


    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            mChart.highlightValues(null); // or highlightTouch(null) for callback to onNothingSelected(...)
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: " + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("Entry selected", e.toString());
        Log.i("LOWHIGH", "low: " + mChart.getLowestVisibleX() + ", high: " + mChart.getHighestVisibleX());
        Log.i("MIN MAX", "xmin: " + mChart.getXChartMin() + ", xmax: " + mChart.getXChartMax() + ", ymin: " + mChart.getYChartMin() + ", ymax: " + mChart.getYChartMax());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }
}
