package com.example.algorithm.search.binarydecisionsearch;

import com.example.algorithm.sortedsingllist.SortedSinglyList;
import com.example.algorithm.sortedsingllist.SortedSinglyLists;
import com.example.algorithm.sorting.Array1;

public class BinaryDecisionSearch {
    //对排序数组values[]进行二分法查找，输出每个元素的二分法查找结果，确定二叉判定树
    public static void print(int[] values) {
        System.out.print("排序关键字序列（升序）: ");
        Array1.print(values);                              //见例1.4

        if (values == null || values.length == 0)
            return;
        //将int[]转换成Integer[]。SortedArray中，查找Integer[]，循环；查找int[]，递归算法
        Integer[] keys = new Integer[values.length];
        for (int i = 0; i < keys.length; i++)
            keys[i] = values[i];

        //排序单链表（升序）list，存储查找每个元素的比较次数
        SortedSinglyList<Integer> list = new SortedSinglyList<Integer>();
        for (int i = 0; i < keys.length; i++)                  //查找每个元素
        {
            System.out.print("二分法查找 " + keys[i] + "，");
            int find = SortedArray.binarySearch(keys, keys[i]);  //二分法查找，显示查找过程
            System.out.println(" " + ((find == -1) ? "不" : "") + "成功");
            list.insert(new Integer(SortedArray.count));   //排序单链表，插入比较次数
        }
        System.out.print("ASL成功=");
        SortedSinglyLists.average(list);         //按ASL成功格式输出list排序单链表，计算平均值
    }

    public static void main(String[] args) {
        int[] values = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};  //图8.2 8个元素
        //输出二分法查找的二叉判定树
        BinaryDecisionSearch.print(values);

    }
}
