package com.xbing.com.viewdemo.ui.customview.draglistview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.xbing.com.viewdemo.R;

import java.util.ArrayList;
import java.util.List;


public class DragListActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drag_list_main);

        DragListView dvl_drag_list = (DragListView) findViewById(R.id.other_drag_list);

        List<String> mDataList = new ArrayList<>();
        for(int i =0;i<=5;i++){
            mDataList.add("item" + i);
        }
        MyAdapter mListAdapter = new MyAdapter(this, mDataList);
        dvl_drag_list.setAdapter(mListAdapter);
        dvl_drag_list.setmPositionChangeListener(new PositionChangeListener() {
            @Override
            public void onPositionChange(int start, int end) {
                Log.i("TAG","onPositionChange:start = " + start + ",end = " + end);
            }
        });
    }

}