package com.example.learn.char2;

public class SortMain {
    public static void main(String[] args) {
        //int[] keys = Array1.randomInt(10,100);          //例9.1
//      int[] keys = {32,26,87,72,26,17};             //图9.1 直接插入排序；图9.3 冒泡排序
//      int[] keys = {38,55,65,97,27,76,27,13,19};    //图9.2 希尔排序
//      int[] keys = {1,2,3,4,5,6,7,8};                   //冒泡排序，快速排序，最小堆
//      int[] keys = {1,3,2,4,5,8,6,7};                   //冒泡排序
//      int[] keys = {4,5,8,1,2,7,3,6};                   //冒泡排序
//      int[] keys = {8,7,6,5,4,3,2,1};                   //冒泡排序，快速排序
//        int[] keys = {38,38,97,75,61,19,26,49};           //图9.6~7 快速排序
//        int[] keys = {38,97,26,19,38,15};                  //图9.8 直接选择排序
//        int[] keys = {81,49,19,38,97,76,13,27};           //图9.9 最小/大堆
        int[] keys = {81, 49, 19, 38, 97, 76, 13, 19};           //图9.10 堆排序
//    int[] keys = {97,82,75,53,17,61,70,12,61,58,26};     //图9.12 归并排序

        System.out.print("关键字序列：");
        Array1.print(keys);  //见例1.4
        MySorting.insertSort(keys);
        MySorting.shellSort(keys);
        MySorting.bubbleSort(keys);                        //升序
        MySorting.bubbleSort(keys, false);                  //降序
        MySorting.quickSort(keys);
        MySorting.selectSort(keys);
        MySorting.heapSort(keys, true);                    //最小堆，降序
        MySorting.heapSort(keys, false);                   //最大堆，升序
        MySorting.heapSort(keys, true);                    //（第5版）升序，最大堆
        MySorting.heapSort(keys, false);                    //（第5版）降序，最小堆
        MySorting.mergeSort(keys);
    }
}
