package MutiThreadProgramming.ch4;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by Yan_Jiang on 2018/11/3.
 *   测试 普通java程序 包含哪些线程
 */
public class MutiThread {

    public static void main(String[] args) {

        //获取java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

        // lockedMonitors  lockedSynchronizers  同步的monitor、synchronized锁
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for(ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getLockName());
            //mian线程和多个其他线程同时运行
        }

    }
}
