package com.mylearn.juc.threadlocal;

public class ThreadLocalDemo {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private String content;

    public String getContent() {
        return threadLocal.get();
    }

    public void setContent(String content) {
        threadLocal.set(content);
    }

    public static void main(String[] args) {
        ThreadLocalDemo demo = new ThreadLocalDemo();

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                demo.setContent(Thread.currentThread().getName() + "的数据");
                System.out.println("----------------");
                System.out.println(Thread.currentThread().getName() + "---->" + demo.getContent());
            });

            thread.setName("线程" + i);
            thread.start();
        }
    }

}
