# CAS

### cas是什么：比较并交换：compare and swap

工作内存中的数据与主内存中的数据进行比较，若是相等则执行替换操作，且该操作是原子的。

### 如何保证原子性：

通过底层native方法，cas是一种系统原语，由若干条指令组成，执行必须连续

```java
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(6);

        atomicInteger.getAndAdd(1);//调用该方法

    } 
```



```java
   //调用unsafe方法
		public final int getAndAdd(int delta) {
        return unsafe.getAndAddInt(this, valueOffset, delta);
    }
```



```java
   //调用native方法  1，2 确定主内存中的值，5为工作内存的值
		public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            var5 = this.getIntVolatile(var1, var2);
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

        return var5;
    }
```

```java
//获取该对像（var1） 的内存地址（var2）上的值 赋值给（var5）
public native int getIntVolatile(Object var1, long var2);
//比较是否相等，若相等则交换
public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);

```

### cas缺点

1.循环时间长，开销大

2.只保证一个共享变量原子性

3.ABA问题

### ABA

什么是ABA：cas只关心开头结尾，过程不管，ABA是在过程中变过值后，又改回来而产生的问题。

```java
public class ABADemo {
    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    public static void main(String[] args) {
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }).start();

        new Thread(() -> {
            // 保证上面线程先执行
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicReference.compareAndSet(100, 2019);
            System.out.println(atomicReference.get()); // 2019
        }).start();
    }
}
```



原子引用更新

如何规避ABA问题:使用原子时间戳引用

```java
public class ABADemo2 {
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " 的版本号为：" + stamp);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1 );
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1 );
        }).start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + " 的版本号为：" + stamp);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(b); // false
            System.out.println(atomicStampedReference.getReference()); // 100
        }).start();
    }
}
```





