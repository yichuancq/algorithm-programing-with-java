package com.example.leetcode.maxsubarray;

import java.util.Arrays;

/**
 * 给定一个整数数组，找出总和最大的连续数列，并返回总和。
 * <p>
 * 示例：
 * 输入： [-2,1,-3,4,-1,2,1,-5,4]
 * 输出： 6
 * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class Solution {

    /**
     * 找出总和最大的连续数列，并返回总和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums != null && nums.length == 1) {
            return nums[0];
        }
        //max 的初始值从array0开始
        int max = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0] + nums[1]);
        for (int i = 1; i < nums.length; i++) {
            //动态规划转移方程
            //f(i)=max{f(i−1)+nums[i],nums[i]}
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 方法2
     *
     * @param nums
     * @return
     */
    private int maxSubArray2(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j < i) {
                    int t = this.fun(nums, j, i);
                    max = Math.max(max, t);
                }
            }
        }
        return max;
    }

    /**
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int fun(int[] nums, int start, int end) {
        int n = 0;
        for (int i = start; i <= end; i++) {
            n += nums[i];
        }
        return n;
    }


    /**
     * 执行用时:1ms,在所有Java提交中击败了89.45%的用户
     * 内存消耗:38.3 MB,在所有Java提交中击败了71.66%的用户
     * 通过测试用例:202/202
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] array = {-2, 1}; //1
        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(Arrays.toString(array));
        int max = solution.maxSubArray(array);
        System.out.println(max);
    }
}
