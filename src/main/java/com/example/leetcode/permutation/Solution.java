package com.example.leetcode.permutation;


import java.util.Arrays;

/**
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列
 */
public class Solution {


    /**
     * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = i + 1;
        }
        perm(numbers, 0, n - 1);
        return "";
    }


    /**
     * @param array
     * @param start
     * @param end
     */
    public void perm(int[] array, int start, int end) {
        if (start == end) {
            System.out.println(Arrays.toString(array));
        } else {
            for (int i = start; i <= end; i++) {
                //1，2，3的全排列这块相当于将其中一个提了出来，下次递归从start+1开始
                swap(array, start, i);
                //
                perm(array, start + 1, end);
                //这块是复原数组，为了保证下次另外的同级递归使用数组不会出错
                //这块可以通过树来理解，每次回退一步操作，交换回去
                swap(array, start, i);

                // swap(array, start, i);
            }
        }
    }

    /**
     * @param source
     * @param i
     * @param j
     */
    private void swap(int[] source, int i, int j) {
        Integer temp = source[i];
        source[i] = source[j];
        source[j] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String last = solution.getPermutation(3, 3);
        //n = 3, k = 1
        //输出："123"
        System.out.println(last);
    }
}
