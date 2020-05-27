package com.example.designpattern.observer;

/**
 * Created by zhaobing04 on 2018/4/26.
 *
 * 抽象一个观察者接口
 * 定义了update()方法，当被观察者调用notifyObserver()方法时，观察者update()方法会被回调
 */

public interface Observer {
    void update(String msg);
}
