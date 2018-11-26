package ConcurrentProgarmming.InteruptTest;

/**
 * Created by Yan_Jiang on 2018/11/26.
 * interrupted方法
 * isInterrupted方法
 * 两者辨析
 * 
 */
public class ThreadB extends Thread {

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 1000; i++) {
            System.out.println("i = "+ (i+1));
        }
    }
}

class RunB {
    public static void main(String[] args) {


        try {
            ThreadB thread = new ThreadB();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt(); //只是在当前线程[调用interupt的线程]打了一个中断停止的标记 并不会停止线程
            System.out.println("是否停止："+thread.interrupted());
            System.out.println("是否停止："+thread.interrupted()); //interrupted 测试当前线程是否处于中断状态 并清除当前状态标记
            System.out.println("是否停止："+thread.isInterrupted()); // 测试当前线程是否处于中断状态 但[不会]清除当前状态标记

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end......");
    }
}
