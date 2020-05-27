package com.example.javatest.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhaobing04 on 2018/5/8.
 */

public class Lambda {


    public static void main(String args[]){
        //命令式的程序设计，习惯于创建对象或变量，通过一些指令，修改他们状态的值
        List<String> list = Arrays.asList("zhangsan","lisi","wangwu","lambda","Streamapi");
        System.out.println("--------java 8之前------------------------------");
        for(String str : list){
            System.out.println(str);
        }

        //函数式声明编程
        System.out.println("\n\n--------java 8之后------------------------------");
        Arrays.asList("zhangsan","lisi","wangwu","lambda","Streamapi").forEach(n-> System.out.println(n));


        System.out.println("\n\n");


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("java8 之前的线程方式");
            }
        }).start();

        System.out.println("\n\n");
        new Thread(() -> {System.out.println("java8 之后的线程方式");}).start();

        Runnable run = () -> System.out.println("定义一个Runnable的Lambda函数");

        //函数式编程关心数据的映射，命令式编程关心解决问题的步骤
    }
}
