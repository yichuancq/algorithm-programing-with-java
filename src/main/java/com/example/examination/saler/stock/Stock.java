package com.example.examination.saler.stock;

/**
 * 库存
 */
public interface Stock<T> {
    /**
     * 进货
     *
     * @param t
     */
    void reStock(T t);

    /**
     * 出库
     *
     * @param t
     */
    void outStock(T t);
}
