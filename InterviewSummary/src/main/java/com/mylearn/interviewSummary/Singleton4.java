package com.mylearn.interviewSummary;

/**
 * 单例模式（懒汉式，双重模式）
 */
public class Singleton4 {

    // volatile主要是为了解决有序性问题
    private static volatile Singleton4 INSTANCE = null;

    private Singleton4() {

    }

    /**
     * 双重检测，优化懒汉式单例模式
     *
     * @return
     */
    public static Singleton4 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton4.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton4();
                }
            }
        }
        return INSTANCE;
    }

}
