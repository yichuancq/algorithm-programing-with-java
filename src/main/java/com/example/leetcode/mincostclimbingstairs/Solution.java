package com.example.leetcode.mincostclimbingstairs;

/**
 * 数组的每个下标作为一个阶梯，第i个阶梯对应着一个非负数的体力花费值cost[i]（下标从 0 开始）。
 * 每当爬上一个阶梯都要花费对应的体力值，一旦支付了相应的体力值，就可以选择向上爬一个阶梯或者爬两个阶梯。
 * 请找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为0或1的元素作为初始阶梯
 */
public class Solution {
    /**
     * 求爬阶梯的最小花费
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int dp[] = new int[len];//走过阶梯i的体力，不是到达i的体力
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < len; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return Math.min(dp[len - 1], dp[len - 2]);
    }

    //    public int minCostClimbingStairs(int[] cost) {
//        int[] dp = new int[cost.length + 1];
//        for (int i = 2; i < dp.length; i++) {
//            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
//        }
//        return dp[cost.length];
//    }
    public static void main(String[] args) {
//        int[] cost = {10, 15, 20};
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        Solution solution = new Solution();
        int n = solution.minCostClimbingStairs(cost);
        System.out.println(n);
    }
}
