package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.customview.swipmenulistview.SwipeMenu;
import com.xbing.com.viewdemo.ui.customview.swipmenulistview.SwipeMenuCreator;
import com.xbing.com.viewdemo.ui.customview.swipmenulistview.SwipeMenuItem;
import com.xbing.com.viewdemo.ui.customview.swipmenulistview.SwipeMenuListView;
import com.xbing.com.viewdemo.ui.customview.timeline.TimelineAdapter;

import java.util.List;

/**
 * Created by bing.zhao on 2017/2/19.
 */

public class SwipMenuActivity extends Activity {

    private SwipeMenuListView mSwipListView;
    private List<ApplicationInfo> mAppList;
    private AppAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swip_menu);
        mSwipListView = (SwipeMenuListView) findViewById(R.id.smlv_listview);
        mAppList = getPackageManager().getInstalledApplications(0);
        mAdapter = new AppAdapter();
        mSwipListView.setAdapter(mAdapter);


        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem item1 = new SwipeMenuItem(
                        getApplicationContext());
                item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0x18,
                        0x5E)));
                item1.setWidth(dp2px(90));
                item1.setIcon(R.drawable.ic_launcher);
                menu.addMenuItem(item1);
            }
        };
        // set creator
        mSwipListView.setMenuCreator(creator);

        // step 2. listener item click event
        mSwipListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                ApplicationInfo item = mAppList.get(position);
                switch (index) {
                    case 0:
                        Log.i("onMenuItemClick","onMenuItemClick: frsit button is click");
                        Log.i("onMenuItemClick","onMenuItemClick: second button is click");
                        mAppList.remove(position);
                        mAdapter.notifyDataSetChanged();
                        break;

                }
                return false;
            }
        });
    }

    class AppAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mAppList.size();
        }

        @Override
        public ApplicationInfo getItem(int position) {
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(),
                        R.layout.item_list_app, null);
                holder = new ViewHolder();
                holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            ApplicationInfo item = getItem(position);
            holder.iv_icon.setImageDrawable(item.loadIcon(getPackageManager()));
            holder.tv_name.setText(item.loadLabel(getPackageManager()));
            return convertView;
        }

        class ViewHolder {
            ImageView iv_icon;
            TextView tv_name;
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
