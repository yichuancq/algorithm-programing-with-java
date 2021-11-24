package com.example.leetcode.containsduplicate;


import java.util.Arrays;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 */
public class ContainsDuplicate {

    /**
     * 方法1：
     * 判断是否存在重复元素，存在返回true
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        boolean flag = false;
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] == nums[i]) {
                flag = true;
                break;
            } else {
                flag = false;
            }
        }

        return flag;
    }


    /**
     * 方法2：
     * 判断是否存在重复元素，存在返回true
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate2(int[] nums) {
        boolean flag = false;
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                } else {
                    flag = false;
                }
            }
        }
        return flag;
    }

    public static void main(String[] args) {

//        int array[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int array[] = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        //1,2,3,4
        ContainsDuplicate containsDuplicate = new ContainsDuplicate();
        // boolean flag = containsDuplicate.containsDuplicate(array);
        //System.out.println(flag);
        boolean flag = containsDuplicate.containsDuplicate(array);
        System.out.println(flag);
    }
}
