package ConcurrentProgarmming.InteruptTest;

/**
 * Created by Yan_Jiang on 2018/11/26.
 *
 * interupt方法测试
 */
public class ThreadA extends Thread {

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 1000; i++) {
            System.out.println("i = "+ (i+1));
        }
    }
}

class RunA {
    public static void main(String[] args) {


        try {
            ThreadA thread = new ThreadA();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt(); //只是在当前线程[调用interupt的线程]打了一个中断停止的标记 并不会停止线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end......");
    }
}
