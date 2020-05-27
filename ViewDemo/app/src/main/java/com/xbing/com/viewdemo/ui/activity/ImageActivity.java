package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.service.utils.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by zhaobing on 2016/8/11.
 */
public class ImageActivity extends Activity implements View.OnClickListener{
    TextView mShow;
    ImageView mImage;
    EditText mParameter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity);
        findViewById(R.id.btn_testArgb8888).setOnClickListener(this);
        findViewById(R.id.btn_testArgb4444).setOnClickListener(this);
        findViewById(R.id.btn_testrgb565).setOnClickListener(this);
        findViewById(R.id.btn_testALPHA_8).setOnClickListener(this);
        findViewById(R.id.btn_quality_compress).setOnClickListener(this);
        findViewById(R.id.btn_size_compress).setOnClickListener(this);
        mShow = (TextView)findViewById(R.id.tv_show);
        mImage = (ImageView)findViewById(R.id.iv_image);
        mParameter = (EditText)findViewById(R.id.et_compress_para);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_testArgb8888:
                test(Bitmap.Config.ARGB_8888);
                break;
            case R.id.btn_testArgb4444:
                test(Bitmap.Config.ARGB_4444);
                break;
            case R.id.btn_testrgb565:
                test(Bitmap.Config.RGB_565);
                break;
            case R.id.btn_testALPHA_8:
                test(Bitmap.Config.ALPHA_8);
                break;
            case R.id.btn_size_compress:
                compressSize();
                break;
            case R.id.btn_quality_compress:
                compressQuality();
                break;
        }
    }

    public void test(Bitmap.Config config){

        String filePath = Utils.getImagePath("640480.BMP");

        BitmapFactory.Options options = new BitmapFactory.Options();
        /**
         * RGB_565 R：5bit G：6bit  B:5bit  一个像素占两位
         * ARGB_8888 A:透明度1byte R:1byte G:1byte B:1byte 一个像素占四位
         * ARGB_4444 A:透明度4bit R:4bit G:4bit B:4bit 一个像素占2位
         */
        options.inPreferredConfig = config;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath,options);

        if(bitmap != null){
            mShow.setText("图片大小：" + bitmap.getByteCount() + "byte");
            mImage.setImageBitmap(bitmap);
//            Toast.makeText(this,"test" +  bitmap.getByteCount(),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"test:" +  "bitmap is null",Toast.LENGTH_SHORT).show();
        }
    }

    public void compressQuality(){

        int quality = Integer.valueOf(mParameter.getText().toString());
        String filePath = Utils.getImagePath("640480.BMP");

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        Bitmap bitmapCompressBefore = BitmapFactory.decodeFile(filePath,options);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.reset();

        bitmapCompressBefore.compress(Bitmap.CompressFormat.JPEG,quality,baos);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        Bitmap bitmap = BitmapFactory.decodeStream(bais);
        if(bitmap != null){
            mShow.setText("图片大小：" + bitmap.getByteCount() + "byte" + ",stream length:" + baos.toByteArray().length);
            mImage.setImageBitmap(bitmap);
//            Toast.makeText(this,"test" +  bitmap.getByteCount(),Toast.LENGTH_SHORT).show();
            try {
                baos.close();
                bais.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            Toast.makeText(this,"test:" +  "bitmap is null",Toast.LENGTH_SHORT).show();
        }

    }

    public void compressSize(){
        Toast.makeText(this,"test:" +  "休息下，今后再写",Toast.LENGTH_SHORT).show();
    }
}
