package com.example.leetcode.arrangecoins;

/**
 * 你总共有n枚硬币,并计划将它们按阶梯状排列。对于一个由k行组成的阶梯,其第i行必须正好有i枚硬币。
 * 阶梯的最后一行可能是不完整的。
 * 给你一个数字n,计算并返回可形成完整阶梯行的总行数
 * <p>
 * 输入：n = 5
 * 输出：2
 * 解释：因为第三行不完整，所以返回 2
 */
public class Solution {


    /**
     * 排列硬币
     *
     * @param n
     * @return count
     */
    public int arrangeCoins(int n) {
        int count = 0;
        int coins = 0;
        while (coins <= n) {
            ++count;
            coins = func(count);
            if (n - coins < 0) {
                break;
            }
        }
        return count - 1;
    }

    /**
     * 递归计算
     *
     * @param n
     * @return
     */
    private int func(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return n + func(n - 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 1;
        int result = solution.arrangeCoins(n);
        System.out.println(result);
    }
}
