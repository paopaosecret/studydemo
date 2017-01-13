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

    private TextView mShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);

        mGifView = (GifView)findViewById(R.id.gv_gif);
        mGifView.setMovieResource(R.raw.walkingboy);

        mShow = (TextView)findViewById(R.id.tv_show);

        findViewById(R.id.btn_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mGifView.isPaused()){
                    mGifView.setPaused(false);
                }else{
                    mGifView.setPaused(true);
                }
            }
        });

        achieveObserver();
    }

    private void achieveObserver() {
        final String Tag = "ObserverTest";

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(Tag,"onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(Tag,"onError()");
            }

            @Override
            public void onNext(String s) {
                Log.i(Tag,"onNext():" + s);
                mShow.setText(s);
            }

            @Override
            public void onStart() {
                Log.i(Tag,"onStart():");
                super.onStart();
            }
        } ;

        Observer<String> observer = new Observer<String>(){

            @Override
            public void onCompleted() {
                Log.i(Tag,"onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(Tag,"onError()");
            }

            @Override
            public void onNext(String s) {
                Log.i(Tag,"onNext():" + s);
                mShow.setText(s);
            }
        };


        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.i(Tag,"call");
                subscriber.onNext("onNext->word");
                subscriber.onCompleted();
                subscriber.onError(new Exception());
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriber);
    }
}
