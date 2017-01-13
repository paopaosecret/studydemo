package com.example.designpattern.Singleton;


/**
 * Created by bing.zhao on 2016/11/30.
 */

public class Singleton {

    /**
     * 1.构造器私有化，不允许别人new实例
     */
    private Singleton(){
    }

    /**
     * 2.声明一个公外部调用的单例
     */
    private static Singleton instance;

    /**
     * 2.实现上述单例
     * @return
     */
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (instance) {       //保证线程安全
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
