package com.yang.code1.jvm;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/10/16
 */
public class CacheTest {

    public static void main(String[] args) throws InterruptedException {

        Data data = new Data();

        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                data.add();
            }
            },String.valueOf(1)).start();
        for (int i = 0; i < 1000; i++) {
            data.add();
        }
        Thread.sleep(1000);
        System.out.println(data.a);


        AtomicInteger atom = new AtomicInteger();
        atom.getAndIncrement();
    }


}

class Data{
    volatile int a = 0;
    ReentrantLock lock = new ReentrantLock();

    public void add(){
        a++;
    }
}
