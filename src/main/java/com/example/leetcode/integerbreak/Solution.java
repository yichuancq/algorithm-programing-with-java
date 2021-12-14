package com.example.leetcode.integerbreak;

/**
 * 给定一个正整数n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * <p>
 * 示例2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 ×3 ×4 = 36
 * <p>
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 */
public class Solution {

    public int integerBreak(int n) {
        //dp[i]为正整数i拆分结果的最大乘积
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; ++i) {
            for (int j = 1; j < i - 1; ++j) {
                //j*(i-j)代表把i拆分为j和i-j两个数相乘
                //j*dp[i-j]代表把i拆分成j和继续把(i-j)这个数拆分，取(i-j)拆分结果中的最大乘积与j相乘
                int max = Math.max(j * (i - j), j * dp[i - j]);
                dp[i] = Math.max(dp[i], max);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 10;
        int result = solution.integerBreak(n);
        System.out.println(result);
    }
}
