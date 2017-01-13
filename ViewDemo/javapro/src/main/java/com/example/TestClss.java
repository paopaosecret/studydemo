package com.example;

/**
 * Created by bing.zhao on 2017/1/13.
 */

public class TestClss {

    public static void main(String[] args){

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }
        };


        Thread t = new Thread(r){

            @Override
            public void run() {
                System.out.println("thread");
            }
        };

        t.start();
    }
}
