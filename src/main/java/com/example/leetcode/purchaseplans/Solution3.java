package com.example.leetcode.purchaseplans;

import java.util.Arrays;

/**
 * * 小力将 N 个零件的报价存于数组 nums。小力预算为 target，假定小力仅购买两个零件，要求购买零件的花费不超过预算，
 * * 请问他有多少种采购方案。
 */
class Solution3 {

    /**
     * @param nums
     * @param target
     * @return
     */
    public int purchasePlans(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            System.out.println("Find" + (target - nums[i]));
            int cnt = upperBound(nums, target - nums[i]);
            System.out.println("Locate" + cnt);
            if (cnt > i) {
                System.out.println("Number 增加" + (cnt - i - 1));
                res += (cnt - i - 1);
                res %= 1000000007;
            }
        }
        return res % 1000000007;
    }

    public static int upperBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        if (target >= nums[nums.length - 1]) return nums.length;
        //if (target >= nums[nums.length-1]) return nums.length-1; 根据题意判断
        if (target < nums[0]) return 0; //这里可以设置成-1 或者 0

        while (left < right) {
            int m = left + (right - left) / 2;
            if (nums[m] <= target) {
                left = m + 1;
            } else if (nums[m] > target) {
                right = m;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        int[] nums = {2, 2, 1, 9};
        //
        int target = 10;
        int n = solution.purchasePlans(nums, target);
        System.out.println(n);
    }
}