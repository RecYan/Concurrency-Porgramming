package ConcurrentProgarmming.WaitAndNotifyDemo;

/**
 * Created by Yan_Jiang on 2018/10/23.
 * 等待唤醒机制 理解
 *   启动线程A，取得锁之后先启动线程B再执行wait()方法，释放锁并等待；
     线程B启动之后会等待锁，A线程执行wait()之后，线程B取得锁，然后启动线程C，再执行notify唤醒线程A，最后退出synchronize代码块，释放锁;
     线程C启动之后就一直在等待锁，这时候线程B还没有退出synchronize代码块，锁还在线程B手里；
     线程A在线程B执行notify()之后就一直在等待锁，这时候线程B还没有退出synchronize代码块，锁还在线程B手里；
     线程B退出synchronize代码块，释放锁之后，线程A和线程C竞争锁；
 */

public class NotifyDemo {

        private static void sleep(long sleepVal){
            try{
                Thread.sleep(sleepVal);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        private static void log(String desc){
            System.out.println(Thread.currentThread().getName() + " : " + desc);
        }

        Object lock = new Object();

        public void startThreadA(){
            new Thread(() -> {
                synchronized (lock){
                    log("get lock");
                    startThreadB();
                    log("start wait");
                    try {
                        lock.wait();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }

                    log("get lock after wait");
                    log("release lock");
                }
            }, "thread-A").start();
        }

        public void startThreadB(){
            new Thread(()->{
                synchronized (lock){
                    log("get lock");
                    startThreadC();
                    sleep(100);
                    log("start notify");
                    lock.notify();
                    log("release lock");

                }
            },"thread-B").start();
        }

        public void startThreadC(){
            new Thread(() -> {
                synchronized (lock){
                    log("get lock");
                    log("release lock");
                }
            }, "thread-C").start();
        }

        public static void main(String[] args){
            new NotifyDemo().startThreadA();
        }
}

