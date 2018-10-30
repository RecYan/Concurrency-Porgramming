package MutiThreadProgramming.ch3;

/**
 * Created by Yan_Jiang on 2018/10/30.
 *  双重检查锁定与延时初始化
 *      线程安全延时初始化案例 -- 双重检查锁定
 */
public class SafeDoubleCheckedLocking {

    private volatile static Instance instance; //volatile内存语义  禁止2、3步骤 可能会发生重排序

    public static Instance getInstance() {

        if(instance == null) { //第一次检查 -- 如果不符合条件 则会降低synchronized带来的性能开销
            synchronized (SafeDoubleCheckedLocking.class) { //加锁
                if(instance == null) { //第二次检查 -- 多个线程在同一时间创建对象时， 会通过加锁来保证只有一个线程能够创建对象
                    instance = new Instance(); //出现问题根源 -- 可能出现重排序状况
                    /**即 instance = new Instance(); 可分为下面三步
                     * 1. memory = allocate()
                     * 2. ctorInstance(memory) //初始化对象
                     * 3. instance = memory // instance指向分配的地址
                     * 其中 2、3步骤 可能会发生重排序
                     */
                }

            }

        }
        return instance;
    }

}
