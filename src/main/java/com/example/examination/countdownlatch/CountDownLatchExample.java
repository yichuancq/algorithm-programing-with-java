package com.example.examination.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/***
 * 进入方法体
 * 进入方法体
 * 进入方法体
 * 进入方法体
 * 子线程：pool-1-thread-4执行
 * 子线程：pool-1-thread-3执行
 * 子线程：pool-1-thread-2执行
 * 子线程：pool-1-thread-1执行
 * finish
 */
public class CountDownLatchExample {

    // 处理文件的数量
    private static final int threadCount = 4;

    public static void main(String[] args) throws InterruptedException {
        // 创建一个具有固定线程数量的线程池对象（推荐使用构造方法创建）
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadnum = i;
            threadPool.execute(() -> {
                try {
                    //处理文件的业务操作
                    System.out.println("进入方法体");
                    Thread.sleep(3000);
                    System.out.println("子线程：" + Thread.currentThread().getName() + "执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //表示一个文件已经被完成
                    countDownLatch.countDown();
                }

            });
        }
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("finish");
    }
}
