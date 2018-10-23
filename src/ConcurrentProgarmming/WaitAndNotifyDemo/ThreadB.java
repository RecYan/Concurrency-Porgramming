package ConcurrentProgarmming.WaitAndNotifyDemo;

/**
 * Created by Yan_Jiang on 2018/10/23.
 * 测试
 */
public class ThreadB extends Thread {

    private Object lock; //声明对象锁

    public ThreadB(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                if (MyList.getSize() != 5) {
                    System.out.println("wait begin "
                            + System.currentTimeMillis());
                    lock.wait();
                    System.out.println("wait end  "
                            + System.currentTimeMillis());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
