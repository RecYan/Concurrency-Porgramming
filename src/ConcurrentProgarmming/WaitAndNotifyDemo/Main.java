package ConcurrentProgarmming.WaitAndNotifyDemo;

/**
 * Created by Yan_Jiang on 2018/10/23.
 * 测试主程序
 */
public class Main {
    public static void main(String[] args) {

        try {
            Object lock = new Object();

            ThreadA a = new ThreadA(lock);
            a.start();

            Thread.sleep(50);

            ThreadB b = new ThreadB(lock);
            b.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
