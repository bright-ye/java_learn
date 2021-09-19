[TOC]

# 并发编程

## 并发和并行的概念

- 并发concurrent：同一时间应对多件事，cpu在多个线程上下快速切换
- 并行parallel：同一时间做多件事，cpu多个核心同时执行多个线程

## 异步和同步

- 同步：需要等待结果才能继续运行

- 异步：不需要等待结果就能返回

## 创建线程的方法

### 1. 直接使用Thread

```java
public class Test1 {

    public static void main(String[] args) {

        // 1.创建线程
        Thread t = new Thread() {
            // 2. 线程要执行的任务
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " running...");
            }
        };

        t.start();

        System.out.println(Thread.currentThread().getName() + " running...");
    }

}
```

### 2. 使用Runnable配合Thread

线程和任务分离开来

```java
public class Test2 {

    public static void main(String[] args) {

        // 线程要执行的任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " running...");
            }
        };

        // 2. 创建线程
        new Thread(runnable).start();

        System.out.println(Thread.currentThread().getName() + " running...");
    }

}
```

Runnable的创建也可以使用lambda表达式来处理：

```java
@Test
public void test2() {
    Runnable runnable = () -> {
        System.out.println(Thread.currentThread().getName() + " running...");
    };
}
```

### 3. 使用FutureTask、Callable、Thread组合获取线程返回值

```java
public class Test3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                return 100;
            }
        });

        Thread t = new Thread(futureTask, "futureTask thread");
        t.start();

        System.out.println(futureTask.get());
    }
}
```

## idea设置多线程debug模式

断点上右击，把 All 改成 Thread，有多个线程的话，需要在多个断点上都需要设置

![image-20210915232150004](%E5%B9%B6%E5%8F%91%E7%BC%96%E7%A8%8B.assets/image-20210915232150004.png)

## 线程上下文切换

cpu在运行线程过程中，可能会暂停执行当前线程，转而执行其他线程，发生这样的原因通常有：

- 线程的cpu时间片用完
- 垃圾回收
- 有更高优先级的线程需要执行
- 线程调用了sleep、yield、wait、join、park、synchronized、lock等方法

当Context Switch发生时，需要由操作系统保存当前线程的状态，恢复另一个线程的状态，Java中对应的概念就是程序计数器Program Counter Register，它的作用是记住下一个jvm指令的执行地址，是线程私有的。

- Context Switch频繁发生会影响性能
- 状态包括了程序计数器、虚拟机栈中每个栈的信息，如局部变量、操作数栈、返回地址等

## 线程方法API

- start()

  启动线程，线程以异步的方式来执行；

  启动一次后，不可以再重复调用，否则会出现异常

- run()

  启动异步线程后，线程run方法中的内容；

  如果直接调用run方法，那么run方法就相当于是一个普通方法，被他所在的那个线程调用，也就是同步执行，不会异步执行

- state

  线程状态

- sleep

  sleep方法，会让线程进入睡眠，此时线程的状态是time waiting；

  其他线程可以使用interrupt方法打断正在睡眠的线程，这时sleep线程会抛出InterruptedException，sleep线程继续执行；

  sleep结束的线程未必会立即得到执行；

  建议使用TimeUnit的sleep代替Thread的sleep来获得更好的可读性；

- yield

  调用yield方法会让当前线程从running进入runnable就绪状态，然后调度其他线程；

  具体的线程依赖于操作系统的任务调度

- 线程优先级setPriority

  线程优先级会提示cpu调度器优先调度该线程；

  如果在cpu比较忙时，优先级高的线程会获得更多的时间片，但cpu闲时，优先级几乎没有作用

  

  

  
