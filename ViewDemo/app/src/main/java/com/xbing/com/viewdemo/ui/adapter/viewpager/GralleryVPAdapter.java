package com.xbing.com.viewdemo.ui.adapter.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xbing.com.viewdemo.R;

import java.util.List;

public class GralleryVPAdapter extends PagerAdapter {
    private List<String> mDataList;
    public static final int MAX_COUNT = 7;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    public GralleryVPAdapter() {

    }

    public GralleryVPAdapter( Context context, List<String> data) {
        super();
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.mDataList = data;
    }

    @Override
    public int getCount() {
        if(mDataList == null){
            return 0;
        }
        if(mDataList.size() > MAX_COUNT){
            return MAX_COUNT;
        }else{
            return mDataList.size();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.i("TAG", "position:" + position);
        View itemView = null;
        if(position == 0 && mDataList.size() == 1){
            mPageWidth = 1;
            itemView = mLayoutInflater.inflate(R.layout.item_task_viewcard, null);
            TextView textView = itemView.findViewById(R.id.tv_title);
            textView.setText(mDataList.get(position));
        }else if(position == 6){
            mPageWidth = 0.6f;
            itemView = mLayoutInflater.inflate(R.layout.item_task_viewcard2, null);
        }else{
            itemView = mLayoutInflater.inflate(R.layout.item_task_viewcard, null);
            mPageWidth = 0.95f;
            TextView textView = itemView.findViewById(R.id.tv_title);
            textView.setText(mDataList.get(position));
        }

        container.addView(itemView);
        return itemView;
    }

    private float mPageWidth = 1;
    @Override
    public float getPageWidth(int position) {
        return mPageWidth;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setmDataList(List<String> mDataList) {
        this.mDataList = mDataList;
    }
}
