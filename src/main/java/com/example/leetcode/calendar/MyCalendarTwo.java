package com.example.leetcode.calendar;

import java.util.Map;
import java.util.TreeMap;

/***
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 * MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，
 * 即 [start,end),实数x 的范围为,start <= x < end
 *
 * 当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生三重预订。
 * 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致三重预订，返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。
 *
 */
class MyCalendarTwo {

    private Map<Integer, Integer> map;

    public MyCalendarTwo() {
        this.map = new TreeMap<>();
    }

    /****
     *
     * @param start
     * @param end
     * @return
     */
    public boolean book(int start, int end) {
        //返回 key 相映射的的 value，如果给定的 key 在映射关系中找不到，则返回指定的默认值
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int ans = 0;
        for (Integer n : map.values()) {
            ans += n;
            if (ans >= 3) {
                map.put(start, map.get(start) - 1);
                map.put(end, map.get(end) + 1);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
        myCalendarTwo.book(10, 20); // returns true
        myCalendarTwo.book(50, 60); // returns true
        myCalendarTwo.book(10, 40); // returns true
        myCalendarTwo.book(5, 15); // returns false
        myCalendarTwo.book(5, 10); // returns true
        myCalendarTwo.book(25, 55); // returns true
        // 创建一个 HashMap
    }
}
