# Concurrency-Porgramming #

+ [java并发机制底层](#1)
	+ [volatile](#1.1)
	+ [synchronized](#1.2)
	+ [原子操作](#1.3)
	+ [java实现原子操作: 循环CAS](#1.4)
+ [java内存模型](#2)
	+ [相关基本概念](#2.1)
	+ [内存模型抽象结构](#2.2)
+ [Java多线程核心知识点](#3)
	+ [基础知识](#3.1)
	+ [同步相关](#3.2)
	+ [java中的锁](#3.3)
+ [Java并发编程与高并发解决方案](#4)
	+ [并发基础](#4.1)
	+ [线程安全性](#4.2)
		+ [原子性](#4.2.1)
		+ [可见性](#4.2.2)
		+ [有序性](#4.2.3)


---

*Java并发编程实战、Java并发编程的艺术、Java多线程编程核心技术中的主要知识点和代码实现*

<a name="1"></a>
## java并发机制底层 ##

<a name="1.1"></a>
+ volatile : 轻量级的synchronized

>多处理器开发中保证了<font color="red">共享变量</font>的`可见性`,即当一个线程改变一个共享变量时，其他的线程也能读取到这个修改的值  
>java内存模型确保所有线程看到该变量的值是一致的  
>可见性保证：Lock前缀指令、缓存一致性  
>优化：追加字节优化性能

<a name="1.2"></a>
+ synchronized
>synchronized用的锁存于java对象头中的 *Mark Word* 区域中  
>jdk1.6以后，引入了**偏向锁**和**轻量级锁**来减少获取锁和释放锁的性能消耗  
>锁四种状态：无锁状态、偏向锁状态、轻量级锁状态、重量级锁状态，状态可以升级但不可降级

<a name="1.3"></a>
+ 原子操作
**CAS:** Compare And Swap
>CAS操作需要输入两个值，一个旧值(期望操作前)、一个新值，操作时先比较旧值是否变化，无则交换程新值，有则不操作

<a name="1.4"></a>
+ **java实现原子操作：** 循环CAS
循环CAS问题：
>ABA问题 ---> 使用版本号解决  
>循环时间长开销大  
>只能保证一个共享变量的原子操作  ---> 多个变量需要锁来实现  

<a name="2"></a>
## java内存模型  ##

<a name="2.1"></a>
+ 相关基本概念
>1. 通信：线程间以何种机制来交换信息，其机制主要有两种：共享内存和消息传递
>共享内存：线程间共享程序的公共状态，通过读-写内存中的公共状态**隐式**通信
>消息传递：线程间无的共享公共状态，只能**显式**通过消息传递来通信
>2. 同步： 程序中用于控制不同线程间操作大声相对顺序的机制
>共享内存：显式（加锁机制）
>消息传递：隐式

**<font color="red">小结：</font>** java中并发采用*共享内存模型*，通信是*隐式*进行的

<a name="2.2"></a>
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

<a name="3"></a>
## **Java多线程核心知识点** ##

<a name="3.1"></a>
**基础知识**
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
10. **多线程状态转换图**  
![多线程状态转换图](https://i.imgur.com/9ucWRWT.jpg)
11. 线程间通信：等待唤醒机制<WatiAndNotifyDemo>
![等待唤醒机制](https://i.imgur.com/OAKwtmP.jpg)
12. Thread.join()实现细节
``` java
当前线程 只有等到调用join()方法的线程执行结束时 才能返回从断点继续执行
public final synchronized void join(long millis) throws InterruptedException { //加锁当前线程对象
	//条件不满足时 则继续等待
	while(isAlive) {
		wait(0);
	}
//条件符合 则方法返回
}
```
<a name="3.2"></a>
**同步相关**
1. 非线程安全： 多个线程对同一个对象中的实例变量进行并发访问时发生，可能会出现*脏读*
2. 方法内的变量时线程安全的 --> 见HasSelfPrivateNum.java

<a name="3.3"></a>
**java中的锁**
1. synchronized锁：隐式的获取锁，只能先获取再释放，过程固定化
2. Lock接口显示的实现锁，具备synchronized锁锁不具备的功能：

``` java
1. 可以尝试非阻塞的获取锁：
2. 可以中断式的获取锁：中断异常时，会释放锁
3. 可以超时获取锁
```
3. 队列同步器(AQS)









---

<a name="4"></a>
# Java并发编程与高并发解决方案 #

<a name="4.1"></a>
## 并发基础 ##
1. 并发：多个线程同时操作相同的资源，保证线程安全性，合理使用资源
2. 高并打：服务能够同时处理多个请求，提高程序性能
3. cpu多级缓存: cpu -> catch -> menory
4. 缓存一致性（MESI）: 保证多个cpu catch之间缓存共享数据的一致性
> M: Modified 修改，指的是该缓存行只被缓存在该CPU的缓存中，并且是被修改过的，因此他与主存中的数据是不一致的，该缓存行中的数据需要在未来的某个时间点（允许其他CPU读取主存相应中的内容之前）写回主存，然后状态变成E（独享）  
>E：Exclusive 独享 缓存行只被缓存在该CPU的缓存中，是未被修改过的，与主存的数据是一致的，可以在任何时刻当有其他CPU读取该内存时，变成S（共享）状态，当CPU修改该缓存行的内容时，变成M（被修改）的状态  
>S：Share 共享，意味着该缓存行可能会被多个CPU进行缓存，并且该缓存中的数据与主存数据是一致的，当有一个CPU修改该缓存行时，其他CPU是可以被作废的，变成I(无效的)  
>I：Invalid 无效的，代表这个缓存是无效的，可能是有其他CPU修改了该缓存行  
>**M(Modified)和E(Exclusive)状态的Cache line，数据是独有的，不同点在于M状态的数据是dirty的(和内存的不一致)，E状态的数据是clean的(和内存的一致)。**
5. 乱序执行优化：cpu为了提高运算速度而做出违背代码原有顺序的优化,可能导致逻辑上后写入的数据不一定最后写入
6. java内存模型（JMM）: 规定了一个线程如何和何时可以看到其他线程修改过后的共享变量的值,如何以及何时同步的访问共享变量
7. JMM中八种同步操作：
>1. Lock（锁定）：作用于主内存的变量，把一个变量标识变为一条线程独占状态
>2. Unlock（解锁）：作用于主内存的变量，把一个处于锁定状态的变量释放出来，释放后的变量才可以被其他线程锁定
>3. Read（读取）：作用于主内存的变量，把一个变量值从主内存传输到线程的工作内存中，以便随后的load动作使用
>4. Load（载入）：作用于**工作内存**的变量，它把Read操作从主内存中得到的变量值放入**工作内存的变量副本**中
>5. Use（使用）：作用于**工作内存**的变量，把**工作内存中的一个变量值传递给执行引擎**
>6. Assign（赋值）：作用于**工作内存**的变量，它**把一个从执行引擎接受到的值赋值给工作内存的变量**
>7. Store（存储）：作用于**工作内存**的变量，把工作内存中的一个变量的值传送到主内存中，以便随后的write的操作
>8. Write（写入）：作用于主内存的变量，它把Store操作从工作内存中一个变量的值传送到主内存的变量中

![同步方法](https://i.imgur.com/cXLguBk.jpg)
8. JMM同步规则：
>1. 如果要把一个变量从主内存中赋值到工作内存，就需要按顺序得执行read和load操作，如果把变量从工作内存中同步回主内存中，就要按顺序得执行store和write操作，但java内存模型只要求上述操作必须按顺序执行，没有保证必须是连续执行,也就是说Read和Load、Store和Write之间是可以插入其他指令的
>2. 不允许read和load、store和write操作之一单独出现
>3. 不允许一个线程丢弃他的最近assign的操作，即变量在工作内存中改变了之后必须同步到主内存中
>4. 不允许一个线程无原因地（也就是说必须有assgin操作）把数据从工作内存同步到主内存中
>5. 一个新的变量只能在主内存中诞生，不允许在工作内存中直接使用一个未被初始化（load或assign）的变量。即就是对一个变量实施use和store操作之前，必须先执行过了load和assign操作
>6. 一个变量在同一时刻只允许一条线程对其进行lock操作，但lock操作可以同时被一条线程重复执行多次，多次执行lock后，只有执行相同次数的unlock操作，变量才会解锁，lock和unlock必须成对出现
>7. 如果一个变量执行lock操作，将会清空工作内存中此变量的值，在执行引擎中使用这个变量前需要重新执行load或assign操作初始化变量的值
>8. 如果一个变量事先没有被lock操作锁定，则不允许他执行unlock操作，也不允许去unlock一个被其他线程锁定的变量
>9. 对一个变量执行unlock操作之前，必须先把此变量同步到主内存中（其实就是执行store和write操作之后）

## 测试项目搭建 ##
1. 使用 Spring-Boot 项目做简单测试用例 <concurrency>  
2. 模拟工具： Postman、Apache Bench(AB)、JMeter、代码并发模拟：Semaphore, CountDownLatch  

**CountDownLatch示意图**: 即阻塞线程 且在特定的条件下执行指定的线程  
![CountDownLatch](https://i.imgur.com/mzQUNWf.jpg)  
**SemaPhore示意图**：信号量，阻塞线程 并控制统一时间内的并发请求量  
![SemaPhore](https://i.imgur.com/MAd7KET.jpg) 

<a name="4.2"></a>
## 线程安全性 ##
1. 定义：当多个线程访问某个类时，不管运行时环境采用**何种调度方式**或者这些线程将如何交替执行，并且在主调代码中**不需要任何额外的同步或协同**，这个类都能**表现出正确的行为**，那么这个类就是线程安全的。
2. 主要体现：
>1. 原子性：提供了互斥访问，同一时刻只能由一个线程来对其进行操作
>2. 可见性：一个线程对**主线程**的修改可以被其他线程观察到
>3. 有序性：一个线程观察其他线程中的指令执行顺序，由于**指令重排序**的存在,该观察结果一般杂乱无序
<a name="4.2.1"></a>
3. **原子性-Atomic包**
>1. AtomicXXX: CAS、Unsafe.compareAndSwapInt....
>2. 使用Atomic包，该改进了原先*CountExample1*中线程不安全的代码--使用AtomicInteger、increaseAndGet
>3. increaseAndGet源码跟踪: 
>>**unsafe**.getAndAddInt(this, valueOffset, 1) + 1; --> **unsafe类中*getAndAddInt*方法实现** 
>>--> do..while语句中*while(!this.**compareAndSwapInt**(var1, var2, var5, var5 + var4));*，发现*compareAndSwapInt*方法被**native**修饰，为底层方法,核心思想为**CAS**。其中，*var5 = this.getIntVolatile(var1, var2);*  
>> **解释：**count.increaseAndGet();[count，i=2,自增1] -->var1:count, var2为当前需要增加的值，例如2，var4为增加的值的大小,例如1，var5为调用底层方法返回的值。
>>那么*compareAndSwapInt*方法的思想为：只有当var2的值与底层返回的var5的值**相同**时,才进行var5的更新操作[var5 = var5 + var4],之后再循环进行。即上面的count存在于工作内存，而var5则存在于主内存中  
>4. AtomicLong和AtomicAdder辨析：
>> **AtomicLong原理：**AtomicLong的原理是依靠底层的cas来保障原子性的更新数据，在要添加或者减少的时候，会使用死循环不断地cas到特定的值，从而达到更新数据的目的 <跟踪源码--while死循环>  
>>**AtomicAdder原理：**increase() --> add() --> cell[] <*将value拆分成多个cell，最终的value值就是这多个cell相加的和*>  --> **volatile** long value 来保证原子性。优势：*在AtomicLong的基础上将单点的更新压力分散到各个节点，在低并发的时候通过对base的直接更新可以很好的保障和AtomicLong的性能基本保持一致，而在高并发的时候通过分散提高了性能。 *[Longadder参考博客](https://blog.csdn.net/u011392897/article/details/60480108)
>5. AtomicBoolean: *compareAndSet()*方法，可保证需要的代码段 同时间，只被一个线程执行<通过设置标记位>,详情见AtomicBoolean.java  
>6. AtomicReference: 关注*compareAndSet()*方法  
>7. AtomicReferenceFieldUpdater: 更新指定类中的符合要求的字段，详细见AtomicIntergeFieldUpdater.java  
>8. AtomitStampReference: **CAS的ABA问题<并发时，一个线程将变量的值A改成B之后又改回A>** --> 使用版本号解决  

4. **原子性-锁**
>**synchronized:** 依赖JVM
>>1. 修饰代码块：大括号括起来的代码，作用于调用对象  
>>2. 修饰方法：整个方法，作用于调用对象  
>>3. 修饰静态方法：静态方法，作用与**类中所有对象**  
>>4. 修饰类  
>>5. 修改countExample代码
>
>**Lock:** 依赖特殊的cpu指令，主要实现类-ReentrantLock <后面单独分析>
>**比较：**
>> synchronized: 不可中断锁，适合竞争不激烈情况，可读性好  
>> Lock: 可中断锁，多样化同步，竞争激烈时可维持常态  
>> Atomic：竞争激烈时可维持常态，比Lock性能好，但只能同步一个值
<a name="4.2.2"></a>
5. **可见性**: 一个线程对**主线程**的修改，可以被其他线程及时观察到
*共享变量在线程间不可见的原因：*
```
1.线程交叉执行
2.重排序结合线程交叉执行
3.共享变量更新后的值没有在工作内存与主内存之间及时更新
```
> **可见性--synchronized**
```
线程解锁前，必须把共享变量的最新值刷新到主内存之中
线程加锁时，必须将工作内存中共享变量的值清空，在从主内存中重新读取最新的值
```
> **可见性--volatile:**通过加入`内存屏障`和`禁止重排序优化`来实现可见性，其**适合作为一个`状态标记量`来使用**，`double check`
```
对volatile变量写操作时，会在写操作后加入一条**store屏障指令**，将本地内存中的变量刷新到主内存中
对volatile变量读操作时，会在读操作后加入一条**load屏障指令**，从主内存中读取共享变量
[即：多个线程对，volatile变量的读写操作，都是会发在主内存中]
**修改原有countEXample程序，加入volatile验证，发现[volatile值保证了可见性，但不保证原子性]**
分析：程序中count++，分为三步： 1.读取count<volatile可以保证所有线程读取的都是一致的， 2. count+1<不保证> ，3. count的更新值刷新回主存<不保证>
```
![volatile变量写操作](https://i.imgur.com/kmd4Z4N.jpg)
![volatile变量读操作](https://i.imgur.com/8zS4rkc.jpg)
<a name="4.2.3"></a>
6. **有序性**：
happens-before原则：
```
 1. 程序次序原则：保证单线程程序执行的顺序
 2. 锁定原则： unLock操作先于Lock操作<同一个锁>
 3. volatile变量规则: 同一个变量的写操作先于读操作
 4. 传递规则：A先于B,B先于C --> A先于C
 --- 前4条比较重要
 5. 线程启动原则、6. 线程中断原则、7. 线程终结规则、8. 对象终结规则
```
7. **线程安全性--总结**
```
原子性： Atomic包、CAS算法、synchronized、Lock
可见性： synchronized、Lock
有序性： happens-before原则
/**
volatile与synchronized辨析：
1.volatile关键字是线程同步的轻量级实现，所以volatile性能肯定比synchronized关键字要好,但是volatile关键字只能用于变量而synchronized关键字可以修饰方法以及代码块,synchronized关键字在JavaSE1.6之后进行了主要包括为了减少获得锁和释放锁带来的性能消耗而引入的偏向锁和轻量级锁以及其它各种优化之后执行效率有了显著提升
2.多线程访问volatile关键字不会发生阻塞，而synchronized关键字可能会发生阻塞
3.volatile关键字能保证数据的可见性，但不能保证数据的原子性。synchronized关键字两者都能保证
4.volatile关键字用于解决变量在多个线程之间的可见性，而ynchronized关键字解决的是多个线程之间访问资源的同步性
**/
```