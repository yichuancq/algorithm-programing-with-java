package com.example.leetcode.maxprofit;

public class Solution3 {

    /**
     * 滚动数组
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        int[][] dp = new int[2][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1] + prices[i]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], -prices[i]);
        }
        return dp[(len - 1) & 1][0];
    }


    public static void main(String[] args) {
        int[] dayPrice = {7, 1, 5, 3, 6, 4};
        Solution2 solution = new Solution2();
        int n = solution.maxProfit(dayPrice);
        System.out.println("最大收益:" + n);
    }


}
