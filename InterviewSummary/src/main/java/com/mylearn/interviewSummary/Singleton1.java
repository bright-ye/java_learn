package com.mylearn.interviewSummary;

import java.io.Serializable;

/**
 * 单例模式（饿汉式）
 */
public class Singleton1 implements Serializable {

    private Singleton1() {
        if (INSTANCE != null) {
            throw new RuntimeException("单例对象不能重复创建");
        }
    }

    private static final Singleton1 INSTANCE = new Singleton1();

    public static Singleton1 getInstance() {
        return INSTANCE;
    }

    // 预防反序列化破坏单例模式（原理不明）
    public Object readResolve() {
        return INSTANCE;
    }

}
