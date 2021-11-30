package com.example.leetcode.findlength;

/**
 * 给两个整数数组A和B返回两个数组中公共的、长度最长的子数组的长度
 */
public class Solution {
    /**
     * 动态规划
     *
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    System.out.println(String.format("dp[%d][%d]=%d", i, j, dp[i][j]));
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num1 = {1, 2, 3, 2, 1};
        int[] num2 = {3, 2, 1, 4, 7};
        int n = solution.findLength(num1, num2);
        System.out.println(n);

    }
}
