package com.example.leetcode.massage;

/**
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。
 * 给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数
 * <p>
 * 示例 3：
 * <p>
 * 输入： [2,1,4,5,3,1,1,3]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
 */
public class Solution {
    /**
     * @param nums
     * @return
     */
    public int massage(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }
        // dp[i]：区间 [0, i] 里接受预约请求的最大时长
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            // 今天在选与不选中，选择一个最优的
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[len - 1];
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] numbers = {2, 1, 4, 5, 3, 1, 1, 3};
        int n = solution.massage(numbers);
        System.out.println(n);
    }
}
