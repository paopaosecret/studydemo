package com.xbing.com.viewdemo.moudle.mvp.presenter;

import android.app.Activity;
import android.content.Context;

import com.xbing.com.viewdemo.moudle.mvp.contract.ILoginContract;
import com.xbing.com.viewdemo.moudle.mvp.model.LoginModel;
import com.xbing.com.viewdemo.moudle.mvp.view.ILoginView;

/**
 * Created by zhaobing04 on 2018/3/19.
 */

public class LoginPresenter implements ILoginContract.ILoginPresenter {
    private ILoginView mView;
    private ILoginContract.ILoginModel mModel;

    private Context mContext;

    public LoginPresenter(Context context,ILoginView view){
        this.mContext = context;
        mView = view;
        mModel = new LoginModel(mContext,this);
    }

    @Override
    public void login(String name,String pwd) {
        mModel.login(name,pwd);
    }

    @Override
    public void onLoginSuccess() {
        mView.updateTextView();
    }

    @Override
    public void addUser() {
        mModel.addUser();
    }
}
