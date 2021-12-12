package com.example.leetcode.search;

/**
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length上进行了旋转,使数组变为 [nums[k],
 * nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1
 */
public class Solution2 {

    /**
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    private int search(int[] nums, int low, int high, int target) {
        if (low > high) return -1;
        int mid = (low + high) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] < nums[high]) {
            if (nums[mid] < target && target <= nums[high]) {
                return search(nums, mid + 1, high, target);
            } else {
                return search(nums, low, mid - 1, target);
            }
        } else {
            if (nums[low] <= target && target < nums[mid]) {
                return search(nums, low, mid - 1, target);
            } else {
                return search(nums, mid + 1, high, target);
            }
        }
    }

    public static void main(String[] args) {
        int[] number = {2, 5, 6, 0, 0, 1, 2};
        int target = 5;
        Solution2 solution = new Solution2();
        int index = solution.search(number, target);
        System.out.println(index);
    }

}
