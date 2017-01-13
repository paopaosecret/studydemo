package com.xbing.com.viewdemo.ui.customview;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xbing.com.viewdemo.R;

/**
 * Created by zhaobing on 2016/10/14.
 */

public class WaitDialog extends Dialog {

    private LayoutInflater mLayoutInflater;
    private View mWaitView;
    private TextView mDialogText;
    public WaitDialog(Context context) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
        mWaitView = (ViewGroup)mLayoutInflater.inflate(R.layout.dialog_wait,null);
        mDialogText = (TextView) mWaitView.findViewById(R.id.waitName);
    }

    public WaitDialog(Context context, int themeResId) {
        super(context, themeResId);
        mLayoutInflater = LayoutInflater.from(context);
        mWaitView = (ViewGroup)mLayoutInflater.inflate(R.layout.dialog_wait,null);
        mDialogText = (TextView) mWaitView.findViewById(R.id.waitName);
    }

    public void show(String msg){
        setContentView(mWaitView);
        setCancelable(true);
        show();
        mDialogText.setText(msg);
    }

}
