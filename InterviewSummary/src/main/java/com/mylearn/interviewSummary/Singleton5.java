package com.mylearn.interviewSummary;

/**
 * 静态内部类
 */
public class Singleton5 {

    private Singleton5() {

    }

    // 静态内部类
    private static class Holder {
        static Singleton5 INSTANCE = new Singleton5();
    }

    public static Singleton5 getINSTANCE() {
        return Holder.INSTANCE;
    }
}
