package MutiThreadProgramming.ch3;

/**
 * Created by Yan_Jiang on 2018/10/30.
 *  双重检查锁定与延时初始化
 *      线程安全延时初始化案例 -- 改进一
 */
public class SafeLazyInitialization {

    private static Instance instance;

    public synchronized static Instance getInstance() {  // 在单线程是性能得到保障，多线程时性能则急剧下降
        if(instance == null) { //A线程
            instance = new Instance(); //B线程
        }
        return instance;
    }

}
