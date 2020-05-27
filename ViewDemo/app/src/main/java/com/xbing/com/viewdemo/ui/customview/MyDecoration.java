package com.xbing.com.viewdemo.ui.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xbing.com.viewdemo.service.utils.Utils;

/**
 * Created by bing.zhao on 2017/2/6.
 */

public class MyDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private int mOrientation;
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;



    public MyDecoration(Context context, int orientation) {
        this.mContext = context;
        setOrientation(orientation);
    }

    //设置屏幕的方向
    public void setOrientation(int orientation){
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST){
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == HORIZONTAL_LIST){
            drawVerticalLine(c, parent, state);
        }else {
            drawHorizontalLine(c, parent, state);
        }
    }

    //画横线, 这里的parent其实是显示在屏幕显示的这部分
    public void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state){
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++){
            final View child = parent.getChildAt(i);


//            //画子view每一项的背景
//            final int cleft = child.getLeft();
//            final int ctop = child.getTop();
//            final int cright = child.getRight();
//            final int cbottom = child.getBottom();
//            Rect  rectCliclBg = new Rect(cleft,ctop,cright,cbottom);
//            Paint cp =new Paint(Color.parseColor("#00f163"));
//            c.drawRect(rectCliclBg,cp);


            //获得child的布局信息,并画分割线
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + Utils.dip2Px(mContext,1);
            Rect rect= new Rect(left,top,right,bottom);
            Paint p = new Paint();
            p.setColor(Color.RED);
            c.drawRect(rect,p);

        }
    }

    //画竖线
    public void drawVerticalLine(Canvas c, RecyclerView parent, RecyclerView.State state){
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++){
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left +  Utils.dip2Px(mContext,1);
            Rect rect= new Rect(left,top,right,bottom);
            Paint p = new Paint();
            p.setColor(Color.RED);
            c.drawRect(rect,p);
        }
    }

    //由于Divider也有长宽高，每一个Item需要向下或者向右偏移
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(mOrientation == HORIZONTAL_LIST){
            //画横线，就是往下偏移一个分割线的高度
            outRect.set(0, 0, Utils.dip2Px(mContext,1), 0);
        }else {
            //画竖线，就是往右偏移一个分割线的宽度
            outRect.set(0, 0,  0,  Utils.dip2Px(mContext,1));
        }
    }
}
