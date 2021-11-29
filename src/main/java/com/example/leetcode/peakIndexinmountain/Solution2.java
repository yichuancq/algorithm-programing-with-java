package com.example.leetcode.peakIndexinmountain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution2 {

    /**
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        return binarySearch(arr, 0, arr.length);

    }

    /**
     * @param a
     * @param fromIndex
     * @param toIndex
     * @return
     */
    private int binarySearch(int[] a, int fromIndex, int toIndex) {
        int low = fromIndex;
        int high = toIndex - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) >>> 1;
            long midVal = a[mid];
            if (mid > 0) {
                //结束条件 arr[i-1]<arr[i]>arr[i+1]
                if ((a[mid - 1] < midVal && midVal > a[mid + 1])) {
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
        Solution2 solution = new Solution2();
        int[] array = {24, 69, 100, 99, 79, 78, 67, 36, 26, 19};
        ArrayList<Integer> integers = (ArrayList) Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println(integers);
        int index = solution.peakIndexInMountainArray(array);
        System.out.println(index);

    }

}
