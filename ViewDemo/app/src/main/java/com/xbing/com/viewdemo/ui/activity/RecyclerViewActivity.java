package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.customview.MyDecoration;
import com.xbing.com.viewdemo.ui.customview.timeline.TimeInfo;
import com.xbing.com.viewdemo.ui.customview.timeline.TimelineAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bing.zhao on 2017/2/6.
 * RecycleView知识点
 * 常用方法
 * 1.RecyclerView.setLayoutManager()  使用必选项(策略模式)：设置布局管理器，决定显示风格，常用的有线性布局管理器（LinearLayoutManager）,
 *                                    网格布局管理器（GridLayoutManager）,瀑布流布局管理器（StaggeredGridLayoutManager）
 * 2.RecyclerView.setAdapter()        使用必选项（适配器模式）：设置适配器  数据更新可通过Adapter 通知 RecycleView 更新UI
 * 3.RecyclerView.addItemDecoration() 可以不设置：设置Item装饰器，用来设置Item 分割线
 * 4.RecyclerView.setItemAnimator()   可以不设置：设置Item的动画
 */

public class RecyclerViewActivity extends Activity implements View.OnClickListener{

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private LineAdapter mLineAdapter;
    private List<TimeInfo> list=null;
    private MyDecoration mDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_view1);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLineAdapter = new LineAdapter();
        mRecyclerView.setAdapter(mLineAdapter);

        //设置每一项item的装饰效果，这里自定义的分割线
        mDecoration = new MyDecoration(this,LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(mDecoration);

        findViewById(R.id.btn_timeline).setOnClickListener(this);
    }

    private void initData() {
        mDatas = new ArrayList<String>();
        for(int i = 0; i<26; i++){
            mDatas.add("item  " + (char)('a' + i));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_timeline:
                setTimeLineAdapter();
                break;
        }
    }

    private void setTimeLineAdapter() {
        mRecyclerView.removeItemDecoration(mDecoration);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        list=new ArrayList<>();
        for(int i=0;i<10;i++){
            if(i == 5){
                TimeInfo info = new TimeInfo();
                info.setFrist(true);
                list.add(info);
            }else{
                list.add(new TimeInfo());
            }

        }
        TimelineAdapter mAdapter = new TimelineAdapter(this, list);
        mRecyclerView.setAdapter(mAdapter);
    }


    class LineAdapter extends RecyclerView.Adapter<LineAdapter.MyViewHolder>{
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    RecyclerViewActivity.this).inflate(R.layout.item_line_adapter, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.mText.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            private TextView mText;
            public MyViewHolder(View itemView) {
                super(itemView);
                mText = (TextView) itemView.findViewById(R.id.tv_text);
            }
        }
    }
}
