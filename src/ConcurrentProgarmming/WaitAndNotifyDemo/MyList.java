package ConcurrentProgarmming.WaitAndNotifyDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yan_Jiang on 2018/10/23.
 * wait notify机制的简单实现
 */
public class MyList {

    private static List<String> list = new ArrayList<String>();

    public static void add() {
        list.add("test");
    }

    public static int getSize() {
        return list.size();
    }
}
