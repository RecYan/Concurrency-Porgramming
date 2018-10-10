package com.recyan.concurrency.example.Atomic;

import com.recyan.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;


/**
 * Created by Yan_Jiang on 2018/10/8.
 * 使用代码来模拟并发请求
 */
@Slf4j
@ThreadSafe
public class AtomicReferenceExample {

    public static AtomicReference<Integer> count = new AtomicReference<>(0);


    public static void main(String[] args) throws Exception {

        count.compareAndSet(0, 1);
        count.compareAndSet(2, 4);
        count.compareAndSet(1, 5);

        System.out.println(count.get());

    }
}
