package com.example.javatest.threadtest.executor;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by bing.zhao on 2017/1/6.
 */

public class MyExecutor {

    private ThreadPoolExecutor mThreadPoolExecutor;

    private static final int MAX_THREAD_COUNT = 5;            //线程池最大线程数量，同一时刻线程池允许运行的最大数量，如果大于该数量，执行拒绝策略
    private static final int CORE_THREAD_COUNT = 3;           //线程池核心线程数量，线程池初始化的时候创建的线程数量
    private static final long KEEP_ALIVE_TIME = 3600;         //等待队列的任务存活时间长度
    private static final int WAIT_THREAD_COUNT = 2;           //等待队列数量

    /**
     * 等待线程队列:有界的任务队列
     * 当有新的任务需要执行，调用execute（runnable）;
     * 1.当线程池中线程数量小于核心数量CORE_THREAD_COUNT，则优先创建线程
     * 2.当线程池中线程数量大于核心数量，则会将任务加入等待队列
     * 3.如果等待队列已满，如果总线程数不大于最大线程数量MAX_THREAD_COUNT，创建新的线程执行该新任务
     * 4.如果总线程数大于最大线程数量，则执行拒绝策略
     */
    private ArrayBlockingQueue<Runnable> mWaitQueue = new ArrayBlockingQueue<Runnable>(WAIT_THREAD_COUNT);

    /**
     * 线程池使用其创建新的线程
     */
    private ThreadFactory mThreadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);                //线程池中线程全设置为守护线程，当主线程退出后线程池中线程也全部结束
            System.out.println("create " + t );
            return t;
        }
    };


    private static MyExecutor instatce;

    public static MyExecutor getInstance(){
        if(instatce != null){
            synchronized (instatce){
                if(instatce != null){
                    instatce = new MyExecutor();
                }
            }
        }
        return instatce;
    }

    private MyExecutor(){
        mThreadPoolExecutor = new ThreadPoolExecutor(
                CORE_THREAD_COUNT,
                MAX_THREAD_COUNT,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                mWaitQueue,
                mThreadFactory,
                new ThreadPoolExecutor.DiscardPolicy()    //线城池达到最大线程数量，再接收到任务直接丢弃
        );
    }

    public void executeTask(Runnable task){
        mThreadPoolExecutor.execute(task);
    }
}
