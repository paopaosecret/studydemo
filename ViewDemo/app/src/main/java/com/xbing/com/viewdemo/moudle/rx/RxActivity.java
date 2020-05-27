package com.xbing.com.viewdemo.moudle.rx;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.manager.IAccountManager;
import com.xbing.com.viewdemo.manager.RxApi;
import com.xbing.com.viewdemo.manager.iml.AccountManagerIml;
import com.xbing.com.viewdemo.model.result.UserDetailResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by bing.zhao on 2017/3/6.
 */
public class RxActivity extends Activity implements View.OnClickListener{

    private TextView mRxTextView;
    public static final String TAG = RxActivity.class.getSimpleName();
    private IAccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rx_activity);
        mRxTextView = (TextView)findViewById(R.id.tv_show);
        accountManager = new AccountManagerIml();
        findViewById(R.id.btn_click1).setOnClickListener(this);
        findViewById(R.id.btn_click2).setOnClickListener(this);
        findViewById(R.id.btn_click3).setOnClickListener(this);
        findViewById(R.id.btn_click4).setOnClickListener(this);
        findViewById(R.id.btn_click5).setOnClickListener(this);
    }

    private void achieveObserver() {

        Observer<String> observer = new Observer<String>(){

            @Override
            public void onCompleted() {
                Log.i(TAG,"onCompleted()" + ",Thread:" + Thread.currentThread());
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG,"onError():" + e.toString());
                e.printStackTrace();

            }

            @Override
            public void onNext(String s) {
                Log.i(TAG,"onNext():" + s + ",Thread:" + Thread.currentThread());
//                mRxTextView.setText(s);
            }
        };


        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {   //OnSubscribe：相当于Observable的计划表，当被观察者在发生订阅时，会自动回掉call方法
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.i(TAG,"call：Thread"  + Thread.currentThread());
                subscriber.onNext("onNext->word");
                ThreadGroup tg = new ThreadGroup(Thread.currentThread().getThreadGroup(),"new group");
                new Thread(tg,new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG,"new：Thread"  + Thread.currentThread());
                    }
                }).start();

            }
        });

        observable.subscribeOn(Schedulers.io())              //----->决定订阅发生时执行代码的线程
                .observeOn(AndroidSchedulers.mainThread())    //---->决定观察者观察到事件，执行回掉的代码执行的线程
                .subscribe(observer);                        //---->发生订阅关系，会回调被观察者的计划表的call方法
        observable.subscribe();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_click1:
                achieveObserver();
                break;

            case R.id.btn_click2:
                click2();
                break;

            case R.id.btn_click3:
                callChain();
                break;

            case R.id.btn_click4:
                RxApi.getInstance().getApiDetail()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<UserDetailResult>() {
                            @Override
                            public void onCompleted() {
                                Log.i(TAG,"HTTP RESPONSE onCompleted"  + Thread.currentThread());
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i(TAG,"HTTP RESPONSE onError"  + Thread.currentThread());
                            }

                            @Override
                            public void onNext(UserDetailResult userDetailResult) {
                                Log.i(TAG,"HTTP RESPONSE onNext:avatar_url = "  + userDetailResult.getAvatar_url());
                            }

                            @Override
                            public void onStart() {
                                Log.i(TAG,"HTTP REQUEST onStart"  + Thread.currentThread());
                            }
                        });
                break;

            case R.id.btn_click5:
                stream();
                break;
        }
    }

    private void stream() {
        Observable.just(1,2,3,4,5)
                .doOnNext(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d(TAG,"current:" + integer);
            }
        })
        .subscribe();
    }


    public File[] getFlorders(){
        File florderImage  = new File(Environment.getExternalStorageDirectory(),"image");
        File florderPicture  = new File(Environment.getExternalStorageDirectory(),"Pictures");
        File[] florders = new File[2];
        florders[0] = florderImage;
        florders[1] = florderPicture;
        return florders;
    }

    private void callChain() {
        Observable.from(getFlorders())
                .flatMap(new Func1<File, Observable<File>>() {
                    @Override
                    public Observable<File> call(File file) {
                        Log.d(TAG,"flatmap filename:" + file.getName());
                        return Observable.from(file.listFiles());
                    }
                })
                .filter(new Func1<File, Boolean>() {
                    @Override
                    public Boolean call(File file) {
                        Log.d(TAG,"filter filename:" + file.getName());
                        return file.getName().endsWith(".jpg");
                    }
                })//
                .take(2)
                .map(new Func1<File, Bitmap>() {
                    @Override
                    public Bitmap call(File file) {
                        Log.d(TAG,"map filename:" + file.getName());
                        return getBitmapFromFile(file);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
//                        imageCollectorView.addImage(bitmap);
                        if(bitmap != null){
                            Log.d(TAG,"show image:height = " + bitmap.getHeight() + ",width = " + bitmap.getWidth());
                        }

                    }
                })
        ;
    }

    private Bitmap getBitmapFromFile(File file) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void click2() {
        //多线程是否可以这样写
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.i(TAG,"call：Thread"  + Thread.currentThread());
            }
        }).subscribeOn(Schedulers.io()).doOnNext(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i(TAG,"doOnNext：call Thread"  + Thread.currentThread());
            }
        }).observeOn(Schedulers.io()).subscribe();
    }
}
