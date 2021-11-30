package com.example.leetcode.findkthpositive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 给你一个 严格升序排列 的正整数数组 arr 和一个整数 k
 * 请你找到这个数组里第 k 个缺失的正整数
 * <p>
 * 示例 1：
 * 输入：arr = [2,3,4,7,11], k = 5
 * 输出：9
 * 解释：缺失的正整数包括 [1,5,6,8,9,10,12,13,...] 。第 5 个缺失的正整数为 9
 */
public class Solution {
    /**
     * @param arr
     * @param k
     * @return
     */
    public int findKthPositive(int[] arr, int k) {
        int c = 0;
        int value = 1;
        while (true) {
            if (search(arr, value)) {
                c++;
            }
            if (c == k) {
                break;
            }
            ++value;
        }
        return value;
    }

    public boolean search(int arr[], int value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == value) return false;
            else if (arr[mid] > value) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        //请你找到这个数组里第 k 个缺失的正整数
        Solution solution = new Solution();
        int array[] = {5, 6, 7, 8, 9};
        ArrayList<Integer> integers = (ArrayList) Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println(integers);
        int k = 9;
        int result = solution.findKthPositive(array, k);

        System.out.println(result);
    }

}
