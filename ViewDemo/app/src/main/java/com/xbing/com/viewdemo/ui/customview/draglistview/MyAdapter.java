package com.xbing.com.viewdemo.ui.customview.draglistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xbing.com.viewdemo.R;

import java.util.List;

/**
 * Created by bing.zhao on 2017/5/16.
 */

public class MyAdapter extends DragListViewAdapter<String> {

    private Context mContext;
    public MyAdapter(Context context, List<String> dataList) {
        super(context, dataList);
        mContext = context;
    }

    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_drag_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.list_item_text);
            viewHolder.desc = (ImageView) convertView.findViewById(R.id.list_item_desc);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(mDragDatas.get(position));
        String s = mDragDatas.get(position) + "的描述";
        return convertView;
    }

    class ViewHolder{
        TextView name;
        ImageView desc;
    }

}