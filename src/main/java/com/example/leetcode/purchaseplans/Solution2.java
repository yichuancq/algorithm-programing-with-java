package com.example.leetcode.purchaseplans;

/**
 * * 小力将 N 个零件的报价存于数组 nums。小力预算为 target，假定小力仅购买两个零件，要求购买零件的花费不超过预算，
 * * 请问他有多少种采购方案。
 */
public class Solution2 {
    /**
     * @param nums
     * @param target
     * @return
     */
    public int purchasePlans(int[] nums, int target) {
        long ans = 0;
        long[] map = new long[target + 2];
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {//如果nums[i]大于target，没有讨论的意义
                k++;
                continue;
            }
            map[nums[i]]++;
        }
        if (k == nums.length) return 0; //map全是0
        for (int i = 1; i <= target; i++) {
            map[i] = map[i - 1] + map[i];
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {//如果nums[i]大于target，没有讨论的意义
                continue;
            }
            int num = nums[i];
            ans = ans + map[target - num];
            if (num <= target - num) ans--;//去重
        }
        ans = ans / 2;
        ans = ans % 1000000007;
        return (int) (ans);
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] nums = {2, 2, 1, 9};
        //
        int target = 10;
        int n = solution.purchasePlans(nums, target);
        System.out.println(n);
    }
}
