package com.mylearn.juc.synchronize;

import java.util.ArrayList;
import java.util.List;

/**
 * 原子性操作
 */
public class AtomicDemo {

    // 1. 初始化一个变量
    private static int number = 0;

    public static void main(String[] args) {
        // 2. 定义在一个线程中num自增1000
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                number++;
            }
        };

        // 3. 总共启动50个线程来对变量进行自增
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
            list.add(thread);
        }

        list.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("number的结果：" + number);

    }

}
