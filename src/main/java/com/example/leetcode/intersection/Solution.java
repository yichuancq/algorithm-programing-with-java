package com.example.leetcode.intersection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * 说明：
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class Solution {

    /**
     * 给定两个数组，编写一个函数来计算它们的交集
     *
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        List resultList = new ArrayList<>();
        List<Integer> integerList1 = new ArrayList<>();
        List<Integer> integerList2 = new ArrayList<>();
        for (int temp : nums1) {
            integerList1.add(temp);
        }
        for (int temp : nums2) {
            integerList2.add(temp);
        }
        //sort
        Collections.sort(integerList1);
        //sort
        Collections.sort(integerList2);
        for (int temp : nums1) {
            if (integerList2.contains(temp)) {
                if (!resultList.contains(temp)) {
                    resultList.add(temp);
                }
            }
        }
        int[] last = new int[resultList.size()];
        int i = 0;
        for (Object obj : resultList.toArray()) {
            last[i] = Integer.valueOf((Integer) obj).intValue();
            i++;
        }
        return last;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int nums1[] = new int[]{1, 2, 2, 1};
        int nums2[] = new int[]{2, 2};
        int[] last = solution.intersection(nums1, nums2);
        for (int val : last) {
            System.out.println(val);
        }

    }
}
