package com.xbing.com.viewdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.customview.ValueItemView;

import java.util.List;
import java.util.Random;

/**
 * Created by bing.zhao on 2016/12/26.
 */

public class HorListAdapter extends BaseAdapter {

    private List<String> mData;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public HorListAdapter(Context context, List<String> list){
        mData = list;
        mContext = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if(null == view){
            view = layoutInflater.inflate(R.layout.hor_list_item,null);
            holder = new ViewHolder();
            holder.valueItemView = (ValueItemView)view.findViewById(R.id.hor_item);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Random random = new Random();
        int hightbg  = random.nextInt(100);
        if(hightbg<=10){
            hightbg = 10;
        }
        int hightfo = random.nextInt(hightbg);
        if(hightfo <=10){
            hightfo = 10;
        }
        holder.valueItemView.setValue(hightbg/100.0f,hightfo/100.0f,mData.get(i));
        return view;
    }

    class ViewHolder{
        ValueItemView valueItemView;
    }
}
