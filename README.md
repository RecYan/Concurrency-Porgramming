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
<div align=center>![Java内存模型](https://github.com/RecYan/Concurrency-Porgramming/blob/master/img/ch3-1.jpg)</div>
即线程A与线程B通信过程如下：
>线程A将本地内存A中更新过的共享变量刷新到主内存中
>线程B再主内存中去读取该共享变量

**<font color="red">注意：</font>**
1. 本地内存为JMM的抽象概念，并不真实存在
2. JMM通过控制主内存与每个线程的本地内存之间的交互，来为Java来提供内存可见性保证

