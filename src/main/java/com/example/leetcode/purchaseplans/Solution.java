package com.example.leetcode.purchaseplans;

import java.util.Arrays;

/**
 * * 小力将 N 个零件的报价存于数组 nums。小力预算为 target，假定小力仅购买两个零件，要求购买零件的花费不超过预算，
 * * 请问他有多少种采购方案。
 */
public class Solution {
    /**
     * @param nums
     * @param target
     * @return
     */
    public int purchasePlans(int[] nums, int target) {
        bucketSort(nums);
        long mod = 1000000007;
        // 二分查找，提前定位可能范围，较少遍历的次数
        int q = Arrays.binarySearch(nums, target), p = 0, result = 0;
        if (q < 0) q = -q - 2;
        while (p < q) {
            int price1 = nums[p];
            while (q > p && price1 + nums[q] > target) q--;
            result = (int) ((result + (q - p)) % mod);
            p++;
        }
        return result;
    }

    private void bucketSort(int[] nums) {
        int[] bucket = new int[100001];
        for (int num : nums) bucket[num]++;
        int i = 0, j = 0, n = nums.length;
        while (j < n) {
            if (bucket[i] == 0) i++;
            else {
                nums[j++] = i;
                bucket[i]--;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 2, 1, 9};
        //
        int target = 10;
        int n = solution.purchasePlans(nums, target);
        System.out.println(n);
    }

}
