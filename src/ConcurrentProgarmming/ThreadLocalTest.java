package ConcurrentProgarmming;

/**
 * Created by Yan_Jiang on 2018/10/24.
 * ThreadLocal测试
 */
public class ThreadLocalTest {

    private static ThreadLocal<String> t = new ThreadLocal<>();


    public static void main(String[] args) {

        if(t.get() == null) {
            System.out.println("为ThreadLocal类对象放入值:aaa");
            t.set("aaa");
        }
        System.out.println(t.get());
        System.out.println(t.get());
    }
    //initialValue() 为ThreadLocal设置初始值
    static public class ThreadLocalExt extends ThreadLocal {
        @Override
        protected Object initialValue() {
            return "我是默认值 第一次get不再为null";
        }
    }
}
