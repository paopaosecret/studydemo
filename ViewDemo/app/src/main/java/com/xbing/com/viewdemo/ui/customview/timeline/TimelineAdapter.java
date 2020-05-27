package com.xbing.com.viewdemo.ui.customview.timeline;

import android.content.Context;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xbing.com.viewdemo.R;

import java.util.List;


public class TimelineAdapter extends  RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private static final int ALPHA = 100;

    private List<TimeInfo> list=null;

    private Context context;
    public TimelineAdapter(Context context,List<TimeInfo> list) {
        this.list=list;
        this.context=context;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.v7_item_timeline, null);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText("2016-08-10\n10：20");
        int color = context.getResources().getColor(R.color.colorAccent);
        holder.civ.setFillColor(color);
        holder.civ.setBorderColor(ColorUtils.setAlphaComponent(color, ALPHA));
        holder.img.setBackgroundResource(R.mipmap.ic_zhihu_logo);
        if(list.get(position).isFrist()){
            holder.line.setVisibility(View.VISIBLE);
        }else{
            holder.line.setVisibility(View.INVISIBLE);
        }
        holder.item_timeline_view.setBackgroundResource(list.size()%2==0?R.color.colorAccent:R.color.colorPrimary);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CircleImageView civ;
        ImageView img;
        View item_timeline_view;
        View line;
        public ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.item_timeline_time);
            civ= (CircleImageView) v.findViewById(R.id.item_timeline_icon_bg);
            img= (ImageView) v.findViewById(R.id.item_timeline_icon);
            item_timeline_view=v.findViewById(R.id.item_timeline_view);
            line = v.findViewById(R.id.v_line);
        }
    }

}
