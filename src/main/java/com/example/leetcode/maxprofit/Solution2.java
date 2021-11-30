package com.example.leetcode.maxprofit;

/**
 * 定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润
 * <p>
 * 示例 1：
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票
 */
public class Solution2 {


    /**
     * 1 枚举所有发生一次交易的股价差
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int n = 0;
        int np[] = new int[2];
        for (int i = 0; i < prices.length; i++) {
            for (int j = 0; j < i; j++) {
                int max = prices[i] - prices[j];
                System.out.println(String.format("[%d]-[%d]=%d", prices[i], prices[j], max));
                n = Math.max(max, n);
            }
        }
        return n;
    }

    public static void main(String[] args) {
        int[] dayPrice = {7, 1, 5, 3, 6, 4};
        Solution2 solution = new Solution2();
        int n = solution.maxProfit(dayPrice);
        System.out.println("最大收益:" + n);
    }
}
