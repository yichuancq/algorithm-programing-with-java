package com.example.leetcode.mysqrt;

/**
 * 给定一个非负整数 x ，计算并返回 x 的平方根，即实现int sqrt(int x)函数。
 * 正数的平方根有两个，只输出其中的正数平方根。
 * 如果平方根不是整数，输出只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * 输入: x = 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: x = 8
 * 输出: 2
 * 解释: 8 的平方根是 2.82842...，由于小数部分将被舍去，所以返回 2
 */
public class Solution {

    /**
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int l = 1;
        int r = x;
        while (l < r) {
            int mid = (r - l + 1) / 2 + l;
            if ((long) mid * mid > x) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int number = 2147395600;
        //46340
        int n = solution.mySqrt(number);
        System.out.println(n);

    }
}
