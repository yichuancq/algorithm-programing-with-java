package com.example.algorithm.quicksort;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * a、确认列表第一个数据为中间值，第一个值看成空缺（低指针空缺）
 * b、然后在剩下的队列中，看成有左右两个指针（高低）
 * c、开始高指针向左移动，如果遇到小于中间值的数据，则将这个数据赋值到低指针空缺，并且将高指针的数据看成空缺值（高指针空缺）
 * 然后先向右移动一下低指针，并且切换低指针移动
 * <p>
 * d、当低指针移动到大于中间值的时候，赋值到高指针空缺的地方。然后先高指针向左移动，并且切换高指针移动。重复c、d操作
 * e、直到高指针和低指针相等时退出，并且将中间值赋值给对应指针位置
 * f、然后将中间值的左右两边看成行的列表，进行快速排序操作
 */
public class QuickSort {


    public int[] sort(int[] array) {
        //快速排序
        int low = 0;
        int high = array.length - 1;
        quickSort(array, low, high);
        return array;
    }

    /**
     * 快速排序
     *
     * @param arr
     * @param start
     * @param end
     * @return
     */
    public int[] quickSort(int arr[], int start, int end) {
        int pivot = arr[start];
        int i = start;
        int j = end;
        while (i < j) {
            while ((i < j) && (arr[j] > pivot)) {
                j--;
            }
            while ((i < j) && (arr[i] < pivot)) {
                i++;
            }
            if ((arr[i] == arr[j]) && (i < j)) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        if (i - 1 > start) arr = quickSort(arr, start, i - 1);
        if (j + 1 < end) arr = quickSort(arr, j + 1, end);
        return (arr);
    }

    private void printData(int[] array) {
        List<Integer> integerList = Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println(integerList);
    }


    /**
     * 生成随机数数组
     *
     * @param amount
     * @return
     */
    private int[] builderArray(int amount) {
        int[] array = new int[amount];
        for (int i = 0; i < amount; i++) {
            array[i] = new Random().nextInt(amount);
        }
        return array;
    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] array = sort.builderArray(10);
        System.out.println("before:");
        sort.printData(array);
        sort.sort(array);
        System.out.println("after");
        sort.printData(array);

    }
}
