package com.example.designpattern.observer;

/**
 * Created by zhaobing04 on 2018/4/26.
 */

public class User implements Observer {

    private String name;
    private String message;

    public User(String name){
        this.name = name;
    }

    @Override
    public void update(String msg) {
        this.message = msg;
        read();
    }

    private void read() {
        System.out.println(name + "收到消息：" + message);
    }
}
