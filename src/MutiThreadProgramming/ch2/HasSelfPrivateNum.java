package MutiThreadProgramming.ch2;

/**
 * Created by Yan_Jiang on 2018/9/25.
 * 方法内部私有变量 不会存在线程安全性问题
 */
public class HasSelfPrivateNum {

    public void add(String userName) {

        try{
            int num = 0;
            if(userName.equals("a")) {
                num = 1000;
                System.out.println("a set over");
                Thread.sleep(2000);
            } else {
                num = 2000;
                System.out.println("b set over");
            }
            System.out.println("userName: "+ userName + "num = "+ num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

class A extends Thread {

    private HasSelfPrivateNum numRef;
    public A(HasSelfPrivateNum numRef) {
        super();
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.add("a");
    }
}

class B extends Thread {

    private HasSelfPrivateNum numRef;
    public B(HasSelfPrivateNum numRef) {
        super();
        this.numRef = numRef;
    }

    @Override
    public void run() {
        super.run();
        numRef.add("b");
    }
}
