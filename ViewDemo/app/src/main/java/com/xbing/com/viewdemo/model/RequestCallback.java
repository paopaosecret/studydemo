package com.xbing.com.viewdemo.model;

import com.xbing.com.viewdemo.model.result.RequestResult;

/**
 * Created by zhaobing on 2016/9/24.
 */
public interface RequestCallback {
    void onRequestComplete(RequestResult result);
}
