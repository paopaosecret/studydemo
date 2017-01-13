package com.xbing.com.viewdemo.ui.adapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xbing.com.viewdemo.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zhaobing on 2016/8/24.
 */
public class GridViewAdapter extends BaseAdapter {

    private List<ResolveInfo> mData;
    private LayoutInflater layoutInflater;
    private Context mContext;


    public GridViewAdapter(Context context,List<ResolveInfo> list){
        mData = list;
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
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
        if (view == null) {
            holder = new ViewHolder();
            view = layoutInflater
                    .inflate(R.layout.gridview_item, null);
            holder.tvName = (TextView)view.findViewById(R.id.tv_name);
            holder.ivIcon = (ImageView)view.findViewById(R.id.iv_icon);
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }
        PackageManager pManager = mContext.getPackageManager();
        // set Icon
        holder.ivIcon.setImageDrawable(mData.get(i).loadIcon(pManager));
        // set Application Name
        holder.tvName.setText(mData.get(i).loadLabel(pManager).toString());
        return view;
    }

    class ViewHolder{
        TextView tvName;
        ImageView ivIcon;
    }
}
