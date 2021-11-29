package com.example.leetcode.guessnumber;

public class GuessGame {
    public int pick;

    public GuessGame(int pk) {
        this.pick = pk;
    }

    /**
     * @param num
     * @return
     */
    public int guess(int num) {
        if (this.pick == num) {
            //我选出的数字和你猜的数字一样
            return 0;
        } else if (this.pick > num) {
            return 1;
        }
        return -1;

    }
}