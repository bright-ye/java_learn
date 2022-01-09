package com.mylearn.interviewSummary;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Singleton2Test {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        System.out.println("调用getInstance获取单例：" + Singleton2.getInstance());

        reflection(Singleton2.class);

       // serializable(Singleton2.getInstance());


    }

    private static void unsafe() {
        // 使用spring框架的unsafeUtils来破坏单例模式，且目前无解
    }


    /**
     * 反序列化破坏单例
     *
     * @param instance
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static void serializable(Object instance) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(instance);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        System.out.println("反序列化创建实例：" + ois.readObject());
    }

    /**
     * 反射破坏单例模式
     *
     *
     * @param clazz
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    private static void reflection(Class<?> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 针对枚举类饿汉式，要修改如下获取构造器，因为枚举类实际不存在无参构造器
        Constructor<?> constructor = clazz.getDeclaredConstructor(String.class,int.class);

        // Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        System.out.println("反射创建的实例：" + constructor.newInstance());
    }

}
