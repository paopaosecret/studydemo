package com.xbing.com.viewdemo.manager;

import android.drm.DrmStore;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.xbing.com.viewdemo.manager.iml.AccountManagerIml;
import com.xbing.com.viewdemo.model.result.UserDetailResult;
import com.xbing.com.viewdemo.service.net.ApiConnection;

import java.net.MalformedURLException;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by zhaobing04 on 2018/5/7.
 */

public class RxApi {
    private static final String TAG = "RxActivity";
    private static RxApi instance;
    private IAccountManager accountManager;
    private RxApi(){
        accountManager = new AccountManagerIml();
    }

    public static RxApi getInstance(){
        if(instance == null){
            instance = new RxApi();
        }

        return instance;
    }

    public  Observable<UserDetailResult> getApiDetail() {
         return  Observable.create(new Observable.OnSubscribe<UserDetailResult>() {
            @Override
            public void call(Subscriber<? super UserDetailResult> subscriber) {

                try {
                    String responseData = getAccountDetailsFromApi();
                    Log.e(TAG,"RxApi response:" + responseData);
                    if (!TextUtils.isEmpty(responseData)) {
                        Gson gson = new Gson();
                        UserDetailResult result = gson.fromJson(responseData, UserDetailResult.class);
                        subscriber.onNext(result);
                        subscriber.onCompleted();
                    } else {
                        Log.e(TAG,"RxApi error");
                    }
                } catch (Exception e) {
                    Log.e(TAG,"RxApi error");
                }

            }
        });

    }

    private String getAccountDetailsFromApi() throws MalformedURLException {
        String apiUrl = "https://api.github.com/users/mutexkid";
        return ApiConnection.create(apiUrl).requestSyncGetCall();
    }
}
