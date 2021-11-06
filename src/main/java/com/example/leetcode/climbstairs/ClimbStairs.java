package com.example.leetcode.climbstairs;

/**
 * 假设你正在爬楼梯。需要 n阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 */
public class ClimbStairs {
    // 常规方式
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 用变量记录代替数组
    public int climbStairs2(int n) {
        int a = 0, b = 1, c = 0; // 默认需要1次
        for (int i = 1; i <= n; i++) {
            c = a + b;          // f(i - 1) + f(n - 2)
            a = b;              // 记录上一轮的值
            b = c;              // 向后步进1个数
        }
        return c;
    }

    public static void main(String[] args) {
        int n = 5;
        ClimbStairs climbStairs = new ClimbStairs();
        int result = climbStairs.climbStairs(n);
        System.out.println(result);

    }
}
