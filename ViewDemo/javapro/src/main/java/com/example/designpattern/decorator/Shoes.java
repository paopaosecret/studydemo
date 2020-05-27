package com.example.designpattern.decorator;

/**
 * Created by zhaobing04 on 2018/4/23.
 */

public class Shoes extends Decorator {

    public Shoes(Show show) {
        super(show);
    }

    public void show(){
        System.out.println("穿上骚气的鞋子");
        this.mBase.show();
    }
}
