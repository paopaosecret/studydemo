package com.xbing.com.viewdemo.moudle.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xbing.com.viewdemo.moudle.mvp.presenter.IBasePresenter;
import com.xbing.com.viewdemo.moudle.mvp.view.IBaseView;

/**
 * Created by zhaobing04 on 2018/3/19.
 */

public abstract class BaseMvpActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {

    protected P mPresenter;

    protected abstract P initPresenter();

    protected abstract int getLayoutResId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        if(mPresenter == null){
            mPresenter = initPresenter();
        }
    }


}
