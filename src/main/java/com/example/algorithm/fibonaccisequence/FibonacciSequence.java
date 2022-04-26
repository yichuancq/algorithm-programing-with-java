package com.example.algorithm.fibonaccisequence;

/**
 * 斐波拉契数列
 */
public class FibonacciSequence {

    /**
     * if n=0 f(n)=2,if n>0 f(n)=2+f(n-1)
     * 斐波拉契数列1
     *
     * @param n
     * @return
     */
    private static int fSequence1(int n) {
        int result = 0;
        if (n == 0) {
            return 2;

        }
        if (n > 0) {
            result = 2 + fSequence1(n - 1);
        }
        return result;
    }

    /**
     * 斐波拉契数列2
     *
     * @param n
     * @return
     */
    private static int fSequence2(int n) {
        int result = 0;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n > 0) {
            result = 1 + fSequence2(n - 2);
        }
        return result;
    }

    /**
     * 斐波拉契数列3
     *
     * @param n
     * @return
     */
    private static int fSequence3(int n) {
        int result = 0;
        if (n == 0) {
            return 0;
        }
        if (n > 0) {
            result = (2 * n) + fSequence3(n - 1);
        }
        return result;
    }

    /**
     * 斐波拉契数列4
     *
     * @param n
     * @return
     */
    private static int fSequence4(int n) {
        int result = 0;
        if (n == 0) {
            return 1;
        }
        if (n > 0) {
            result = 2 * fSequence4(n - 1);

        }
        return result;
    }

    /**
     * 斐波拉契数列5
     *
     * @param n
     * @return
     */
    private static int fSequence5(int n) {
        int result = 0;

        if (n == 0) {
            return 1;
        }
        if (n > 0) {
            result = 3 * fSequence4(n - 1);
        }

        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        final int n = 10;
        int result = fSequence1(n);
        System.out.println("fSequence1=" + result);
        //函数2
        result = fSequence2(n);
        System.out.println("fSequence2=" + result);
        //函数3
        result = fSequence3(n);
        System.out.println("fSequence3=" + result);
        //函数4
        result = fSequence4(n);
        System.out.println("fSequence4=" + result);
        //函数5
        final int m = 10;
        result = fSequence5(m);
        System.out.println(String.format("fSequence5(%d)=%d", m, result));


    }
}
