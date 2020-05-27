package com.xbing.com.viewdemo.moudle.mvp.model;

import android.app.Activity;
import android.content.Context;

import com.xbing.com.viewdemo.db.greenDao.Bean.UserBean;
import com.xbing.com.viewdemo.db.greenDao.DaoManager;
import com.xbing.com.viewdemo.moudle.mvp.contract.ILoginContract;
import com.xbing.com.viewdemo.moudle.mvp.presenter.LoginPresenter;

/**
 * Created by zhaobing04 on 2018/3/19.
 */

public class LoginModel implements ILoginContract.ILoginModel {
    private Context mContext;
    private LoginPresenter mPresenter;

    public LoginModel(Context activity, LoginPresenter presenter){
        this.mContext = activity;
        this.mPresenter = presenter;
    }

    @Override
    public void login(String name, String pwd) {
        mPresenter.onLoginSuccess();
    }

    @Override
    public void addUser() {
        UserBean userBean= new UserBean(1,"zhangsan",1,"13510578961");
        DaoManager.getInstance().getDaoSession().getUserBeanDao().insert(userBean);
    }
}
