package com.example.designpattern.factorymethod.factory;

import com.example.designpattern.factorymethod.model.Sender;
import com.example.designpattern.factorymethod.model.impl.EmailSender;
import com.example.designpattern.factorymethod.model.impl.QQSender;
import com.example.designpattern.factorymethod.model.impl.SmsSneder;

/**
 * Created by bing.zhao on 2016/11/30.
 *
 */

public class SenderFactory {

    public static Sender produceSmsSender(){
        return new SmsSneder();
    }

    public static Sender produceQQSender(){
        return new QQSender();
    }

    public static Sender pruduceEmailSender(){
        return new EmailSender();
    }
}
