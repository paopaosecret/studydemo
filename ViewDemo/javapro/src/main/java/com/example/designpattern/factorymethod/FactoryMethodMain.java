package com.example.designpattern.factorymethod;

import com.example.designpattern.factorymethod.factory.SenderFactory;
import com.example.designpattern.factorymethod.model.Sender;

/**
 * Created by bing.zhao on 2016/11/30.
 */

public class FactoryMethodMain {

    public static void main(String args[]){
        Sender sender = SenderFactory.produceQQSender();
        sender.send();
    }
}
