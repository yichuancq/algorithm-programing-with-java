package com.example.leetcode.maxproduct;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，使 (nums[i]-1)*(nums[j]-1) 取得最大值。
 * 请你计算并返回该式的最大值。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,5,2]
 * 输出：12
 * 解释：如果选择下标 i=1 和 j=2（下标从 0 开始），则可以获得最大值，(nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12 。
 */
public class Solution {
    /**
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        int[] result = new int[2];
        int i = nums.length - 1;
        int k = 0;
        for (int j = 0; j < nums.length; j++) {
            if (j < 2) {
                result[k] = nums[i] - 1;
                k++;
                i--;
            } else {
                break;
            }
        }
        int sum = 1;
        for (int m = 0; m < result.length; m++) {
            sum *= result[m];
        }
        return sum;
    }

    public static void main(String[] args) {
        int nums[] = {1, 5, 4, 5};
        Solution solution = new Solution();
        int n = solution.maxProduct(nums);
        System.out.println(n);
    }

}
