package com.xbing.com.viewdemo.ui.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;
import android.widget.Toast;

/**
 * Created by zhaobing on 2016/6/14.
 *
 * 注意使用Scroller滑动，或者使用view.scrollTo或者scrollBy（）方法
 * 都是滑动view的内容，view的位置并没有被滑动
 *
 */
public class MoveView extends View implements View.OnClickListener{

    private Paint mPaint;

    private Rect mBackground;

    private Scroller mScroller;
    private Context mContext;

    public MoveView(Context context) {
        super(context);
        mContext = context;
    }

    public MoveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.YELLOW);
        mBackground = new Rect(0,0,100,100);
        mScroller = new Scroller(context);
        setOnClickListener(this);
        mContext = context;
    }

    public MoveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(200,200,200,mPaint);
    }

    @Override
    public void onClick(View view) {
//        if(view.getX()==464){
//            view.setLeft(264);
//        }
//        view.setBackgroundColor(Color.BLUE);
        int x;
//        int y = 400;
//        view.setTranslationX(x);
//        view.setTranslationY(y);
        if(view.getScrollX() < -200 ){
            x = 20;
        }else{
            x= -20;
        }
        smaoothScrollerTo(-250,250);

//        ObjectAnimator animator = new ObjectAnimator();
//        animator = ObjectAnimator.ofFloat(view,"rotation",0,180,0);
//        animator.setDuration(1000);
//        animator.start();
        Toast.makeText(mContext,"left:" + view.getLeft() +",right:" + view.getRight()
                        + ",x:" + view.getX()+ ",translationx:" +view.getTranslationX()
                        + ",width" + view.getWidth()+",scrollx:" + view.getScrollX()
                ,Toast.LENGTH_SHORT).show();

    }

    public void smaoothScrollerTo(int destX, int destY){
        int scrollerX = getScrollX();
        int delta = destX - scrollerX;
        mScroller.startScroll(scrollerX,0,delta,0,1000);   //初始化scroller的参数
        invalidate();  //回调view的onDraw(),onDraw在绘制过程会回调下面的computeScoll（）方法
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){   //重置下次滑动的位置
            //通过上面的方法新的滑动位置按时间均分，并将下次滑动位置存放在curX,curY中
            //可查看源码Scroller.computeScrollOffset()
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            postInvalidate();
        }
    }
}
