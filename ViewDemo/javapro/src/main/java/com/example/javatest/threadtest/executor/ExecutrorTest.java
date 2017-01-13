package com.example.javatest.threadtest.executor;

/**
 * Created by bing.zhao on 2017/1/6.
 */

public class ExecutrorTest {

    public static void main(String[] args){
        MyExecutor myExecutor = MyExecutor.getInstance();

        Task t1 = new Task(1);
        Task t2 = new Task(2);
        Task t3 = new Task(3);
        Task t4 = new Task(4);
        Task t5 = new Task(5);
        Task t6 = new Task(6);
        Task t7 = new Task(7);
        Task t8 = new Task(8);
        Task t9 = new Task(9);
        Task t10 = new Task(10);

        myExecutor.executeTask(t1);
        myExecutor.executeTask(t2);
        myExecutor.executeTask(t3);
        myExecutor.executeTask(t4);
        myExecutor.executeTask(t5);
        myExecutor.executeTask(t6);
        myExecutor.executeTask(t7);
        myExecutor.executeTask(t8);
        myExecutor.executeTask(t9);
        myExecutor.executeTask(t10);

        try {
            Thread.sleep(15000);
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
            System.out.println("current thread:" + Thread.currentThread().toString() + ",index=" + index);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
