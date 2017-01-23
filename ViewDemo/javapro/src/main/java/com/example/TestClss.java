package com.example;


import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by bing.zhao on 2017/1/13.
 */

public class TestClss {

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
