package com.example.examination.saler;

import com.example.examination.saler.bill.Bill;
import com.example.examination.saler.bill.BillItem;
import com.example.examination.saler.goods.Goods;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/***
 *
 * @param <T>
 */
public class SalerImpl<T> implements Bill<T> {
    private List<BillItem> goodsList = new ArrayList<>();
    //数量
    private double goodsAmount;
    //件数
    private int count = 0;
    //总价
    private double totalPrice;

    /**
     * 订单流水号
     */
    private String billNumber;

    private String happenTime;

    public SalerImpl() {
        billNumber = String.valueOf(UUID.randomUUID());
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        happenTime = simpleDateFormat.format(date);
        //
        System.out.println(String.format("No:%s \r\ntime:%s", billNumber, happenTime));
    }

    /**
     * 添加商品
     *
     * @param t
     */
    @Override
    public void addBill(T t) {
        goodsList.add((BillItem) t);
    }

    /**
     * 移除商品
     *
     * @param t
     */
    @Override
    public void removeBill(T t) {
        goodsList.remove((BillItem) t);
    }

    /**
     * 显示订单信息
     */
    public void showOrder() {
        System.out.println("===订单如下==");
        for (BillItem billItem : goodsList) {
            Goods goods = (Goods) billItem.getT();
            //数量
            double amount = billItem.getAmount();
            //小计
            double price = billItem.getPrice();
            System.out.println(String.format("货号：%s,名称：%s,单价：%f,数量：%f，小计：%f ", goods.getGoodsNumber()
                    , goods.getName(), goods.getPrice(), amount, price));
        }
        System.out.println("===========");
    }

    /**
     * 显示合计信息
     */
    public void showTotalPrice() {
        for (BillItem billItem : goodsList) {
            //数量
            double amount = billItem.getAmount();
            //小计
            double price = billItem.getPrice();
            //数量
            this.goodsAmount += amount;
            //总价
            this.totalPrice += price;
        }
        this.count = getGoodsList().size();
        System.out.println(String.format("数量%f，件数%d，合计%f", goodsAmount, count, totalPrice));
        System.out.println("谢谢惠顾，欢迎下次光临！");
    }

    public List<BillItem> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<BillItem> goodsList) {
        this.goodsList = goodsList;
    }
}
