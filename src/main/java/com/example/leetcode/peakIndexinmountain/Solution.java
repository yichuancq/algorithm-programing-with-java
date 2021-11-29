package com.example.leetcode.peakIndexinmountain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 山脉数组的峰顶索引
 * 给你由整数组成的山脉数组arr返回任何满足arr[0]<arr[1]<... arr[i-1]<arr[i]>arr[i+1]>...>arr[arr.length-1]的下标i
 * <p>
 * 示例 1：
 * 输入：arr = [0,1,0]
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：arr = [0,2,1,0]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：arr = [0,10,5,2]
 * 输出：1
 */
public class Solution {
    /**
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        int[] copyOfArray = Arrays.copyOf(arr, arr.length);
        Arrays.sort(arr);
        int key = arr[arr.length - 1];
        return search(copyOfArray, key);

    }

    /**
     * @param array
     * @param key
     * @return
     */
    private int search(int array[], int key) {
        return binarySearch(array, 0, array.length, key);
    }

    /**
     * @param a
     * @param fromIndex
     * @param toIndex
     * @param key
     * @return
     */
    private int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) >>> 1;
            long midVal = a[mid];
            if (mid > 0) {
                //结束条件 arr[i-1]<arr[i]>arr[i+1]
                if (key == a[mid] && (a[mid - 1] < midVal && midVal > a[mid + 1])) {
                    break;
                } else if (a[mid - 1] < midVal && midVal < a[mid + 1]) {
                    low = mid + 1;
                } else if (a[mid - 1] > midVal && midVal > a[mid + 1]) {
                    high = mid - 1;
                }
            } else {
                mid = mid + 1;
                break;
            }
        }
        return mid;
    }

    /**
     * 本质求最大值的索引
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = {24, 69, 100, 99, 79, 78, 67, 36, 26, 19};
        ArrayList<Integer> integers = (ArrayList) Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println(integers);
        int index = solution.peakIndexInMountainArray(array);
        System.out.println(index);

    }

}
