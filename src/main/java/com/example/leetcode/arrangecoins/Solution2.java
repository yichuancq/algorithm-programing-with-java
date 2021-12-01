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
public class Solution2 {
    /***
     *
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
        long start = 0, end = (long) n / 2 + 1, mid;
        while (end - start > 1) {
            mid = (start + end) >>> 1;
            if (mid * (mid + 1) == (long) 2 * n) {
                return (int) mid;
            } else if (mid * (mid + 1) <= (long) 2 * n) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return (end * (end + 1) == (long) 2 * n) ? (int) end : (int) start;
    }


    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int n = 5;
        int result = solution.arrangeCoins(n);
        System.out.println(result);

    }
}

