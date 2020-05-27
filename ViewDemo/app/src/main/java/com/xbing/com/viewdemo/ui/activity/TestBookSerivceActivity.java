package com.xbing.com.viewdemo.ui.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xbing.app.component.aidl.BookManager;
import com.xbing.com.viewdemo.Book;
import com.xbing.com.viewdemo.IBookManager;
import com.xbing.com.viewdemo.R;
import com.xbing.com.viewdemo.aidl.BookManagerService;

import java.util.List;

/**
 * Created by bing.zhao on 2017/8/23.
 */

public class TestBookSerivceActivity extends Activity implements View.OnClickListener{
    private final String TAG = TestBookSerivceActivity.class.getSimpleName();
    private BookManager manager;
    private IBookManager mBinder;
    private TextView tvResult;

    private final String ACTION = "android.intent.action.BookService";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_book_service);
        tvResult = findViewById(R.id.tv_result);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_get).setOnClickListener(this);

        findViewById(R.id.btn_start_other_app).setOnClickListener(this);
        findViewById(R.id.btn_stop_other_app).setOnClickListener(this);
        findViewById(R.id.btn_add_other_app).setOnClickListener(this);
        findViewById(R.id.btn_get_other_app).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_start:
                bindService();
                break;
            case R.id.btn_stop:
                stopService();
                break;
            case R.id.btn_add:
                addBook();
                break;
            case R.id.btn_get:
                getBookList();
                break;

            case R.id.btn_start_other_app:
                Intent intent = new Intent(ACTION);
                //注意在 Android 5.0以后，不能通过隐式 Intent 启动 service，必须制定包名
                intent.setPackage("com.xbing.app.component");
                bindService(intent, otherAppConnection, Context.BIND_AUTO_CREATE);
                break;

            case R.id.btn_add_other_app:
                com.xbing.app.component.aidl.Book book = new com.xbing.app.component.aidl.Book("边城");
                try {
                    if (manager != null) {
                        manager.addBook(book);
                        tvResult.setText("新书：" + book.bookName);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_get_other_app:
                try {
                    if (manager != null) {
                        List<com.xbing.app.component.aidl.Book> list = manager.getBookList();
                        tvResult.setText("getBookList:" + list);
                        Log.d(TAG, "getBookList:" + list);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btn_stop_other_app:
                unbindService(otherAppConnection);
                break;
        }

    }

    private void addBook() {
        if(mBinder!=null){
            Book book = new Book();
            book.setBookId(1);
            book.setBookName("天龙八部");
            try {
                mBinder.addBook(book);
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.i("BookManagerService","RemoteException");
            }
        }else{
            Log.i("BookManagerService","binder is null");
        }
    }

    private void stopService() {
        unbindService(serviceConnection);
    }

    private void bindService() {
        Intent intent = new Intent(TestBookSerivceActivity.this, BookManagerService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void startService() {
        Intent intent = new Intent(TestBookSerivceActivity.this, BookManagerService.class);
        startService(intent);
    }



    public void getBookList() {
        if(mBinder != null){
            try {
                List<Book> books = mBinder.getBookList();
                Log.i("BookManagerService","books[0]:" + books.get(0).getBookName() + ",books.size() =" + books.size());
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.i("BookManagerService","RemoteException");
            }
        }else{
            Log.i("BookManagerService","Binder is null");
        }

    }



    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("BookManagerService","onServiceConnected");
            mBinder = IBookManager.Stub.asInterface(service);
            try {
                service.linkToDeath(mDeathRecipient,0);
                Log.i("BookManagerService","linkToDeath");
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.i("BookManagerService","RemoteException");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("BookManagerService","onServiceDisconnected");
            mBinder = null;
        }
    };

    ServiceConnection otherAppConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            manager = BookManager.Stub.asInterface(service);
            if(manager != null){
                tvResult.setText("onServiceConnected:绑定服务成功");
            }else{
                tvResult.setText("onServiceConnected:绑定服务失败");
            }
        }

        //类ServiceConnection中的onServiceDisconnected()方法在正常情况下是不被调用的，
        //它的调用时机是当Service服务被异外销毁时，例如内存的资源不足时.
        @Override
        public void onServiceDisconnected(ComponentName name) {
            tvResult.setText("onServiceConnected:解除绑定服务");
            manager = null;
        }
    };

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if(mBinder == null){
                Log.i("BookManagerService","binderDied binder == null");
                return;
            }
            mBinder.asBinder().unlinkToDeath(mDeathRecipient,0);
            mBinder = null;
            Log.i("BookManagerService","binderDied binder != null");
            bindService();
        }
    };
}
