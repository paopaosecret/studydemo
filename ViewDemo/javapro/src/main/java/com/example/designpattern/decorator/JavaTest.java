package com.example.designpattern.decorator;

/**
 * Created by zhaobing04 on 2018/4/23.
 */

public class JavaTest {

    @Deprecated
    public static void main(String args[]){
        Person person = new Person();
        Decorator decorator = new Decorator(new Clothes(new Shoes(person)));
        decorator.show();
    }
}
