package com.example.leetcode.mycalendar;

import java.util.Map;
import java.util.TreeMap;

public class MyCalendar2 {
    // 用红黑树存储时间段<start,end>，根据时间 start 进行排序
    private TreeMap<Integer, Integer> timeMap;

    // 初始化
    public MyCalendar2() {
        timeMap = new TreeMap<>();
    }

    /**
     * 预定
     *
     * @param start
     * @param end
     * @return
     */
    public boolean book(int start, int end) {
        // 获取比当前时间段前的最接近的时间
        Map.Entry<Integer, Integer> floorPeriod = timeMap.floorEntry(start);
        // 获取比当前时间段后的最接近的时间
        Map.Entry<Integer, Integer> ceilPeriod = timeMap.ceilingEntry(start);
        // 记录 "比当前时间段前的最近时间" 的 "结束时间"
        int lastEnd = Integer.MIN_VALUE;
        // 记录 "比当前时间段后的最近时间" 的 "开始时间"
        int nextStart = Integer.MAX_VALUE;
        if (floorPeriod != null) {
            lastEnd = floorPeriod.getValue();
        }
        if (ceilPeriod != null) {
            nextStart = ceilPeriod.getKey();
        }
        // 如果当前开始时间比 最近上一段时间的结束时间 晚，又比 最近下一段时间的开始时间 早，则可以预定
        if (lastEnd <= start && end <= nextStart) {
            timeMap.put(start, end);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        MyCalendar2 myCalendar = new MyCalendar2();
        boolean flag = myCalendar.book(47, 50);
        System.out.println(flag);
        flag = myCalendar.book(33, 41);
        System.out.println(flag);
        flag = myCalendar.book(39, 45);
        System.out.println(flag);
        flag = myCalendar.book(33, 42);
        System.out.println(flag);
        //
        flag = myCalendar.book(25, 32);
        System.out.println(flag);
        flag = myCalendar.book(26, 35);
        System.out.println(flag);
        flag = myCalendar.book(19, 25);
        System.out.println(flag);
        //
        flag = myCalendar.book(3, 8);
        System.out.println(flag);
        flag = myCalendar.book(8, 13);
        System.out.println(flag);
        flag = myCalendar.book(18, 27);
        System.out.println(flag);

    }
}
