package com.example.algorithm.stock;

/**
 * @author yichuan
 * @version 1.0
 * @description: 库存
 * @date 2023/6/14 18:00
 */
public class Stock {
    // 初始商品数量
    private int count = 10;

    /**
     * 尝试抢购商品，如果抢购成功则返回 true，
     * 否则返回 false
     */
    public synchronized boolean buy() {
        if (count > 0) {
            count--;
            return true;
        }
        return false;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
