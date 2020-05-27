package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.ui.customview.GifView;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by bing.zhao on 2016/12/19.
 */

public class GitActivity extends Activity {

    private GifView mGifView;
    private GifView mGifViewPro;
    private TextView mShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);

        mGifView = (GifView)findViewById(R.id.gv_gif);
        mGifView.setMovieResource(R.raw.walkingboy);
        mGifViewPro= (GifView)findViewById(R.id.gv_gif_pro);
        mGifViewPro.setMovieResource(R.raw.finish_goal);
        mShow = (TextView)findViewById(R.id.tv_show);

        findViewById(R.id.btn_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mGifView.isPaused()){
                    mGifView.setPaused(false);
                    mGifViewPro.setPaused(false);
                }else{
                    mGifView.setPaused(true);
                    mGifViewPro.setPaused(true);
                }
            }
        });

    }


}
