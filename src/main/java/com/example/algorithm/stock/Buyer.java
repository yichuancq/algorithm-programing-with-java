package com.example.algorithm.stock;

import java.util.concurrent.CountDownLatch;

/**
 * @author yichuan
 * @version 1.0
 * @description: 消费者
 * @date 2023/6/14 18:01
 */
public class Buyer implements Runnable {
    private Stock stock;
    private CountDownLatch startSignal;
    private CountDownLatch endSignal;

    public Buyer(Stock stock, CountDownLatch startSignal, CountDownLatch endSignal) {
        this.stock = stock;
        this.startSignal = startSignal;
        this.endSignal = endSignal;
    }

    @Override
    public void run() {
        try {
            // 等待开始信号
            startSignal.await();
            // 尝试抢购商品
            while (stock.buy()) {
                System.out.println(Thread.currentThread().getName() + " 抢到了商品");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            endSignal.countDown(); // 发送结束信号
        }
    }
}

