package com.example.leetcode.searchinsert;

/**
 * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1
 * *示例 1
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * <p>
 * 示例 2
 * <p>
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 */
public class Search {

    public int search(int[] nums, int target) {
        int n = 0;
        n = binarySearch(nums, 0, nums.length, target);
        if (n >= 0) {
            return n;
        }
        return -1;
    }

    /**
     * 二分查找
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

        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = -1;
        Search search = new Search();
        int n = search.search(nums, target);
        System.out.println(n);
    }
}
