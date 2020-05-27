package com.example.thinkinjava.charpter_fifteen;

import com.example.thinkinjava.charpter_fifteen.bean.Apple;
import com.example.thinkinjava.charpter_fifteen.bean.Fruit;
import com.example.thinkinjava.charpter_fifteen.bean.Orange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhaobing04 on 2018/4/17.
 *
 * 泛型类
 */
public class Manager<T> {

    ArrayList<Integer> f;
    public T getState() {
        return state;
    }

    public void setState(T state) {
        this.state = state;
    }

    private T state;

    public void printf(T... args){
        if(args != null && args.length>0){
            for(T item: args){
                System.out.println(item.toString());
            }
        }
    }

    public static void main(String[] args){
        Manager manager = new Manager();
        manager.getState();

        List<? super Fruit> objectList = new ArrayList<Object>();
        Apple apple = new Apple();
        objectList.add(apple);
        Orange orange = new Orange();
        objectList.add(orange);

        List<? extends Fruit> list = Arrays.asList(new Apple());

       Apple apple1 = (Apple)list.get(0);
    }
}
