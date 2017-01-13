package com.example.javatest.innerclass;

/**
 * Created by bing.zhao on 2017/1/10.
 */

public class MainTest {

    public static void main(String[] arg){
        Goods goods = new Goods();

        /**
         * 隐藏了内部类MyContetn和MyDestination的具体实现
         */
        Content content = goods.getContent();
        Destination destination = goods.getDestination("shenzhen");

        Content content1 = new Goods().new MyContent();
        System.out.println("content.value=" + content.value() + ",destination.label=" + destination.readLabel());
    }
}
