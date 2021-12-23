package com.example.examination.saler.stock;

import java.util.ArrayList;
import java.util.List;

/**
 * 库存管理
 */
public class StockImpl<T> implements Stock<T> {
    private List<StockItem> stockItemList = new ArrayList<>();

    /**
     * 入库
     *
     * @param t
     */
    @Override
    public void reStock(T t) {
        if (!stockItemList.contains((StockItem) t)) {
            stockItemList.add((StockItem) t);
        }
    }

    /**
     * 出库
     *
     * @param t
     */
    @Override
    public void outStock(T t) {
        StockItem stockItem = (StockItem) t;
        for (StockItem stockItemDB : stockItemList) {
            // 商品货号相同
            if (stockItemDB.getGoodsNumber().equals(stockItem.getGoodsNumber())) {

                //冲减数量
                stockItemDB.setStockAmount(stockItemDB.getStockAmount() - stockItem.getStockAmount());
                System.out.println("出库商品：" + stockItem.getName() + ",冲减数量:" + stockItem.getStockAmount());
            }
        }
    }

    public void showStock() {
        System.out.println("===库存显示===");
        for (StockItem stockItem : stockItemList) {
            System.out.println(String.format("商品名称：%s,库存数量:%f", stockItem.getName(), stockItem.getStockAmount()));
        }
        System.out.println("=============");
    }
}
