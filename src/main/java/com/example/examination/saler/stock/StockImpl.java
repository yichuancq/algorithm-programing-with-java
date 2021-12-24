package com.example.examination.saler.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if (!stockItemList.contains(t)) {
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
        //List<StockItem> newList = stockItemList.stream().filter(item -> item.getGoodsNumber().equals(stockItem.getGoodsNumber())).limit(1).collect(Collectors.toList());
        //1、filter过滤 2、取第一个符合条件的，商品货号相同
        Optional optional = stockItemList.stream().filter(item -> item.getGoodsNumber().equals(stockItem.getGoodsNumber())).findFirst();
        if (optional.isPresent()) {
            StockItem stockItemMemory = (StockItem) optional.get();
            stockItemMemory.setStockAmount(stockItemMemory.getStockAmount() - stockItem.getStockAmount());
            System.out.println("出库商品：" + stockItem.getName() + ",冲减数量:" + stockItem.getStockAmount());
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
