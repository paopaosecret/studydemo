package com.example.designpattern.observer;

/**
 * Created by zhaobing04 on 2018/4/26.
 *
 * 抽象被观察者
 * 定义注册，注销，通知观察者三个方法
 */

public interface Observerable {

    void registerObserver(Observer observer);
    void unRegisterObserver(Observer observer);
    void notifyObserver();

}


