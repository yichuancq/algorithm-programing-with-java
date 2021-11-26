package com.example.leetcode.finderrornums;

import java.util.Arrays;
import java.util.HashSet;


/**
 * 找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回
 * 示例 1：
 * 输入：nums = [1,2,2,4]
 * 输出：[2,3]
 */
public class Solution2 {

    /**
     * 找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回
     *
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            Integer key = i + 1;
            //重复
            if (hashSet.contains(nums[i])) {
                result[0] = nums[i];
            }
            //否则添加到set
            hashSet.add(nums[i]);
            //缺失
            if (!hashSet.contains(key)) {
                result[1] = key;
            }
        }
        return result;
    }

    /**
     * 方法2。如果找到重复直接退出循环
     *
     * @param nums
     * @return
     */
    public int[] findErrorNums2(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            //重复
            if (nums[i + 1] - (nums[i]) == 0) {
                result[0] = nums[i + 1];
                break;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            hashSet.add(nums[i]);
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            j++;
            //缺失
            if (!hashSet.contains(j)) {
                result[1] = j;
            }
        }
        return result;
    }

    /**
     * 方法3
     *
     * @param nums
     * @return
     */
    public int[] findErrorNums3(int[] nums) {
        int[] result = new int[2];
        int[] temp = new int[nums.length + 1];
        for (int num : nums) {
            temp[num]++;
        }
        for (int i = 1; i < temp.length; i++) {
            if (temp[i] == 1)
                continue;
            if (temp[i] == 2)
                result[0] = i;
            else
                result[1] = i;
        }
        return result;
    }


    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int nums[] = {3, 2, 3, 4, 6, 5};
        int[] result = solution.findErrorNums3(nums);
        for (int temp : result) {
            System.out.print(temp + " ");
        }
    }

}
