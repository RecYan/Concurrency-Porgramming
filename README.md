# Concurrency-Porgramming #

---

*Java并发编程实战、Java并发编程的艺术、Java多线程编程核心技术中的主要知识点和代码实现*


# java并发机制底层 #

+ volatile : 轻量级的synchronized

>多处理器开发中保证了<font color="red">共享变量</font>的`可见性`,即当一个线程改变一个共享变量时，其他的线程也能读取到这个修改的值
>java内存模型确保所有线程看到的这个变量的值是一直的

可见性保证：Lock前缀指令、缓存一致性

优化：追加字节优化性能

+ synchronized
>synchronized用的锁存于java对象头中的 *Mark Word* 区域中
>jdk1.6以后，引入了**偏向锁**和**轻量级锁**来减少获取锁和释放锁的性能消耗  
>锁四种状态：无锁状态、偏向锁状态、轻量级锁状态、重量级锁状态，状态可以升级但不可降级

+ 原子操作
**CAS:** Compare And Swap 
>CAS操作需要输入两个值，一个旧值(期望操作前)、一个新值，操作时先比较旧值是否变化，无则交换程新值，有则不操作

**java实现原子操作：** 循环CAS

循环CAS问题：
>ABA问题 ---> 使用版本号解决  
>循环时间长开销大  
>只能保证一个共享变量的原子操作  ---> 多个变量需要锁来实现  

# java内存模型 #

+ 相关基本概念
>1. 通信：线程间以何种机制来交换信息，其机制主要有两种：共享内存和消息传递
>共享内存：线程间共享程序的公共状态，通过读-写内存中的公共状态**隐式**通信
>消息传递：线程间无的共享公共状态，只能**显式**通过消息传递来通信
>2. 同步： 程序中用于控制不同线程间操作大声相对顺序的机制
>共享内存：显式（加锁机制）
>消息传递：隐式

**<font color="red">小结：</font>** java中并发采用*共享内存模型*，通信是*隐式*进行的

+ 内存模型抽象结构
JMM(Java内存模型)决定了一个线程对*共享变量的操作*何时对另一个线程可见，其抽象模型如下：
![Java内存模型](https://github.com/RecYan/Concurrency-Porgramming/raw/master/img/ch3-1.jpg)  

即线程A与线程B通信过程如下：
>线程A将本地内存A中更新过的共享变量刷新到主内存中
>线程B再主内存中去读取该共享变量

**<font color="red">注意：</font>**
1. 本地内存为JMM的抽象概念，并不真实存在
2. JMM通过控制主内存与每个线程的本地内存之间的交互，来为Java来提供内存可见性保证



---

**Java多线程核心知识点**
1. 进程：一次程序的运行，为系统进行资源分配和调度的一个独立单位 eg->任务列表中的exe文件  
2. 线程：进程中独立运行的子任务  
3. 多线程编程实现方式：i.继承Thread类 ii.实现Runnable接口  
> 需要注意：`public class Thread implements Runnable`，Thread类实现了Runnable接口  
4. 在JVM中，i++的执行过程：  
>1. 取得原有i值  
>2. 计算i+1  
>3. 对i进行复制  
>4. **若有多线程访问时，很容易出现线程不安全问题，需要引起注意**  
5. API常见方法：  
>currentThread(): 返回代码段正在被那个线程调用  
>isAlive(): 判断当前线程是否处于活动状态  
>sleep(): 在指定毫秒内让当前“正在执行的线程”休眠  
>getId(): 取得线程的唯一标志  
6. 线程停止问题  
> interrupted(): 测试**当前线程**是否终端，仅仅在当前线程搭了一个停止的标记，并不是真正的通知线程,其具有清除状态的功能  
``` java
/*** public static boolean interrupted ***/  
Thread.currentThread.interrupted();  
System.out.println(Thread.interrupted()); //true  
System.out.println(Thread.interrupted()); //false  
```
> isInterrupted()：测试Thread对象是否已经停止，但不清楚状态标志
``` java
/*** public boolean interrupted ***/  
Thread.currentThread.interrupted();  
System.out.println(Thread.isInterrupted()); //true  
System.out.println(Thread.isInterrupted()); //true  
```
7. 线程暂停问题
>suspend(): 暂停线程， resume(): 恢复线程运行  
>**注意：**可能出现*独占锁*的现象，不同步现象  
>yield(): 放弃当前cpu的执行权  
8. 线程优先级：java中线程优先级分为1--10个等级，等级越高，优先级越大  
9. 守护线程： 但进程中不存在非守护线程时，该线程自动销毁，典型的例子就是 *垃圾回收线程*  

**同步相关**
1. 非线程安全： 多个线程对同一个对象中的实例变量进行并发访问时发生，可能会出现*脏读*
2. 方法内的变量时线程安全的 --> 见HasSelfPrivateNum.java
3. 






