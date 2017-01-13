package com.xbing.com.viewdemo.ui.activity.twolabel;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.adapter.HorListAdapter;
import com.xbing.com.viewdemo.ui.customview.HorizontalListView;
import com.xbing.com.viewdemo.ui.customview.ValueItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bing.zhao on 2016/12/26.
 */

public class HorListViewActivity extends Activity {

    HorizontalListView mHorListView;


    HorListAdapter mAdapter;
    List<String> mData = new ArrayList<String>();

    ValueItemView mValueView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hor_listview);

//        mValueView = (ValueItemView) findViewById(R.id.viv_value);
//        mValueView.setValue(0.5f,0.25f,"Jun");

        mData.add("Jan");
        mData.add("Feb");
        mData.add("Mar");
        mData.add("Apr");
        mData.add("May");
        mData.add("Jun");
        mData.add("Jul");
        mData.add("Aug");
        mData.add("Sep");
        mData.add("Oct");
        mData.add("Nov");
        mData.add("Dec");

        mHorListView = (HorizontalListView)findViewById(R.id.hlv_view);
        mAdapter = new HorListAdapter(HorListViewActivity.this,mData);

        mHorListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mHorListView.setSelection(5);
            }
        }, 350);

    }

}
