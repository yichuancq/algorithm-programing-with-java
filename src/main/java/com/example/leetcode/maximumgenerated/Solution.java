package com.example.leetcode.maximumgenerated;
/**
 * 给你一个整数 n 。按下述规则生成一个长度为 n + 1 的数组 nums ：
 * nums[0] = 0
 * nums[1] = 1
 * 当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
 * 当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
 * 返回生成数组 nums 中的 最大 值。
 * <p>
 * 示例 1：
 * 输入：n = 7
 * 输出：3
 * 解释：根据规则：
 * nums[0] = 0
 * nums[1] = 1
 * nums[(1 * 2) = 2] = nums[1] = 1
 * nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
 * nums[(2 * 2) = 4] = nums[2] = 1
 * nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
 * nums[(3 * 2) = 6] = nums[3] = 2
 * nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
 * 因此，nums = [0,1,1,2,1,3,2,3]，最大值 3
 */
public class Solution {

    /**
     * @param n
     * @return
     */
    public int getMaximumGenerated(int n) {
        if (n == 0 || n == 1) return n;
        int[] dp = new int[n + 1];
        int ans = 0;
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                //当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
                System.out.println("偶数");
                dp[i] = dp[i / 2];
                //System.out.println(String.format("dp[%d]=%d", i, dp[i]));
            } else {
                //当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]
                System.out.println("奇数");
                dp[i] = dp[i / 2] + dp[(i + 1) / 2];
            }
        }
        for (int j : dp) {
            ans = Math.max(ans, j);
        }
        return ans;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = solution.getMaximumGenerated(7);
        System.out.println(n);
    }
}
