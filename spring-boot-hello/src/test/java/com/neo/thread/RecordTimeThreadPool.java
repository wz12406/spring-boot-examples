package com.neo.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @date 2020/8/1 8:33
 * @desc 拓展线程池，实现记录任务执行时间、和平均响应时间的线程池
 */
public class RecordTimeThreadPool extends ThreadPoolExecutor {

    protected AtomicInteger maxExeTime = new AtomicInteger(0);
    protected AtomicInteger minExeTime = new AtomicInteger(0);
    /**
     * 平均执行时间
     */
    protected AtomicInteger avgExeTime = new AtomicInteger(0);
    /**
     * 总执行时间
     */
    protected AtomicInteger totalExeTime = new AtomicInteger(0);
    /**
     * 执行次数
     */
    protected AtomicInteger count = new AtomicInteger(0);



    public RecordTimeThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("开始执行任务----"+System.currentTimeMillis());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("任务执行完成----"+System.currentTimeMillis());

    }
}
