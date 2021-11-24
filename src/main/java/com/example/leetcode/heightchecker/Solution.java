package com.example.leetcode.heightchecker;

import java.util.Arrays;

/**
 * heightChecker
 * 学校打算为全体学生拍一张年度纪念照。根据要求，学生需要按照 非递减 的高度顺序排成一行。
 * 排序后的高度情况用整数数组 expected 表示，其中 expected[i] 是预计排在这一行中第 i 位的学生的高度（下标从 0 开始）。
 * 给你一个整数数组 heights ，表示 当前学生站位 的高度情况。heights[i] 是这一行中第 i 位学生的高度（下标从 0 开始）。
 * 返回满足 heights[i] != expected[i] 的 下标数量 。
 * <p>
 * 输入：heights =[1,1,4,2,1,3]
 * 输出：3
 * 解释：
 * 高度：[1,1,4,2,1,3]
 * 预期：[1,1,1,2,3,4]
 * 下标 2 、4 、5 处的学生高度不匹配。
 * <p>
 * 示例 2：
 * <p>
 * 输入：heights = [5,1,2,3,4]
 * 输出：5
 * 解释：
 * 高度：[5,1,2,3,4]
 * 预期：[1,2,3,4,5]
 * 所有下标的对应学生高度都不匹配。
 */
public class Solution {

    /**
     * 高度检查器
     *
     * @param heights
     * @return
     */
    public int heightChecker(int[] heights) {
        //拷贝数组
        int temp[] = Arrays.copyOf(heights, heights.length);
        return compare(heights, temp);
    }

    /**
     * 比较方法
     *
     * @param heights
     * @param temp
     * @return
     */
    private int compare(int[] heights, int temp[]) {
        Arrays.sort(temp);
        int count = 0;
        for (int i = 0; i < temp.length; i++) {
            if (heights[i] != temp[i]) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] heights = {1, 1, 4, 2, 1, 3};
        Solution solution = new Solution();
        int num = solution.heightChecker(heights);
        System.out.println(num);
    }
}
