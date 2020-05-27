package com.example.designpattern.reuseclass.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhaobing04 on 2018/4/12.
 */

public class SleepInvocationHandler implements InvocationHandler {

    private Object mSleep;

    public SleepInvocationHandler(Object sleep){
        this.mSleep = sleep;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("dynamic proxy -------------start---------------");
        method.invoke(mSleep,objects);
        System.out.println("dynamic proxy -------------start---------------");
        return null;
    }

    public Object getSleepProxy(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?>[] interfaces = mSleep.getClass().getInterfaces();
        return Proxy.newProxyInstance(loader,interfaces,this);
    }

    public static void main(String[] args){
        ISleep sleep = new SleepImpl();
        SleepInvocationHandler sleepInvocationHandler = new SleepInvocationHandler(sleep);
        ISleep sleep1 = (ISleep)sleepInvocationHandler.getSleepProxy();
        sleep1.sleep();
    }
}
