package com.xbing.com.viewdemo.moudle.mvp.bean;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zhaobing04 on 2018/4/18.
 */

public class JsonBusinessDataBean<T> implements Serializable {

    private String function;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    private T params;

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "BusinessDataBean{" +
                "params=" + params +
                '}';
    }
}
