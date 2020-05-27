package com.example.designpattern.observer;

/**
 * Created by zhaobing04 on 2018/4/26.
 */

public class JavaTest {
    public static void main(String[] args){
        WeChatServer server = new WeChatServer();

        Observer zhangsan = new User("zhangsan");
        Observer lisi = new User("lisi");
        Observer wangwu = new User("wangwu");

        server.registerObserver(zhangsan);
        server.registerObserver(lisi);
        server.registerObserver(wangwu);

        server.setMessage("你好");

        System.out.println("---------------------------------");
        server.unRegisterObserver(zhangsan);
        System.out.println("---------------------------");
        server.setMessage("再见");
    }
}
