package com.xbing.com.viewdemo.service.net.builder;


import com.xbing.com.viewdemo.service.net.OkHttpUtils;
import com.xbing.com.viewdemo.service.net.request.OtherRequest;
import com.xbing.com.viewdemo.service.net.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
