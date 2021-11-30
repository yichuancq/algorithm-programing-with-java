package com.example.leetcode.findlength;

/**
 * 给两个整数数组A和B ，返回两个数组中公共的、长度最长的子数组的长度
 */
public class Solution3 {
    /**
     * 滑动窗口
     *
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {
        int lenA = A.length;
        int lenB = B.length;
        int max = 0;
        for (int i = 0; i < lenA; i++) {
            max = getMax(B, A, lenB, max, i);
        }
        for (int i = 0; i < lenB; i++) {
            max = getMax(A, B, lenA, max, i);
        }
        return max;
    }

    private int getMax(int[] A, int[] B, int lenA, int max, int i) {
        int count = 0;
        for (int j = 0; j < lenA; j++) {
            if (i + j >= B.length) {
                break;
            }
            if (B[i + j] == A[j]) {
                count++;
            } else {
                count = 0;
            }
            max = Math.max(count, max);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        int[] num1 = {1, 2, 3, 2, 1};
        int[] num2 = {3, 2, 1, 4, 7};
        int n = solution.findLength(num1, num2);
        System.out.println(n);
    }
}
