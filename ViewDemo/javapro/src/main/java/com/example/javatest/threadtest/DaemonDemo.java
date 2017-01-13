package com.example.javatest.threadtest;

/**
 * Created by bing.zhao on 2017/1/6.
 */

public class DaemonDemo {

    public static class DaemonT extends Thread{

        @Override
        public void run() {
            while(true){
                System.out.println("i am alive");
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        Thread t = new DaemonT();
        t.setDaemon(true);   //设置为守护线程，当用户线程全部完成之后，守护线程也会跟着结束，例如该守护线程虽然死循环但在main线程2s结束，t1线程12s结束之后，它也会跟着结束
        t.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t1 = new Thread(){

            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(12000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
    }
}
