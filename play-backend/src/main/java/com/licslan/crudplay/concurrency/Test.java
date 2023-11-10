package com.licslan.crudplay.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    //常量有三种类型：静态常量、成员常量和局部常量。

    //在定义常量时就需要对该常量进行初始化。
    //final 关键字不仅可以用来修饰基本数据类型的常量，还可以用来修饰对象的引用或者方法。
    //为了与变量区别，常量取名一般都用大写字符。


    //Java中的static关键字用于修饰成员变量和成员方法，表示它们属于类而不是对象。
    //static成员变量存放在方法区中的Class对象中，每个静态变量都有一个固定的内存位置，不会随对象的创建而改变。
    //static成员方法也存放在方法区中的Class对象中，可以通过类名和点操作符直接调用，而不需要通过对象来调用。

    // 静态常量 Linking阶段的里面的准备阶段  给静态变量(static)分配内存并赋初始值,double test =0此时非100,如果final修饰则是3.14了此时
    public static final double PI = 3.14;
    // 声明成员常量
    final int y = 10;

    public static void main3(String[] args) {
        // 声明局部常量
        final double x = 3.3;
    }

    //共享变量 存在主内存区域
    static int counter = 0;

    //线程不安全版
    public static void main1(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter++;
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter--;
            }
        }, "t2");
        t1.start();
        t2.start();
        //等待t1线程执行结束
        t1.join();
        //等待t2线程执行结束
        t2.join();
        System.out.println(counter);
    }


    //加锁保护临界区 一段代码块内如果存在对共享资源的多线程读写操作，称这段代码块为临界区
    private static final Test room = new Test();

    //synchronized 线程安全版
    public static void main2(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (room) {
                    counter++;
                }
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (room) {
                    counter--;
                }

            }
        }, "t2");
        t1.start();
        t2.start();
        //等待t1线程执行结束
        t1.join();
        //等待t2线程执行结束
        t2.join();
        System.out.println(counter);
    }


    //CAS 原子类保证线程安全
    static AtomicInteger data = new AtomicInteger(0);


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                data.incrementAndGet();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                data.decrementAndGet();

            }
        }, "t2");
        t1.start();
        t2.start();
        //等待t1线程执行结束
        t1.join();
        //等待t2线程执行结束
        t2.join();
        System.out.println(data.get());
    }

    /***
     //i++的字节码文件
     getstatic i // 获取静态变量i的值
     iconst_1 // 准备常量1
     iadd // 自增
     putstatic i // 将修改后的值存入静态变量i

     //i--的字节码文件
     getstatic i // 获取静态变量i的值
     iconst_1 // 准备常量1
     isub // 自减
     putstatic i // 将修改后的值存入静态变量i


     分析容易出现的问题，上述的四步操作各自都是原子操作，但是，这4个步骤组合在一起就不是原子操作了，
     当执行了第3步自增操作之后，如果还没有执行第4步将修改后的值存入静态变量i，这是CPU的时间片
     就被分出去执行i--操作了就会出现错误；同理对i--的详细操作也是如此，当执行自减操作之后，CPU
     的分片执行其他指令，也会出现错误
     */
}
