package com.recyan.ch2;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Yan_Jiang on 2018/8/6.
 * 测试使用循环CAS来实现原子操作
 */
public class Counter {

    private AtomicInteger atmoticI = new AtomicInteger(0);

    private int i = 0;

    public static void main(String[] args) {

        final Counter cas = new Counter();
        ArrayList<Thread> ts = new ArrayList<>(600);
        long satrt = System.currentTimeMillis();

        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }

        for (Thread t : ts) {
            t.start();
        }
        //等待所有线程执行完成
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(cas.i);
        System.out.println(cas.atmoticI.get());
        System.out.println(System.currentTimeMillis() - satrt);
    }

    // 使用CAS实现线程安全的计数器
    private void safeCount() {
        for (; ; ) {
            int i = atmoticI.get(); //get() 获取设置的初始值
            boolean suc = atmoticI.compareAndSet(i, ++i); //如果当前值==预期值，则原子性地将值设置为给定的更新值 compareAndSet(int expect int update)
            if (suc) {
                break;
            }
        }
    }

    // 非线程安全计数器
    private void count() {
        i++;
    }
}
