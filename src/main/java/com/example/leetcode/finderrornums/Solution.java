package com.example.leetcode.finderrornums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 集合 s 包含从 1 到n的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，
 * 导致集合 丢失了一个数字 并且 有一个数字重复 。给定一个数组 nums 代表了集合 S 发生错误后的结果。
 * 找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回
 * <p>
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,2,4]
 * 输出：[2,3]
 */
public class Solution {
    /**
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            //如果数据重复
            if (map.containsValue(nums[i])) {
                result[0] = nums[i];
            }
            Integer key = i + 1;
            map.put(key, nums[i]);
        }
        judge(map, result);
        return result;
    }

    /**
     * 判断缺失数据
     *
     * @param map
     * @param result
     */
    private void judge(Map<Integer, Integer> map, int[] result) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() != entry.getValue()) {
                Integer key = entry.getKey();
                //缺失
                if (!map.containsValue(key)) {
                    result[1] = entry.getKey();
                    break;
                }
            }
        }
    }

    /**
     * @param map
     */
    private void printMap(Map<Integer, Integer> map) {
        System.out.println("打印map");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(String.format("map key:%d-> val:%d", entry.getKey(), entry.getValue()));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int nums[] = {3, 2, 3, 4, 6, 5};
        //[3,2,3,4,6,5]
        int[] result = solution.findErrorNums(nums);
        for (int temp : result) {
            System.out.print(temp + " ");
        }
    }
}
