package com.example.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaobing04 on 2018/4/26.
 */

public class WeChatServer implements Observerable {

    private List<Observer> mObserverList;
    private String message;

    public WeChatServer(){
        mObserverList = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        mObserverList.add(observer);
    }

    @Override
    public void unRegisterObserver(Observer observer) {
        if(!mObserverList.isEmpty()){
            mObserverList.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        if(!mObserverList.isEmpty()){
            for(Observer observer:mObserverList){
                observer.update(message);
            }
        }
    }

    public void setMessage(String s) {
        this.message = s;
        System.out.println("微信服务更新消息： " + s);
        //消息更新，通知所有观察者
        notifyObserver();
    }
}
