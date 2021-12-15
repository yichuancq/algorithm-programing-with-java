package com.example.leetcode.coinchange;

import java.util.Arrays;

/**
 * 动态规划，找硬币
 */
public class Solution4 {

    /**
     * 找硬币
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int amount = 100;
        int[] coins = {1, 2, 5};
        Solution4 solution = new Solution4();
        int n = solution.coinChange(coins, amount);
        System.out.println(n);
    }
//    public int coinChange(int[] coins, int amount) {
//        if (amount == 0 || coins == null) return 0;
//        int n = coins.length;
//        int[] dp = new int[amount + 1];
//        for (int i = 1; i <= amount; i++) {
//            for (int j = 0; j < n; j++) {
//                if (coins[j] == i) {
//                    dp[i] = 1;
//                } else if (coins[j] < i && dp[i] == 0 && dp[i - coins[j]] != 0) {
//                    dp[i] = dp[i - coins[j]] + 1;
//                } else if (coins[j] < i && dp[i - coins[j]] != 0) {
//                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
//                }
//            }
//        }
//        return dp[amount] == 0 ? -1 : dp[amount];
//    }
}
