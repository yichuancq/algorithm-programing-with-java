

package com.example.leetcode.merge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给你两个按 非递减顺序 排列的整数数组nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 */
public class Solution {

    /**
     * 合并两个有序数组
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public int[] merge(int[] nums1, int m, int[] nums2, int n) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            integerList.add(nums1[i]);
        }
        for (int i = 0; i < n; i++) {
            integerList.add(nums2[i]);
        }
        Collections.sort(integerList);
        //重新排序num1
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = integerList.get(i);
        }
        return nums1;
    }


    public static void main(String[] args) {
        int[] num1 = {1, 2, 3, 0, 0, 0};
        int[] num2 = {2, 5, 6};
        int m = 3;
        int n = 3;
        Solution solution = new Solution();
        int[] rs = solution.merge(num1, m, num2, n);
        for (int r : rs) {
            System.out.print(r + " ");
        }

    }
}
