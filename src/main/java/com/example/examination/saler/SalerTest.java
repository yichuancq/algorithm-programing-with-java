package com.example.examination.saler;

import com.example.examination.saler.bill.BillItem;
import com.example.examination.saler.goods.Drink;
import com.example.examination.saler.goods.Fruit;
import com.example.examination.saler.goods.Vegetables;
import com.example.examination.saler.stock.StockImpl;
import com.example.examination.saler.stock.StockItem;

public class SalerTest {
    static StockImpl stock = new StockImpl();

    /**
     * 入库
     */
    private static void inStock() {
        //goods
        Vegetables vegetables1 = new Vegetables("10202", "番茄🍅", 6.56);
        Vegetables vegetables2 = new Vegetables("10265", "白菜", 4.50);
        Fruit fruit = new Fruit("20021", "火龙果", 6.66);
        Drink drink = new Drink("20022", "百事可乐", 3.00);
        //入库
        stock.reStock(new StockItem(vegetables1, 200));
        //入库
        stock.reStock(new StockItem(vegetables2, 200));
        //入库
        stock.reStock(new StockItem(fruit, 100));
        //入库
        stock.reStock(new StockItem(drink, 100));
        //显示库存
        stock.showStock();
    }

    /**
     * 销售
     */
    private static void saleGoods() {
        // 订单管理
        SalerImpl saler = new SalerImpl();
        //goods
        Vegetables vegetables1 = new Vegetables("10202", "番茄🍅", 6.56);
        Vegetables vegetables2 = new Vegetables("10265", "白菜", 4.50);
        Fruit fruit = new Fruit("20021", "火龙果", 6.66);
        Drink drink = new Drink("20022", "百事可乐", 3.00);
        // 订单item
        BillItem billItem1 = new BillItem<>(vegetables1, 0.52);
        BillItem billItem2 = new BillItem<>(vegetables2, 0.22);
        // Fruit
        BillItem billItem3 = new BillItem<>(fruit, 5.30);
        //
        BillItem billItem4 = new BillItem<>(drink, 1);
        // 添加到订单
        saler.addBill(billItem1);
        saler.addBill(billItem2);
        // 添加水果到订单
        saler.addBill(billItem3);
        saler.addBill(billItem4);
        //出库商品
        stock.outStock(new StockItem(vegetables1, billItem1.getAmount()));
        stock.outStock(new StockItem(vegetables2, billItem2.getAmount()));
        stock.outStock(new StockItem(fruit, billItem3.getAmount()));
        stock.outStock(new StockItem(drink, billItem4.getAmount()));
        // 显示订单
        saler.showOrder();
        // 合计信息
        saler.showTotalPrice();
    }

    public static void main(String[] args) {
        SalerTest.inStock();
        SalerTest.saleGoods();
        stock.showStock();
    }
}
