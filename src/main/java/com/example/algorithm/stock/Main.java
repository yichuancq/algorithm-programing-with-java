package com.example.algorithm.stock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yichuan
 * @version 1.0
 * @description: 函数入口
 * @date 2023/6/14 18:01
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 抢购者人数
        int buyerNum = 20;
        Stock stock = new Stock();
        // 开始信号
        CountDownLatch startSignal = new CountDownLatch(1);
        // 结束信号
        CountDownLatch endSignal = new CountDownLatch(buyerNum);

        // 创建抢购者线程池
        ExecutorService executor = Executors.newFixedThreadPool(buyerNum);
        for (int i = 0; i < buyerNum; i++) {
            executor.submit(new Buyer(stock, startSignal, endSignal));
        }

        System.out.println("开始抢购！");
        startSignal.countDown(); // 发送开始信号
        endSignal.await(); // 等待所有抢购者线程结束
        System.out.println("抢购结束！成功抢购人数：" + (10 - stock.getCount()));
        executor.shutdown(); // 关闭线程池
    }
}
