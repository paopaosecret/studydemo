package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.NumberPicker;

import com.xbing.com.viewdemo.R;

/**
 * Created by zhaobing on 2016/9/1.
 */
public class PickViewActivity extends Activity {
    private NumberPicker mNumberPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickview_activity);

        mNumberPicker = (NumberPicker)findViewById(R.id.np_pick);
        mNumberPicker.setMinValue(0);
        mNumberPicker.setMaxValue(3);
        mNumberPicker.setDisplayedValues(new String[]{"fsa","fdasf","dsadfa","fdas"});
//        mNumberPicker.setFormatter(new NumberPicker.Formatter() {
//            @Override
//            public String format(int i) {
//                if(i==6){
//                    return "8\n这个\n你妹的";
//                }
//
//                return i+"ge";
//            }
//        });

    }
}
