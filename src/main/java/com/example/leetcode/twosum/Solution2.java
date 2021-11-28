package com.example.leetcode.twosum;

/**
 * 给定一个已按照 非递减顺序排列 的整数数组numbers ，请你从数组中找出两个数满足相加之和等于目标数target 。
 * <p>
 * 函数应该以长度为2的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，
 * 所以答案数组应当满足1<= answer[0] < answer[1] <= numbers.length
 * <p>
 * 示例 1：
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * <p>
 * 示例 2：
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 */
public class Solution2 {

    /**
     * 二分查找的思想
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length - 1;
        for (int i = 0, j = n; i < j; ) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[]{i + 1, j + 1};
            } else if (sum > target) {
                j--;
            } else if (sum < target) {
                i++;
            }

        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] numbers = {2, 3, 4};
        int target = 7;
        int rs[] = solution.twoSum(numbers, target);
        if (rs != null)
            for (int i : rs) {
                System.out.print(i + " ");
            }
    }
}