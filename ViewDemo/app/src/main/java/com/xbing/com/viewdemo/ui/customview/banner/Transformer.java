package com.xbing.com.viewdemo.ui.customview.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.xbing.com.viewdemo.ui.customview.banner.transformer.AccordionTransformer;
import com.xbing.com.viewdemo.ui.customview.banner.transformer.BackgroundToForegroundTransformer;
import com.xbing.com.viewdemo.ui.customview.banner.transformer.CubeInTransformer;
import com.xbing.com.viewdemo.ui.customview.banner.transformer.CubeOutTransformer;
import com.xbing.com.viewdemo.ui.customview.banner.transformer.DefaultTransformer;
import com.xbing.com.viewdemo.ui.customview.banner.transformer.DepthPageTransformer;
import com.xbing.com.viewdemo.ui.customview.banner.transformer.FlipHorizontalTransformer;
import com.xbing.com.viewdemo.ui.customview.banner.transformer.FlipVerticalTransformer;
import com.xbing.com.viewdemo.ui.customview.banner.transformer.ForegroundToBackgroundTransformer;
import com.xbing.com.viewdemo.ui.customview.banner.transformer.RotateDownTransformer;
import com.xbing.com.viewdemo.ui.customview.banner.transformer.RotateUpTransformer;
import com.xbing.com.viewdemo.ui.customview.banner.transformer.ScaleInOutTransformer;
import com.xbing.com.viewdemo.ui.customview.banner.transformer.StackTransformer;
import com.xbing.com.viewdemo.ui.customview.banner.transformer.TabletTransformer;
import com.xbing.com.viewdemo.ui.customview.banner.transformer.ZoomInTransformer;
import com.xbing.com.viewdemo.ui.customview.banner.transformer.ZoomOutSlideTransformer;
import com.xbing.com.viewdemo.ui.customview.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
