package com.mylearn.interviewSummary;

/**
 * 枚举类饿汉式
 */
public enum Singleton2 {

    INSTANCE;

    // 枚举类的私有无参构造方法，最终生成的class文件是含两参数的构造方法
    private Singleton2() {
    }

    // 实际是这样的方法
    // private Singleton2(String name,int index){
    //
    // }

    public static Singleton2 getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
}
