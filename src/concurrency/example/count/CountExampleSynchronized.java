package com.recyan.concurrency.example.count;

import com.recyan.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Yan_Jiang on 2018/10/8.
 * 使用代码来模拟并发请求
 */
@Slf4j
@NotThreadSafe
public class CountExampleSynchronized {

    public static int clientTotal = 5000; //请求总数

    public static int threadTotal = 200; //并发线程数

    public static int count = 0; //计数器


    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool(); //定义一个线程池
        final Semaphore semaphore = new Semaphore(threadTotal); //同一时间请求量
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal); //请求总数

        for (int i = 0; i < clientTotal; i++) {
            //Lamada表达式
            executorService.execute(() -> {

                try {
                    semaphore.acquire(); //信号量
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown(); //闭锁
            });
        }
        countDownLatch.await();
        executorService.shutdown(); //关闭线程池
        log.info("count:{}" + count);
    }

    //计数方法
    private static synchronized void add() {
        count ++;
    }

}
