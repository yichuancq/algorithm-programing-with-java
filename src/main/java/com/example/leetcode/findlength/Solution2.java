package com.example.leetcode.findlength;

/**
 * 给两个整数数组A和B,返回两个数组中公共的.长度最长的子数组的长度
 */
public class Solution2 {

    /**
     * 状态压缩
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        int[] dp = new int[nums2.length + 1];
        int result = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = nums2.length; j > 0; j--) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                } else {
                    dp[j] = 0;
                }
                result = Math.max(result, dp[j]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] num1 = {1, 2, 3, 2, 1};
        int[] num2 = {3, 2, 1, 4, 7};
        int n = solution.findLength(num1, num2);
        System.out.println(n);
    }
}
