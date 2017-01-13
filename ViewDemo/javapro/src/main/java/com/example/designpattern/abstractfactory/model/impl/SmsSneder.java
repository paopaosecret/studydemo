package com.example.designpattern.abstractfactory.model.impl;

import com.example.designpattern.abstractfactory.model.Sender;

/**
 * Created by bing.zhao on 2016/11/30.
 */

public class SmsSneder implements Sender {
    @Override
    public void send() {
        System.out.println("SmsSneder -> send");
    }
}
