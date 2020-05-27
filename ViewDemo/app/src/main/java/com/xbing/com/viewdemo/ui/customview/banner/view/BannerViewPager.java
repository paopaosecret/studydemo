package com.xbing.com.viewdemo.ui.customview.banner.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by zhaobing04 on 2018/4/10.
 */

public class BannerViewPager extends ViewPager {

    private boolean scrollable = true;

    public BannerViewPager(Context context) {
        super(context);
    }

    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if(this.scrollable){
            if(getCurrentItem() == 0 && getChildCount() == 0){
                return false;
            }else{
                return super.onInterceptTouchEvent(ev);
            }
        }else{
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(this.scrollable){
            if(getCurrentItem() == 0 && getChildCount() == 0){
                return false;
            }else{
                return super.onInterceptTouchEvent(ev);
            }
        }else{
            return false;
        }
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

}
