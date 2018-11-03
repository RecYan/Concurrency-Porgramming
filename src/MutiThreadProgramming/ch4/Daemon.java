package MutiThreadProgramming.ch4;

/**
 * Created by Yan_Jiang on 2018/11/3.
 * 守护线程测试
 */
public class Daemon {

    public static void main(String[] args) {

        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        thread.setDaemon(true); //需要在启动线程之前设置
        thread.start();
    }

    public static class DaemonRunner implements Runnable {

        @Override
        public void run() {
            try{
                SleepUtils.second(10);
            } finally {
                System.out.println("Daemon finally run");
            }
        }


    }

}
