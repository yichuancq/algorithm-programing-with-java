package com.example.leetcode.fib;

/**
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 */
public class FibNumber {
    /**
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        int result = 0;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            result = fib(n - 1) + fib(n - 2);
        }
        return result;
    }

    public static void main(String[] args) {
        FibNumber fibNumber = new FibNumber();
        int n = fibNumber.fib(4);
        System.out.println(n);
    }

}
