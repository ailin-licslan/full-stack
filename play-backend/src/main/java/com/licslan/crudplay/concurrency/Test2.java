package com.licslan.crudplay.concurrency;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发编程理解  Java内存模型  volatile  原子性  可见性  有序性  happens-before  内存屏障  CAS  AQS
 * <p>
 * <p>
 * 无锁： cas原子类  局部变量  ThreadLocal  不可变对象
 * 有锁： synchronized ReentrantLock
 */

public class Test2 {

    private static final AtomicInteger j = new AtomicInteger(0);

    private static final Object o = new Object();

    //存放方法区 类静态变量
    static int i = 0;

    public static void method() {

        // synchronized 线程安全 同步  悲观锁思想
//        synchronized (o) {
//            i++;
//        }

        //非线程安全计算
//        i++;

        //CAS 原子类 乐观锁思想
        int i1 = j.incrementAndGet();

        System.out.println(i + " === " + i1);

    }

    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 50000; i++) {
            new Thread(Test2::method).start();
        }

        Thread.sleep(1);

        System.out.println("====>  " + i);

        System.out.println("============> end");

    }
}
