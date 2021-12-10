package com.example.leetcode.mycalendar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 请实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内没有其他安排，则可以存储这个新的日程安排。
 * MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，即 [start, end),
 * 实数x 的范围为，start <= x < end。
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。
 * 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true。否则，返回 false并且不要将该日程安排添加到日历中。
 * 请按照以下步骤调用 MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * 输入:
 * ["MyCalendar","book","book","book"]
 * [[],[10,20],[15,25],[20,30]]
 * 输出: [null,true,false,true]
 * 解释:
 * MyCalendar myCalendar = new MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false ，第二个日程安排不能添加到日历中，因为时间 15 已经被第一个日程安排预定了
 * MyCalendar.book(20, 30); // returns true ，第三个日程安排可以添加到日历中，因为第一个日程安排并不包含时间 20
 */
public class MyCalendar {
    private List<Integer> days;

    public MyCalendar() {
        days = new ArrayList<>();
    }


    /**
     * @param start
     * @param end
     * @return
     */
    public boolean book(int start, int end) {
        for (int i = start; i < end; i++) {
            int key = i;
            int index = this.binarySearch(key);
            //find
            if (index >= 0) {
                return false;
            }
        }
        for (int i = start; i < end; i++) {
            days.add(i);
        }
        Collections.sort(days);
        System.out.println(days);
        return true;
    }

    private Integer binarySearch(int key) {
        int index = Collections.binarySearch(days, key);
        return index;
    }

    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
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
