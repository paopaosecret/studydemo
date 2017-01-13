package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.aigestudio.wheelpicker.WheelPicker;
import com.xbing.com.viewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaobing on 2016/8/15.
 */
public class TwoListActivity extends Activity implements WheelPicker.OnItemSelectedListener{


    private ListView mListRight;
    private ArrayAdapter<String> mAdapterRight;
    private List<String> mDataLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twolist_activity);
        mListRight = (ListView)findViewById(R.id.lv_right);

        mDataLeft = new ArrayList<String>();
        mDataLeft.add("1-周一-2005步");
        mDataLeft.add("2-周二-2055步");
        mDataLeft.add("3-周三-2345步");
        mDataLeft.add("4-周四-2235步");
        mDataLeft.add("5-周五-2035步");
        mDataLeft.add("6-周六-2035步");
        mDataLeft.add("7-周七-2035步");
        mDataLeft.add("8-周一-2035步");
        mDataLeft.add("9-周二-2035步");
        mDataLeft.add("10-周三-2035步");
        mDataLeft.add("11-周四-2035步");
        WheelPicker wheelLeft = (WheelPicker) findViewById(R.id.main_wheel_left);
        wheelLeft.setData(mDataLeft);
        wheelLeft.setSelectedItemPosition(1);//设置当前显示的数据位置  下标从0开始
        wheelLeft.setOnItemSelectedListener(this);

        mAdapterRight = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData());
        mListRight.setAdapter(mAdapterRight);
        mAdapterRight.notifyDataSetChanged();
    }

    private List<String> getData(){
        List<String> data = new ArrayList<String>();
        for(int i = 0;i<50;i++){
            data.add("item-> " + i);
        }
        return data;
    }

    @Override
    public void onItemSelected(WheelPicker picker, Object data, int position) {
        Toast.makeText(this, position+":" + String.valueOf(data), Toast.LENGTH_SHORT).show();
    }
}
