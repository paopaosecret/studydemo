package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.viewpagerindicator.TabPageIndicator;
import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.service.utils.Utils;
import com.xbing.com.viewdemo.ui.adapter.GridViewAdapter;
import com.xbing.com.viewdemo.ui.adapter.GridViewVPAdapter;
import com.xbing.com.viewdemo.ui.adapter.HeaderViewBean;
import com.xbing.com.viewdemo.ui.customview.banner.Banner;
import com.xbing.com.viewdemo.ui.customview.banner.BannerConfig;
import com.xbing.com.viewdemo.ui.customview.banner.Transformer;
import com.xbing.com.viewdemo.ui.customview.banner.listener.OnBannerListener;
import com.xbing.com.viewdemo.ui.customview.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import static com.xbing.com.viewdemo.ui.customview.banner.BannerConfig.NUM_INDICATOR;


/**
 * Created by zhaobing04 on 2018/3/29.
 */

public class ViewPagerActivity extends Activity implements ViewPager.OnPageChangeListener, OnBannerListener {

    private ViewPager mViewPager;
    private GridView gridView1,gridView2,gridView3;
    private TabLayout mTabLayout;
    private TextView mTvTabShow;
    List<HeaderViewBean> mDatas;

    private String[] tabTitle = {
            "新手入门",
            "推广秘籍",
            "高手专区",
            "活动报名"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        mTabLayout = findViewById(R.id.tl_title);
        mTvTabShow = findViewById(R.id.tv_show_tab);
        //添加标题
        for (int i=0;i<tabTitle.length;i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[i]));
        }
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mTvTabShow.setText(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        initDatas();

        mViewPager = (ViewPager) findViewById(R.id.vp_content);
        ArrayList<View> aList = new ArrayList<View>();
        LayoutInflater li = getLayoutInflater();

        View  view1 = li.inflate(R.layout.viewpager_one,null,false);
        aList.add(view1);
        View  view2 = li.inflate(R.layout.viewpager_two,null,false);
        aList.add(view2);
        View  view3 = li.inflate(R.layout.viewpager_three,null,false);
        aList.add(view3);

        gridView1 = (GridView) view1.findViewById(R.id.gridview1);
        gridView2 = (GridView) view2.findViewById(R.id.gridview2);
        gridView3 = (GridView) view3.findViewById(R.id.gridview3);


        GridViewVPAdapter adapter1 = new GridViewVPAdapter(this,mDatas,0);
        GridViewVPAdapter adapter2 = new GridViewVPAdapter(this,mDatas,1);
        GridViewVPAdapter adapter3 = new GridViewVPAdapter(this,mDatas,2);
        gridView1.setAdapter(adapter1);
        gridView2.setAdapter(adapter2);
        gridView3.setAdapter(adapter3);

        int count = 0;
        if(null != mDatas && mDatas.size() > 0){
            if(mDatas.size() % 4 == 0){
                count = mDatas.size() / 4 ;
            }else{
                count = mDatas.size() /4 + 1;
            }
        }
        MyPagerAdapter mAdapter = new MyPagerAdapter(aList,count);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(this);

        TabPageIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);
        indicator.setOnPageChangeListener(this);
        indicator.setCurrentItem(1);

        initBanner();
    }

    private void initBanner() {
        Banner banner = (Banner) findViewById(R.id.banner);

        ArrayList<String> list_path = new ArrayList<>();

        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");

        //放标题的集合
        ArrayList<String> list_title = new ArrayList<>();
        list_title.add("好好学习");
        list_title.add("天天向上");
        list_title.add("热爱劳动");
        list_title.add("不搞对象");

        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
            .setImageLoader(new MyLoader())//设置图片加载器，图片加载器在下方
            .setImages(list_path)   //设置图片网址或地址的集合
            .setBannerAnimation(Transformer.Default)  //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
            .setBannerTitles(list_title)     //设置轮播图的标题集合
            .setBannerStyle(NUM_INDICATOR)
            .setDelayTime(2000)       //设置轮播间隔时间
            .isAutoPlay(true)        //设置是否为自动轮播，默认是“是”。
            .setIndicatorGravity(BannerConfig.CENTER)        //设置指示器的位置，小点点，左中右。
            .setOnBannerListener(this) //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
            .start();  //必须最后调用的方法，启动轮播图。
    }

    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第"+position+"张轮播图");
        switch (position){
            case 1:

                break;
            case 2:

                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public ViewPager getmViewPager() {
        return mViewPager;
    }


    public class MyPagerAdapter extends PagerAdapter {
        private ArrayList<View> viewLists;
        private int count = 0;
        public MyPagerAdapter() {
        }

        public MyPagerAdapter(ArrayList<View> viewLists, int count) {
            super();
            this.viewLists = viewLists;
            this.count = count;
        }

        @Override
        public int getCount() {
            return this.count;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewLists.get(position));
            return viewLists.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewLists.get(position));
        }
    }

    public void initDatas(){
        mDatas = new ArrayList<HeaderViewBean>();
        mDatas.add(new HeaderViewBean("帅哥1", R.mipmap.ic_launcher));
        mDatas.add(new HeaderViewBean("帅哥2", R.mipmap.ic_launcher));
        mDatas.add(new HeaderViewBean("帅哥3", R.mipmap.ic_launcher));
        mDatas.add(new HeaderViewBean("帅哥4", R.mipmap.ic_launcher));
        mDatas.add(new HeaderViewBean("帅哥5", R.mipmap.ic_launcher));
        mDatas.add(new HeaderViewBean("帅哥6", R.mipmap.ic_launcher));
        mDatas.add(new HeaderViewBean("帅哥7", R.mipmap.ic_launcher));

    }

    class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
}
