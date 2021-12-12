package com.example.leetcode.smallestk;

import java.util.Arrays;

/**
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 * 示例：
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 */
public class Solution {
    /**
     * @param arr
     * @param k
     * @return
     */
    public static int[] smallestK(int[] arr, int k) {
        quickSort(arr, 0, arr.length - 1, k);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    private static void quickSort(int[] arr, int startIndex, int endIndex, int k) {
        if (startIndex >= endIndex) {
            return;
        }
        int partitionIndex = quickSortPartition(arr, startIndex, endIndex);
        if (partitionIndex == k) {
            return;
        }
        if (k < partitionIndex) {
            quickSort(arr, startIndex, partitionIndex - 1, k);
        } else {
            quickSort(arr, partitionIndex + 1, endIndex, k);
        }
    }


    private static int quickSortPartition(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[endIndex];
        int i = startIndex;
        for (int j = startIndex; j <= endIndex - 1; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, endIndex);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
        int k = 4;
        int arrays[] = smallestK(arr, k);
        System.out.println(Arrays.toString(arrays));
    }
}
