package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.service.utils.NetRequest;
import com.xbing.com.viewdemo.service.utils.ZipUtils;
import com.xbing.com.viewdemo.ui.customview.WaitDialog;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhaobing on 2016/10/13.
 */

public class ZipActivity extends Activity implements View.OnClickListener{

    private TextView mShow;
    WaitDialog waitDialog;
    private static final String zipPath = Environment.getExternalStorageDirectory().getAbsolutePath()+
            File.separator+"viewdemo"+File.separator+"zip";

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 100){
                String result = (String) msg.obj;
                if(waitDialog != null){
                    waitDialog.dismiss();
                }
                if(TextUtils.isEmpty(result)){
                    Toast.makeText(ZipActivity.this,"上传失败" ,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ZipActivity.this,"上传成功:" + result ,Toast.LENGTH_SHORT).show();
                    Log.e("upload","upload:" + result);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zip_activity);

        mShow = (TextView)findViewById(R.id.tv_show);
        mShow.setText(zipPath);
        findViewById(R.id.btn_zip).setOnClickListener(this);
        findViewById(R.id.btn_upload).setOnClickListener(this);
        findViewById(R.id.btn_showdialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_zip){
            String  fileSrc = zipPath + File.separator + "123.txt";
            String fileObj = zipPath + File.separator + "123.zip";
            try {
                ZipUtils.zip(fileSrc,fileObj);
                Toast.makeText(ZipActivity.this,"压缩成功",Toast.LENGTH_SHORT).show();
            }  catch (IOException e) {
                Toast.makeText(ZipActivity.this,"IOException",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }else if(v.getId() == R.id.btn_upload){
            if(waitDialog==null){
                waitDialog = new WaitDialog(ZipActivity.this);
            }
            waitDialog.show("正在上传文件");
            NetRequest.getInstance().uploadFile(mHandler,100,zipPath + File.separator + "123.zip");
        }else if(v.getId() == R.id.btn_showdialog) {
            if (waitDialog == null) {
                waitDialog = new WaitDialog(ZipActivity.this,R.style.translucent_dialog);
            }
            waitDialog.show("正在上传文件");
        }else if(v.getId() == R.id.btn_closedialog){
            if (waitDialog != null) {
                waitDialog.dismiss();
            }
        }
    }
}
