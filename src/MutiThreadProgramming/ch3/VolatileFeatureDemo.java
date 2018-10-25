package MutiThreadProgramming.ch3;

/**
 * Created by Yan_Jiang on 2018/10/25.
 * volatile特性理解
 *      即对单个变量的读写 等价与该变量使用同一把锁进行相同操作
 *          对一个共享变量的读，总能看到（任意线程）对该共享变量最后的写入
 */
public class VolatileFeatureDemo {

    volatile long vl = 0L;

    public void set(long vl) {
        vl = vl;
    }
    /*public synchronized void set(long vl) {
        vl = vl;
    }*/

    public void getAndIncrement() {
        vl ++;
    }
    /*public synchronized void getAndIncrement() {
        long temp = get();
        temp = temp + 1L;
        set(temp);
    }*/

    public long get() {
        return vl;
    }
    /*public synchronized long get() {
        return vl;
    }*/
}
