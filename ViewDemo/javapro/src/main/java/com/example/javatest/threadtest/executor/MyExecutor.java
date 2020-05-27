package com.example.javatest.threadtest.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by bing.zhao on 2017/1/6.
 */

public class MyExecutor {

    private ThreadPoolExecutor mThreadPoolExecutor;

    /* 重要 最大线程数量  不是核心线程数量个数加上等待队列的任务个数的最大值
     * 而是 线程池允许运行的最大线程数量 **/

    private static final int MAX_THREAD_COUNT = 2;            //线程池最大线程数量，同一时刻线程池（允许运行-这点很重要）的最大数量，如果大于该数量，执行拒绝策略
    private static final int CORE_THREAD_COUNT = 2;           //线程池核心线程数量，线程池初始化的时候创建的线程数量
    private static final long KEEP_ALIVE_TIME = 0;         //空闲线程存活时间（当线程）keepAliveTime 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间
    private static final int WAIT_THREAD_COUNT = 3;           //等待队列线程数量

    /**
     * 等待线程队列:有界的任务队列
     * 当有新的任务需要执行，调用execute（runnable）;
     * 1.当线程池中线程数量小于核心数量CORE_THREAD_COUNT，则优先创建线程
     * 2.当线程池中线程数量大于核心数量，则会将任务加入等待队列
     * 3.如果等待队列已满，如果总线程数不大于最大线程数量MAX_THREAD_COUNT，创建新的线程执行该新任务
     * 4.如果总线程数大于最大线程数量，则执行拒绝策略
     */
//    private ArrayBlockingQueue<Runnable> mWaitQueue = new ArrayBlockingQueue<Runnable>(WAIT_THREAD_COUNT);
    private LinkedBlockingQueue<Runnable> mWaitQueue = new LinkedBlockingQueue<Runnable>(5);

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
    private static Object locked = new Object();

    public static MyExecutor getInstance(){
        if(instatce == null){
            synchronized (locked){
                if(instatce == null){
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
                TimeUnit.MILLISECONDS,
                mWaitQueue,
                mThreadFactory,
//                new ThreadPoolExecutor.DiscardPolicy()    //线城池达到最大线程数量，再接收到任务执行丢弃策略，直接丢弃
//                new ThreadPoolExecutor.DiscardOldestPolicy() //线城池达到最大线程数量，再接收到任务执行丢弃策略，丢弃老的任务
                new ThreadPoolExecutor.AbortPolicy() //线城池达到最大线程数量，再接收到任务执行则提示异常
//                new ThreadPoolExecutor.CallerRunsPolicy()   //线城池达到最大线程数量，再接收到任务 在execute 方法的调用线程中执行任务
        );
    }

    public void executeTask(Runnable task){
        mThreadPoolExecutor.execute(task);
    }
}
