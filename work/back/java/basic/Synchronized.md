# Synchronized



### 什么是Synchronized

关键字，为了解决java并发编程中同步的问题。为一段代码块提供原子性，可见性，有序性。





### 怎么用Synchronized

静态同步方法

同步方法

同步代码块

锁住对象，谁拿到锁谁就可以执行。





### Synchronized原理

为什么要锁住对象？

每个对象在JVM的内存中分为3个区域：对象头，实例变量，填充数据

其中对象头包含两个数据：Mark Word 和Class Metedate Address

Mark Word 中包含hashCode，分代信息，是否轻量级锁，锁标志位

java6前synchronized 是重量锁，6之后对其进行了很多优化。当标志位是10时，指针指向monitor对象，当一个monitor被一个线程所占有时，此对象处于锁住状态。我们可以用javap 命令反编译class文件查看汇编指令。在同步代码块中可以看到一个monitorenter和两个monitorexit。这两个exit，一个代表正常突出时，一个代表异常退出时执行。而写在方法上的synchronized 反编译可以看到时一个ACC_SYNCHRONIZED的一个标示，实际上底层也是调用monitor的方法来控制同步执行。monitor是一个计数器，被占有时加一，被释放时减一。

因为monitor存在对象头中，所以每个对象都可以作为synchronized的锁对象，同时await/notify/notifyall这三个方法也被写在Object的方法里面。此三个方法需要与synchronized配合使用否则会抛出illegialMonitorStackException错误因为这几个方法需要拿到monitor对象。

偏向锁：竞争少的情况下适用。看标记位，可以就用，有竞争就通过CAS，超过安全点就升级成轻量锁

轻量锁：使用CAS 竞争多会膨胀成重量锁。































