package com.example.designpattern.abstractfactory.factory.impl;

import com.example.designpattern.abstractfactory.factory.Provider;
import com.example.designpattern.factorymethod.model.Sender;
import com.example.designpattern.factorymethod.model.impl.QQSender;

/**
 * Created by bing.zhao on 2016/11/30.
 */

public class QQSenderFactory implements Provider {
    @Override
    public Sender produce() {
        return new QQSender();
    }
}
