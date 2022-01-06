package com.example.examination.saler.bill;

/**
 * 订单
 *
 * @param <T>
 */
public interface Bill<T> {

    /**
     * 添加商品
     *
     * @param t
     */
    public void addBill(T t);

    /**
     * 移除商品
     *
     * @param t
     */
    public void removeBill(T t);
}
