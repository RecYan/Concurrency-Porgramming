package com.recyan.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Yan_Jiang on 2018/10/10.
 * 同步代码块
 */
@Slf4j
public class SynchronizedExample1 {

    //修饰代码块
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test2 {} - {}", j, i);
            }
        }
    }

    public synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test1 - {}", i);
        }
    }

    public static void main(String[] args) {

        SynchronizedExample1 sync1 = new SynchronizedExample1();
        SynchronizedExample1 sync2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool(); //线程池

        executorService.execute(() -> {
            sync1.test1(1);
        });
        executorService.execute(() -> {
            sync2.test1(2);
        });
    }

}
