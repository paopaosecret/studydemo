package com.example.designpattern.Singleton;

/**
 * Created by bing.zhao on 2016/11/30.
 */

public class Singleton2{

    private static class SingletonHolder {
        private static final Singleton2 INSTANCE = new Singleton2();
    }

    private Singleton2 (){

    }
    public static final Singleton2 getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
