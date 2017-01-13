package com.example.designpattern.abstractfactory.factory;

import com.example.designpattern.factorymethod.model.Sender;

/**
 * Created by bing.zhao on 2016/11/30.
 */

public interface Provider {
    Sender produce();
}
