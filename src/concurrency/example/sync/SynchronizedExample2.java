package com.recyan.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Yan_Jiang on 2018/10/10.
 * 同步代码块
 */
@Slf4j
public class SynchronizedExample2 {

    //修饰类
    public  static void test1(int j) {
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} - {}", j, i);
            }
        }
    }

    //修饰一个静态方法
    public static synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - {}", i);
        }
    }

    public static void main(String[] args) {

        SynchronizedExample2 sync1 = new SynchronizedExample2();
        SynchronizedExample2 sync2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool(); //线程池

        executorService.execute(() -> {
            sync1.test1(1);
        });
        executorService.execute(() -> {
            sync2.test1(2);
        });
    }

}
