package com.xbing.com.viewdemo.moudle.mvp.contract;

import com.xbing.com.viewdemo.moudle.mvp.model.IBaseModel;
import com.xbing.com.viewdemo.moudle.mvp.presenter.IBasePresenter;
import com.xbing.com.viewdemo.moudle.mvp.view.IBaseView;

/**
 * Created by zhaobing04 on 2018/3/19.
 */

public interface ILoginContract {

    interface ILoginView extends IBaseView{

    }

    interface ILoginPresenter extends IBasePresenter{
        void login(String name,String pwd);
        void onLoginSuccess();

        void addUser();
    }

    interface ILoginModel extends IBaseModel{
        void login(String name, String pwd);

        void addUser();
    }
}
