package com.example.leetcode.isperfectsquare;

/**
 * 定一个正整数num，编写一个函数，如果num是一个完全平方数，则返回 true，否则返回false
 * <p>
 * 示例 1：
 * 输入：num = 16
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：num = 14
 * 输出：false
 */
public class Solution {

    /**
     * 判断num是一个完全平方数
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        boolean flag = false;
        if (num == 0 || num == 1) {
            return true;
        }
        int left = 1;
        int right = num;
        while (left <= right) {
            //求中间值
            int middle = (right - left + 1) / 2 + left;
            //如果两个数相乘和结果相同，则为完全平方数
            if ((long) (middle * middle) == num) {
                flag = true;
                return flag;
            } else if ((long) middle * middle > num) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int number = 64;
        boolean flag = solution.isPerfectSquare(number);
        System.out.println(flag);

    }
}
