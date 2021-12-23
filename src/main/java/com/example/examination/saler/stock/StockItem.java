package com.example.examination.saler.stock;

import com.example.examination.saler.goods.Goods;

public class StockItem<T> extends Goods<T> {
    private double stockAmount;

    public StockItem() {
        super();
    }

    /**
     * @param goodsNumber
     * @param name
     * @param price
     */
    public StockItem(String goodsNumber, String name, double price, double stockAmount) {
        super(goodsNumber, name, price);
        this.stockAmount = stockAmount;
    }

    /**
     * @param goods
     * @param stockAmount
     */
    public StockItem(Goods goods, double stockAmount) {
        super(goods.getGoodsNumber(), goods.getName(), goods.getPrice());
        this.stockAmount = stockAmount;
    }


    @Override
    public String toString() {
        return "StockItem{" + "stockAmount=" + stockAmount + '}';
    }

    public double getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(double stockAmount) {
        this.stockAmount = stockAmount;
    }
}
