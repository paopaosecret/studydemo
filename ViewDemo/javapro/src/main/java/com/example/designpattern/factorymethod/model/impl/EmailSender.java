package com.example.designpattern.factorymethod.model.impl;

import com.example.designpattern.factorymethod.model.Sender;

/**
 * Created by bing.zhao on 2016/11/30.
 */

public class EmailSender implements Sender {
    @Override
    public void send() {
        System.out.println("EmailSender -> send");
    }
}
