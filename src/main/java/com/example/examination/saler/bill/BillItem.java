package com.example.examination.saler.bill;

import com.example.examination.saler.goods.Goods;

/**
 * 订单item
 */
public class BillItem<T> {

    /**
     * 商品泛型
     */
    private T t;
    /**
     * 数量
     */
    private double amount;
    /**
     * 小计
     */
    private double price;

    /**
     * @param t
     * @param amount
     */
    public BillItem(T t, double amount) {
        Goods goods = (Goods) t;
        this.t = t;
        this.amount = amount;
        //小计
        this.setPrice(goods.getPrice() * this.amount);
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BillItem{" + "t=" + t + ", amount=" + amount + ", price=" + price + '}';
    }
}
