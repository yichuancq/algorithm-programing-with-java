package com.example.leetcode.recentcounter;

/**
 * 写一个RecentCounter类来计算特定时间范围内最近的请求。请实现 RecentCounter 类：
 * RecentCounter() 初始化计数器，请求数为 0 。
 * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数
 * 包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
 */
public class RecentCounter {

    public RecentCounter() {

    }

    public int ping(int t) {

        return 0;
    }

    public static void main(String[] args) {
        //todo  写一个 RecentCounter 类来计算特定时间范围内最近的请求。
        RecentCounter recentCounter = new RecentCounter();
        recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
        recentCounter.ping(100);   // requests = [1, 100]，范围是 [-2900,100]，返回 2
        recentCounter.ping(3001);  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
        recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3
    }
}
