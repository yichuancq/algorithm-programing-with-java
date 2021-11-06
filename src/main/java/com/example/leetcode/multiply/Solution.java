package com.example.leetcode.multiply;

/**
 * 递归乘法。 写一个递归函数，不使用 * 运算符，
 * 实现两个正整数的相乘。可以使用加号、减号、位移
 *
 * @author yichuan
 */
public class Solution {

    /**
     * n*m 本质是n行m列相加的和
     *
     * @param i
     * @param j
     * @return
     */
    public int multiply(int i, int j) {
        int n = i;
        if (i == 0 || j == 0) {
            return 0;
        }
        n = multiply(i, j - 1);
        n += i;
        return n;
    }
    public static void main(String[] args) {
        //3*4=12
        Solution solution = new Solution();
        int result = solution.multiply(3, 4);
        System.out.println(result);

    }
}
