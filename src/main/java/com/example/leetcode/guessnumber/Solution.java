package com.example.leetcode.guessnumber;

import java.util.Random;

/**
 * 从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
 * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了
 * 1 <= n <= 231 - 1
 * 1 <= pick <= n
 */


public class Solution extends GuessGame {
    public Solution(int pk) {
        super(pk);
    }

    /**
     * @param n
     * @return
     */
    public int guessNumber(int n) {
        //返回我选出的数字
        System.out.println("pick:" + this.pick);
        int left = 1;
        int right = n;
        while (left <= right) {
            System.out.println(String.format("left:%d right:%d", left, right));
            int middle = ((right - left) / 2) + left;
            System.out.println("猜的数字:" + middle);
            if (guess(middle) == 0) {
                System.out.println("我选出的数字和你猜的数字一样。恭喜！你猜对了！");
                break;
            }
            //我选出的数字比你猜的数字小 pick < num
            else if (guess(middle) == -1) {
                System.out.println("我选出的数字比你猜的数字小 pick < num");
                right = middle - 1;
            } else if (guess(middle) == 1) {
                System.out.println("我选出的数字比你猜的数字大 pick > num");
                left = middle + 1;
            }
        }
        return pick;
    }


    /**
     * 本质是二分查找指定n个自然数的一个随机值
     *
     * @param args
     */
    public static void main(String[] args) {
        int n = 999;
        Random random = new Random();
        int pick = random.nextInt(n) + 1;
        Solution solution = new Solution(pick);
        Integer intRs = solution.guessNumber(n);
        System.out.println(intRs);
    }
}
