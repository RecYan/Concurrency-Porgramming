package MutiThreadProgramming.ch3;

/**
 * Created by Yan_Jiang on 2018/10/30.
 * 基于类的初始化优化 -- 解决双重检查锁定的延时初始化问题
 */
public class InstanceFactory {

    private static class InstanceHolder {
        public static Instance instance = new Instance();
    }

    public static Instance getInstance() {
        return InstanceHolder.instance; //这里 InstanceHolder类 将会被初始化
    }
}
