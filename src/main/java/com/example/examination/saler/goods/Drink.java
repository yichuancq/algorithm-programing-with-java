package com.example.examination.saler.goods;

/**
 * 饮料
 *
 * @param <T>
 */
public class Drink<T> extends Goods<T> {
    /**
     * @param goodsNumber
     * @param name
     * @param price
     */
    public Drink(String goodsNumber, String name, double price) {
        super(goodsNumber, name, price);
    }
}
