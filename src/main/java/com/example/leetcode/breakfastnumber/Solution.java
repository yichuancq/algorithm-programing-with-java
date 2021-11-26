package com.example.leetcode.breakfastnumber;

import java.util.Arrays;

/**
 * 小扣在秋日市集选择了一家早餐摊位，一维整型数组 staple 中记录了每种主食的价格，一维整型数组 drinks 中记录了每种饮料的价格。
 * 小扣的计划选择一份主食和一款饮料，且花费不超过 x 元。请返回小扣共有多少种购买方案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：staple = [10,20,5], drinks = [5,5,2], x = 15
 * <p>
 * 输出：6
 * <p>
 * 解释：小扣有 6 种购买方案，所选主食与所选饮料在数组中对应的下标分别是：
 * 第 1 种方案：staple[0] + drinks[0] = 10 + 5 = 15；
 * 第 2 种方案：staple[0] + drinks[1] = 10 + 5 = 15；
 * 第 3 种方案：staple[0] + drinks[2] = 10 + 2 = 12；
 * 第 4 种方案：staple[2] + drinks[0] = 5 + 5 = 10；
 * 第 5 种方案：staple[2] + drinks[1] = 5 + 5 = 10；
 * 第 6 种方案：staple[2] + drinks[2] = 5 + 2 = 7。
 */
public class Solution {

    /**
     * @param staple
     * @param drinks
     * @param x
     * @return
     */
    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        int n = 0;
        if (staple == null || drinks == null || staple.length == 0 || drinks.length == 0) {
            return 0;
        }
        Arrays.sort(staple);
        Arrays.sort(drinks);
        for (int i = 0; i < staple.length; ) {
            for (int j = 0; j < drinks.length; j++) {
                if (staple[i] + drinks[j] <= x) {
                    n++;
                    System.out.println(String.format("staple[%d]+drinks[%d]=%d ",
                            i, j, staple[i] + drinks[j]));
                } else {
                    break;
                }
            }
            i++;
        }
        return n;
    }

    /**
     * 方法2
     *
     * @param staple
     * @param drinks
     * @param x
     * @return
     */

    public int breakfastNumber2(int[] staple, int[] drinks, int x) {
        int ans = 0;
        Arrays.sort(staple);
        Arrays.sort(drinks);
        for (int s : staple) {
            if (s + drinks[0] > x) {
                break;
            }
            int left = 0, right = drinks.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (s + drinks[mid] <= x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            ans += left;
            ans %= 1e9 + 7;
        }
        return ans;
    }

    /**
     * 方法3
     *
     * @param staple
     * @param drinks
     * @param x
     * @return
     */
    public int breakfastNumber3(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        long sum = 0;
        int j = drinks.length - 1;
        for (int i = 0; i < staple.length && staple[i] < x; i++) {
            for (; j >= 0; j--) {
                if (drinks[j] + staple[i] <= x) {
                    sum += (j + 1);
                    break;
                }
            }
        }
        return new Long(sum % 1000000007).intValue();
    }

    public static void main(String[] args) {
//        int staple[] = {10, 20, 5};
//        int drinks[] = {5, 5, 2};
//        int x = 15;
        int staple[] = {2, 1, 1};
        int drinks[] = {8, 9, 5, 1};
        int x = 9;
        Solution solution = new Solution();
        int n = solution.breakfastNumber3(staple, drinks, x);
        System.out.println(n);
    }
}
