package com.xbing.com.viewdemo.moudle.mvp.view;

/**
 * Created by zhaobing04 on 2018/3/19.
 */

public interface IBaseView {
    void initView();
    void initData();
    void initListener();
    void showToast(String msg);
}
