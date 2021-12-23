package com.example.examination.saler.goods;

/**
 * 商品
 */
public abstract class Goods<T> {

    /**
     * 货号
     */
    private String goodsNumber;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 价格
     */
    private double price;

    public Goods() {
    }

    /**
     * @param name
     * @param price
     */
    public Goods(String goodsNumber, String name, double price) {
        this.goodsNumber = goodsNumber;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    @Override
    public String toString() {
        return "Goods{" + "goodsNumber='" + goodsNumber + '\'' + ", name='" + name + '\'' + ", price=" + price + '}';
    }
}
