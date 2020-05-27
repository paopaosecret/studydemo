package com.example;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by bing.zhao on 2017/1/13.
 */

public class TestClss implements InvocationHandler {

    /** 需要代理的目标类 */
    private Object target;

    /**
     * 写法固定，aop专用:绑定委托对象并返回一个代理类
     *
     * @param target
     * @return
     */
    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Object result = null;
        // 切面之前执行
        System.out.println("切面之前执行");
        // 执行业务
        result = method.invoke(target, objects);
        // 切面之后执行
        System.out.println("切面之后执行");

        return result;
    }

    static class Number{

        int i;

        public Number(int i) {
            this.i = i;
        }
    }

    public static void main(String[] args){

//        Set<Object> sets = new TreeSet<>();
//        sets.add("zhangsna");
//        sets.add("lisi");
//        sets.add("wanglaowu");
//        sets.add("heizi");
//        sets.add("laoliu");
//        sets.add("lol");
//        sets.add("zhaosi");

//        java.util.Iterator  iterator = sets.iterator();
//        while(iterator.hasNext()){
//            String i = (String) iterator.next();
//            System.out.println("index:" + i);
//        }

//        System.out.println(sets);

        Number number = new Number(10);

        Number number1 =number;
        number1.i = 11;
        System.out.println("i=" + number.i);

        add(number);
        System.out.println("i=" + number.i);
    }

    static void add(Number number){
        number.i++;
    }
}
