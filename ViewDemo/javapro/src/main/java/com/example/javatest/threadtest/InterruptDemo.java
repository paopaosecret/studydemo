package com.example.javatest.threadtest;

/**
 * Created by bing.zhao on 2017/1/6.
 */

public class InterruptDemo {
    static class InterruptT extends Thread{

        @Override
        public void run() {
            while (true){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("i an Interrupted");
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //中断异常之后会重置中断位，所以需要重新设置中断
                    Thread.currentThread().interrupt();
                }
                System.out.println("i am run");
            }
        }
    }

    public static void main(String[] args){
        InterruptT t1 = new InterruptT();
        t1.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }
}
