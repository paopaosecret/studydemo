package com.example.javatest.aop;

/**
 * Created by bing.zhao on 2017/5/8.
 */

public class JavaMain {

    public static void main(String[] args){
        // 绑定代理，这种方式会在所有的方法都加上切面方法
        ITalker iTalk = (ITalker) new ProxyTalker().bind(new Talker());
        iTalk.talk();
    }
}
