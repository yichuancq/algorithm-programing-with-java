package com.example.leetcode.arithmeticprogression;

import java.util.Arrays;

/**
 * 给你一个数字数组 arr 。
 * 如果一个数列中，任意相邻两项的差总等于同一个常数，那么这个数列就称为 等差数列 。
 * <p>
 * 如果可以重新排列数组形成等差数列，请返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 输入：arr = [3,5,1]
 * 输出：true
 * 解释：对数组重新排序得到 [1,3,5] 或者 [5,3,1] ，任意相邻两项的差分别为 2 或 -2 ，可以形成等差数列。
 */
public class ArithmeticProgression {
    /**
     * 等差数列判断
     *
     * @param arr
     * @return
     */
    public boolean canMakeArithmeticProgression(int[] arr) {
        boolean flag = false;
        if (arr == null || arr.length == 0) {
            return false;
        }
        Arrays.sort(arr);
        showData(arr);
        if (arr.length == 2 || arr.length == 1) {
            return true;
        }
        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i + 1] * 2 == arr[i] + arr[i + 2]) {
                System.out.println(String.format("%d*2=%d+%d", arr[i + 1], arr[i], arr[i + 2]));
                flag = true;
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private void showData(int[] arr) {
        for (int ints : arr) {
            System.out.print(ints + " ");
        }

    }

    public static void main(String[] args) {

        int arr[] = {20, 17, -18, -13, 13, -14, -8, 10, 1, 14, -19};
        //[13,12,-12,9,9,16,7,-10,-20,0,18,-1,-20,-10,-8,15,15,16,2,15]
        ArithmeticProgression arithmeticProgression = new ArithmeticProgression();
        boolean flag = arithmeticProgression.canMakeArithmeticProgression(arr);
        System.out.println(flag);
    }

}
