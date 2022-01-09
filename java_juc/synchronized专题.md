# synchronized专题

# 1. 并发编程的三个问题

## 1.1 可见性

- 可见性visibility:

  是指当一个线程对共享变量进行了修改，那么另外的线程可以立即看到修改后的值

- 可见性demo:

```java
package com.mylearn.juc.synchronize;

import java.util.concurrent.TimeUnit;

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
```

运行结果：

![image-20211215231502186](images/image-20211215231502186.png)

由图中可以看到：程序一直运行未退出，这就是共享变量可见性的安全问题。

## 1.2 原子性

- 概念：

  在一次或多次操作中，所有的操作都执行并且不会受到其他因素干扰而中断，要么就全不操作。

- 原子性demo:

```java
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
```

运行结果：

![image-20211215232602012](images/image-20211215232602012.png)

这实际就是因为线程的操作并不是原子性造成的结果。

## 1.3 有序性

程序在执行过程中的先后顺序，由于java在编译期以及运行期的优化，导致了代码的执行顺序未必就是开发者编写代码的顺序。

- 有序性demo

```
```



# 2. Java内存模型(JMM)





# 3. synchronized保证三大特性

# 4. synchronized的特性

## 4.1 可重入性

## 4.2 不可中断性

# 5. synchronied原理

# 6. JDK6中 synchronized优化

## 锁升级过程



