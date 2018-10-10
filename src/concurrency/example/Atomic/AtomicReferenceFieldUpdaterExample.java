package com.recyan.concurrency.example.Atomic;

import com.recyan.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;


/**
 * Created by Yan_Jiang on 2018/10/8.
 * 使用代码来模拟并发请求
 */
@Slf4j
@ThreadSafe
public class AtomicReferenceFieldUpdaterExample {

    private static AtomicIntegerFieldUpdater<AtomicReferenceFieldUpdaterExample> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicReferenceFieldUpdaterExample.class, "count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) throws Exception {

        AtomicReferenceFieldUpdaterExample atomicReferenceFieldUpdaterExample =
                new AtomicReferenceFieldUpdaterExample();

       if (updater.compareAndSet(atomicReferenceFieldUpdaterExample, 100, 120)) {
            log.info("success1, {}", atomicReferenceFieldUpdaterExample.getCount());
       } if (updater.compareAndSet(atomicReferenceFieldUpdaterExample, 100, 120)) {
            log.info("success2, {}", atomicReferenceFieldUpdaterExample.getCount());
        } else {
            log.info("faild1, {}", atomicReferenceFieldUpdaterExample.getCount());
        }

    }
}
