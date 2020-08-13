package com.neo.thread;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @date 2020/8/1 11:52
 * @desc
 */
public class ThreadPoolTest {

    /**
     * 测试自定义线程池
     */
    @Test
    public void test_001() throws Exception{
        ExecutorService executorService = new RecordTimeThreadPool(5, 5, 500, TimeUnit.SECONDS,
                new ArrayBlockingQueue(1000));

        for(int i = 0;i<100;i++){

            executorService.submit(()->{
                System.out.println("任务执行中");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            });

        }
        TimeUnit.SECONDS.sleep(1000);
    }
}
