package com.example.designpattern.abstractfactory.factory.impl;

import com.example.designpattern.abstractfactory.factory.Provider;
import com.example.designpattern.factorymethod.model.Sender;
import com.example.designpattern.factorymethod.model.impl.SmsSneder;

/**
 * Created by bing.zhao on 2016/11/30.
 */

public class SmsSenderFactory implements Provider {
    @Override
    public Sender produce() {
        return new SmsSneder();
    }
}
