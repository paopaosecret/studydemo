package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.adapter.CardAdapter;
import com.xbing.com.viewdemo.ui.adapter.viewpager.CardTransformer;
import com.xbing.com.viewdemo.ui.adapter.viewpager.GralleryVPAdapter;
import com.xbing.com.viewdemo.ui.customview.recyclergallery.CardScaleHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bing.zhao on 2017/5/24.
 */

public class GalleryActivity extends Activity {

    private RecyclerView mRecyclerView;
    private ViewPager viewPager;
    private List<Integer> mList = new ArrayList<>();
    private CardScaleHelper mCardScaleHelper = null;
    private Runnable mBlurRunnable;
    private int mLastPos = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        init();
    }

    private void init() {
        for (int i = 0; i < 3; i++) {
            mList.add(R.drawable.pic4);
            mList.add(R.drawable.pic5);
            mList.add(R.drawable.pic6);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new CardAdapter(mList));

        // mRecyclerView绑定scale效果
        mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.setCurrentItemPos(2);
        mCardScaleHelper.attachToRecyclerView(mRecyclerView);

        viewPager = findViewById(R.id.vp_content);
        initViewPager();
    }

    private void initViewPager() {
        List<String> dataList = new ArrayList<>();
        dataList.add("位置1");
        dataList.add("位置2");
        dataList.add("位置3");
        dataList.add("位置4");
        dataList.add("位置5");
        dataList.add("位置6");
        dataList.add("位置7");
        GralleryVPAdapter vpAdapter = new GralleryVPAdapter(GalleryActivity.this, dataList);
        viewPager.setAdapter(vpAdapter);
        viewPager.setOffscreenPageLimit(2);//预加载2个
        viewPager.setPageMargin(30);//设置viewpage之间的间距
        viewPager.setClipChildren(false);
//        viewPager.setPageTransformer(true, new CardTransformer());      //设置中间放大效果
    }
}




