package com.example.leetcode.findkthpositive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 请你找到这个数组里第 k 个缺失的正整数
 */
public class Solution2 {

    /**
     * @param arr
     * @param k
     * @return
     */
    public int findKthPositive(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            System.out.println(" left " + left + " right " + right);
            int mid = left + (right - left) / 2;
            int miss = arr[mid] - mid - 1;
            if (miss < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return k + left;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int array[] = {5, 6, 7, 8, 9};
        ArrayList<Integer> integers = (ArrayList) Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println(integers);
        int k = 9;
        int result = solution.findKthPositive(array, k);
        System.out.println(result);
    }
}
