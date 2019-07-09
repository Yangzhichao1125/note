# CountDownLatch/CyclicBarrier/Semaphore

## CountDownLatch重点：countDown（），away（）的用法知道即可

```java
public class CountDownLanchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " 离开了教室...");
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("班长把门给关了，离开了教室...");
    }
}
```

CountDownLatch数值减到0时执行代码



## CyclicBarrier思路与CountDownLatch相反

加到对应数值后执行代码

```java
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> {
            System.out.println("车满了，开始出发...");
        });
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 开始上车...");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
```

数值达到3后，再从0开始算

若数值等不到，则会一直等下去



## Semaphore

多个资源争抢更多资源(acquire获得资源，release释放资源)

```java
public class SemaphoreDemo {
  public static void main(String[] args) {
      Semaphore semaphore = new Semaphore(3);
      for (int i = 0; i < 6; i++) {
          new Thread(() -> {
              try {
                  semaphore.acquire(); // 获取一个许可
                  System.out.println(Thread.currentThread().getName() + " 抢到车位...");
                  Thread.sleep(3000);
                  System.out.println(Thread.currentThread().getName() + " 离开车位");
              } catch (InterruptedException e) {
                  e.printStackTrace();
              } finally {
                  semaphore.release(); // 释放一个许可
              }
          }).start();
      }
  }
}
```





