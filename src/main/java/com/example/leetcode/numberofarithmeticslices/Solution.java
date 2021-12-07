package com.example.leetcode.numberofarithmeticslices;

/**
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的子数组个数。
 */
public class Solution {

    /**
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }
        // 定义一个 dp[nums.length] 数组， 记录 dp[i] 以 i 结尾的等差数列子数组的个数
        // dp[2] = nums[0] - nums[1] == nums[1] - nums[2] ? 1 : 0;
        // nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2] ， 如果 i 于前两个构成等差数列的话
        // 必定于 dp[i - 1] 构成等差数列，所以 dp[i] = dp[i - 1] + 1;
        int[] dp = new int[nums.length];
        if (nums[0] - nums[1] == nums[1] - nums[2]) {
            dp[2] = 1;
        }

        int sum = dp[2];
        for (int i = 3; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                sum += dp[i];
            }
        }
        return sum;
    }

    //135(1)
    //135,357,1357(3) 4->3
    //135,357,579,159,1357,3579,13579 (6) 5->6
    //135,357,579,79 11,159,37 11,13579 11(10) 6->10
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = {1, 3, 5, 7};
//        int[] array = {1, 2, 3, 4};
        int n = solution.numberOfArithmeticSlices(array);
        System.out.println(n);
    }
}
