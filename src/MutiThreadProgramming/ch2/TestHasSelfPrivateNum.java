package MutiThreadProgramming.ch2;

/**
 * Created by Yan_Jiang on 2018/9/25.
 */
public class TestHasSelfPrivateNum {

    public static void main(String[] args) {
        HasSelfPrivateNum numRef = new HasSelfPrivateNum();

        A aThread = new A(numRef);
        aThread.start();
        B bThread = new B(numRef);
        bThread.start();

    }

}
