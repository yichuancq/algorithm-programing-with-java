package com.example.leetcode.twosum;

import java.util.Arrays;

/**
 * 给定一个已按照非递减顺序排列的整数数组numbers ，请你从数组中找出两个数满足相加之和等于目标数target 。
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
public class Solution {

    /**
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        //是否找打目标值的两个数的和
        Arrays.sort(numbers);
        int result[] = null;
        for (int i = 0; i < numbers.length && numbers[i] <= target; i++) {
            int j = 0;
            while (j + 1 <= numbers.length && numbers[i] + numbers[j] <= target) {
                if (numbers[i] + numbers[j] == target) {
                    result = new int[2];
                    int first = i + 1;
                    int second = j + 1;
                    result[0] = Math.min(first, second);
                    result[1] = Math.max(first, second);
                    // System.out.println(String.format("numbers[%d]+numbers[%d]=%d", i, j, target));
                    break;
                }
                j++;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] numbers = {2, 3, 4};
        int[] numbers = {0, 0, 3, 4};
        int target = 0;
//        int[] numbers = {-1, 0};
//        int target = -1;
        int rs[] = solution.twoSum(numbers, target);
        if (rs != null)
            for (int i : rs) {
                System.out.print(i + " ");
            }
    }
}
