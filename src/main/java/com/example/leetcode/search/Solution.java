package com.example.leetcode.search;

import java.util.Arrays;

/**
 * 存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，
 * 则返回 true ，否则返回 false 。
 * <p>
 * 示例 1：
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 */
public class Solution {

    /**
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Arrays.sort(nums);
        int index = Arrays.binarySearch(nums, target);
        if (index >= 0) {
            return true;
        }
        return false;
    }

    // Like public version, but without range checks.
    private int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key) low = mid + 1;
            else if (midVal > key) high = mid - 1;
            else return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    public static void main(String[] args) {
        int[] number = {2, 5, 6, 0, 0, 1, 2};
        int target = 99;
        Solution solution = new Solution();
        boolean flag = solution.search(number, target);
        System.out.println(flag);
    }

}
