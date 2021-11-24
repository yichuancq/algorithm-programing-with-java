package com.example.leetcode.intersect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 给定两个数组，编写一个函数来计算它们的交集
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * <p>
 * 示例 2:
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 */
public class Solution {
    /**
     * 给定两个数组,计算它们的交集
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length <= nums2.length) {
            return calc(nums1, nums2);
        } else {
            return calc(nums2, nums1);
        }
    }


    /**
     * 方法1 双层循序对比
     *
     * @param nums1
     * @param nums2
     * @return
     */
    private int[] calc(int[] nums1, int[] nums2) {
        List<Integer> integerList = new ArrayList<>();
        List<Integer> integerList2 = new ArrayList<>();
        for (Integer integer : nums2) {
            integerList2.add(integer);
        }
        for (int i = 0; i < nums1.length; i++) {
            int count = 0;
            Iterator iterator = integerList2.iterator();
            while (iterator.hasNext()) {
                Integer integer = (Integer) iterator.next();
                //对比一次成功，跳过后面元素
                if (nums1[i] == integer && count < 1) {
                    //发现对比成功，累加计数器
                    count++;
                    integerList.add(nums1[i]);
                    //移除并集元素，已经对比的
                    iterator.remove();
                }
            }
        }
        int[] result = new int[integerList.size()];
        int i = 0;
        for (Integer integer : integerList) {
            result[i] = integer;
            i++;
        }
        return result;
    }


    /**
     * 排序预处理
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num1 = {4, 9, 5};
        int[] num2 = {9, 4, 9, 8, 4};
        int[] result = solution.intersect2(num1, num2);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
