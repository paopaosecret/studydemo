package com.example.javatest.threadtest.executor;

/**
 * Created by bing.zhao on 2017/1/6.
 */

public class ExecutrorTest {

    public static void main(String[] args){
        MyExecutor myExecutor = MyExecutor.getInstance();

        for(int i= 0; i<= 10000;i++){

            Task t = new Task(i);
            myExecutor.executeTask(t);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        Task t2 = new Task(2);
//        Task t3 = new Task(3);
//        Task t4 = new Task(4);
//        Task t5 = new Task(5);
//        Task t6 = new Task(6);
//        Task t7 = new Task(7);
//        Task t8 = new Task(8);
//        Task t9 = new Task(9);
//        Task t10 = new Task(10);
//
//
//        myExecutor.executeTask(t2);
//        myExecutor.executeTask(t3);
//        myExecutor.executeTask(t4);
//        myExecutor.executeTask(t5);
//        myExecutor.executeTask(t6);
//        myExecutor.executeTask(t7);
//        myExecutor.executeTask(t8);
//        myExecutor.executeTask(t9);
//        myExecutor.executeTask(t10);

        try {
            Thread.sleep(1500000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    static class Task implements Runnable{
        public int index = 0;
        public Task(int i){
            index = i;
        }

        @Override
        public void run() {
//            System.out.println("start current thread:" + Thread.currentThread().toString() + ",index=" + index);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end current thread:" + Thread.currentThread().toString() + ",index=" + index);
        }
    }
}
