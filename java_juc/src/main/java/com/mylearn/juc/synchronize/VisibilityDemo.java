package com.mylearn.juc.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * 可见性demo
 */
public class VisibilityDemo {
    // 1. 定义一个共享变量
    private static boolean flag = true;

    public static void main(String[] args) {
        // 2. 其中1个线程根据共享变量去执行
        new Thread(() -> {
            while (flag) {

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3. 修改共享变量的值
        new Thread(() -> {
            flag = false;
        }).start();

    }

}
