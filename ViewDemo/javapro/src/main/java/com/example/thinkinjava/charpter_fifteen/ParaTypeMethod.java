package com.example.thinkinjava.charpter_fifteen;

/**
 * Created by zhaobing04 on 2018/4/17.
 *
 * 泛型方法
 */

public class ParaTypeMethod {

    public <T> void printf(T bean){
       System.out.println(bean.getClass().getSimpleName());
    }
}
