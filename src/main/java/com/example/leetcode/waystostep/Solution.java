package com.example.leetcode.waystostep;

/**
 * 三步问题
 * 有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。
 * 实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007
 */
public class Solution {

    /**
     * @param n
     * @return
     */
    public int waysToStep(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 4;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        for (int i = 3; i < n; i++) {
            //递推公式(绘制演算方法，推导公式)
            //dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]);
            dp[i] += (dp[i - 3] + dp[i - 2]) % 1000000007;
            dp[i] %= 1000000007;
            dp[i] += dp[i - 1];
            dp[i] %= 1000000007;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        int n = 61;
        int ways = solution.waysToStep(n);
        System.out.println(ways);

    }

}
