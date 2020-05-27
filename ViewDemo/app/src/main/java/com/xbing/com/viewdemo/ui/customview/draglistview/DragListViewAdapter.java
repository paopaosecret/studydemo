package com.xbing.com.viewdemo.ui.customview.draglistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by bing.zhao on 2017/5/16.
 */

public abstract class DragListViewAdapter<T> extends BaseAdapter {

    public Context mContext;
    public List<T> mDragDatas;

    public DragListViewAdapter(Context context, List<T> dataList){
        this.mContext = context;
        this.mDragDatas = dataList;
    }

    @Override
    public int getCount() {
        return mDragDatas == null ? 0 : mDragDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDragDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(position, convertView, parent);
    }

    public abstract View getItemView(int position, View convertView, ViewGroup parent);

    public void swapData(int from, int to){
        Collections.swap(mDragDatas, from, to);
        notifyDataSetChanged();
    }

    public void deleteData(int position) {
        mDragDatas.remove(position);
        notifyDataSetChanged();
    }
}