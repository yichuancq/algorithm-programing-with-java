package com.example.leetcode.climbstairs;

/**
 * 已知不同面值的钞票，求如 何用最少数量的钞票组成某个金额，求可 以使用的最少钞票数量。如果任意数量的已知面值钞票都无法组成该金额， 则返回-1
 * <p>
 * 1、首先将原问题拆分为子问题
 * 已知什么？显而易见，钞票的金额都只需要其本身1张即可，如何在已知钞票的情况下构造出 金额X需要的最少钞票组合
 * <p>
 * 2、确认状态
 * DP[0] - DP[amount] 表示构造金额amount需要的最小钞票数
 * <p>
 * 3、确认边界状态（初试条件）
 * DP[coin] = 1 其他的都未知初始值设为 -1
 * 例如coins = [1, 2, 5], amount = 11 已知 dp[1]、dp[2]、dp[5] =1
 * 现在已知 DP[coin] 需要求出每一个DP[amount]
 * <p>
 * 4、状态转移方程
 * dp[i] = min(dp[i-1], dp[i-2], dp[i-5]) + 1
 */
public class Coin {


    public int coinChange(int[] coins, int amount) {
        int len = coins.length;
        if (len == 0 || amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        // 初始化
        for (int i = 0; i <= amount; i++) {
            dp[i] = -1;
        }
        for (int i = 0; i < len; i++) {
            if (coins[i] == amount)
                return 1;
            if (coins[i] < amount)
                dp[coins[i]] = 1;
        }
        // 状态转移方程
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < len; j++) {
                if ((i - coins[j] >= 0) && dp[i - coins[j]] != -1) {
                    if (dp[i] == -1 || dp[i] > dp[i - coins[j]] + 1) {
                        dp[i] = dp[i - coins[j]] + 1;
                    }
                }
            }
        }
        // 返回值
        return dp[amount];
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        //
        Coin coin = new Coin();
        int ways = coin.coinChange(coins, amount);
        System.out.println(ways);

    }
}
