package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.xbing.com.viewdemo.R;

/**
 * Created by bing.zhao on 2017/8/14.
 */

public class DialogActivity extends Activity {

    private TextView mTVShow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        String msg = getIntent().getStringExtra("message");
        mTVShow = (TextView)findViewById(R.id.tv_show);
        if(TextUtils.isEmpty(msg)){
            mTVShow.setText(msg);
        }

        // 这里你可以进行一些等待时的操作，我这里用8秒后显示Toast代理等待操作
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){

                DialogActivity.this.finish();
                Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
            }
        }, 8000);
    }

    public static void showDialog(Context context,String msg){
        Intent intent = new Intent(context,DialogActivity.class);
        intent.putExtra("message",msg);
        context.startActivity(intent);
    }
}
