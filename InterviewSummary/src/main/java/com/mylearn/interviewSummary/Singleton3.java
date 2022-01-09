package com.mylearn.interviewSummary;

/**
 * 懒汉式单例模式
 */
public class Singleton3 {

    private Singleton3() {

    }

    private static Singleton3 INSTANCE = null;

    // 下面如果不加synchronized关键字在多线程情况下会破坏单例模式
    public static synchronized Singleton3 getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton3();
        }
        return INSTANCE;
    }
}
