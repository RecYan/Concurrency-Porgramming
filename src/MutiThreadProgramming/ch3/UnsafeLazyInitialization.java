package MutiThreadProgramming.ch3;

/**
 * Created by Yan_Jiang on 2018/10/30.
 *  双重检查锁定与延时初始化
 *      非线程安全延时初始化案例
 */
public class UnsafeLazyInitialization {

    private static Instance instance;

    public static Instance getInstance() {
        if(instance == null) { //A线程
            instance = new Instance(); //B线程
        }
        return instance;
    }

}
