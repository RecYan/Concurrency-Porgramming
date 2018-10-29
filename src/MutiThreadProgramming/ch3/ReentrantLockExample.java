package MutiThreadProgramming.ch3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yan_Jiang on 2018/10/29.
 * 锁内存语义实现的机制 --- ReetarntLock源码分析
 *      实现依赖于AQS: java同步器框架 其使用一个整形的volatile变量[state]来维护同步状态
 *         查看ReentrantLock类图
 */
public class ReentrantLockExample {

    int a = 0;
    ReentrantLock lock = new ReentrantLock();

    public void writer() {

        lock.lock();
        try {
            a++;
        } finally {
            lock.unlock();
        }
    }

    public void reader() {
        lock.lock();
        try {
            int i = a;
            // ...
        } finally {
            lock.unlock();
        }
    }
}
