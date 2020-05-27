package com.example;

/**
 * Created by zhaobing04 on 2018/4/16.
 */

public class JavaP {
    public static final int a = 10;
    public static final String b = "hello";
    public int c = 20;
    public String d = "world";

    public static void main(String[] args){
        System.out.println(b + a);
        JavaP p = new JavaP();
        System.out.println(p.d + p.c);
    }
}
