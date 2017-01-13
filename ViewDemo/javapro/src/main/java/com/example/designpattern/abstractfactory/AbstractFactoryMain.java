package com.example.designpattern.abstractfactory;

import com.example.designpattern.abstractfactory.factory.Provider;
import com.example.designpattern.abstractfactory.factory.impl.SmsSenderFactory;
import com.example.designpattern.factorymethod.model.Sender;

/**
 * Created by bing.zhao on 2016/11/30.
 */

public class AbstractFactoryMain {

    public static void main(String args[]){
        Provider provider = new SmsSenderFactory();
        Sender sender = provider.produce();
        sender.send();
    }
}
