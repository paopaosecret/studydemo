package com.xbing.com.viewdemo.aidl;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.xbing.com.viewdemo.Book;
import com.xbing.com.viewdemo.IBookManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bing.zhao on 2017/8/23.
 */

public class BookManagerService extends Service {

    private Receiver mReceiver;
    private List<Book> bookList = new ArrayList<>();
    private static final String TAG = BookManagerService.class.getSimpleName();
    IBookManager.Stub mBinder = new IBookManager.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            if(bookList !=null && bookList.size()>0){
                return bookList;
            }else{
                List<Book> books = new ArrayList<>();
                Book book = new Book();
                book.setBookName("天龙八部");
                book.setBookId(1);
                books.add(book);
                return books;
            }
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            if(bookList.size()<10){
                bookList.add(book);
                Log.i(TAG,book.getBookName() + " has add");
            }else{
                Log.i(TAG,"bookList is MaxSize");
            }
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initReceiver();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private void initReceiver() {
        mReceiver = new Receiver(this);
        mReceiver.registerAction(ReceiverAction.RECEIVER_ACTION_ADD);
    }

    class Receiver extends BroadcastReceiver {

        Context mContext;

        public Receiver(Context context)
        {
            mContext = context;
        }

        public void registerAction(String action)
        {
            IntentFilter filter = new IntentFilter();
            filter.addAction(action);
            mContext.registerReceiver(this, filter);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i(TAG, "action:" + action);
            if (action.equals(ReceiverAction.RECEIVER_ACTION_ADD))
            {

            }
        }
    }

    class ReceiverAction{
        public static final String RECEIVER_ACTION_ADD = "receiver_action_add";
    }
}
