package com.example.algorithm.lottery;

public class LotteryPlay {
    public static void main(String[] args) {

        Integer[] balls = new Integer[]{4, 25, 31, 29, 33, 15};
        Lottery lottery = new Lottery(balls);
        //
        Thread thread1 = new Thread(lottery);
        thread1.start();

        Thread thread2 = new Thread(lottery);
        thread2.start();

        Thread thread3 = new Thread(lottery);
        thread3.start();
    }
}
