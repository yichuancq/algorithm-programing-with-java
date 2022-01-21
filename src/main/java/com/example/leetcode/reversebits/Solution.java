package com.example.leetcode.reversebits;

/**
 * 翻转数位
 * 给定一个32位整数 num，你可以将一个数位从0变为1。
 * 请编写一个程序，找出你能够获得的最长的一串1的长度。
 * <p>
 * 输入: num = 1775(110111011112)
 * 输出: 8
 */
public class Solution {
    public int reverseBits(int num) {
        int max = 0;
        int reverse = 0;
        int current = 0;
        for (int i = 0; i < 32; i++) {
            if ((num & 1) == 1) {
                current++;
                reverse++;
            } else {
                reverse = current + 1;
                current = 0;
            }
            if (reverse > max) max = reverse;
            num >>= 1;
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int number = 1775;
        int result = solution.reverseBits(number);
        System.out.println(result);
    }
}
