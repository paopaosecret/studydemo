package com.example.designpattern.decorator;

/**
 * Created by zhaobing04 on 2018/4/23.
 */

public class Person implements Show{
    @Override
    public void show() {
        System.out.println("打扮自己帅气一点");
    }
}
