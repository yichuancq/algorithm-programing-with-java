package com.example.leetcode.tribonacci;

/**
 * 泰波那契序列Tn定义如下:
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * <p>
 * 给你整数n，请返回第 n 个泰波那契数Tn 的值。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 */
public class Solution {

    /**
     * @param n
     * @return
     */
    public int tribonacci2(int n) {
        int[] dp = new int[n + 4];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 0; i < n + 1; i++) {
            //递推公式
            dp[i + 3] = dp[i] + dp[i + 1] + dp[i + 2];
        }
        return dp[n];
    }

    public int tribonacci(int n) {
        if (n <= 0) return 0;
        else if (n < 3) return 1;
        int firstValue = 0;
        int secondValue = 1;
        int thirdValue = 1;
        for (int i = 3; i <= n; i++) {
            int temp = thirdValue;
            thirdValue += (firstValue + secondValue);
            firstValue = secondValue;
            secondValue = temp;
        }
        return thirdValue;
    }


    private int fib(int n) {
        int result = 0;
        if (n == 0) {
            result = 0;
        } else if (n == 1) {
            result = 1;
        } else {
            result = fib(n - 1) + fib(n - 2);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int tribonacci = solution.tribonacci2(4);
        System.out.println(tribonacci);
    }
}
