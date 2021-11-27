package com.example.leetcode.searchinsert;

import java.util.Arrays;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <p>
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * <p>
 * 示例2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 */
public class Solution {

    /**
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int arrLen = nums.length + 1;
        int newArray[] = Arrays.copyOf(nums, arrLen);
        newArray[arrLen - 1] = target;
        int index = binarySearch(nums, 0, nums.length, target);
        if (index >= 0) {
            return index;
        } else {
            //排序
            Arrays.sort(newArray);
            return binarySearch(newArray, 0, newArray.length, target);
        }
    }

    /**
     * 二分搜索
     *
     * @param array
     * @param fromIndex
     * @param toIndex
     * @param key
     * @return
     */
    private static int binarySearch(int[] array, int fromIndex, int toIndex, Integer key) {
        int low = fromIndex;
        int high = toIndex - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            //int mid = (low + high) >>> 1;
            long midVal = array[mid];
            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                // key found
                return mid;
        }
        // key not found.
        return -(low + 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int num[] = {1, 3, 5};
        int target = 1;
        int n = solution.searchInsert(num, target);
        System.out.println("index:" + n);
    }
}
