package com.example.algorithm.selectsort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 选择排序
 */
public class SelectSort {

    /**
     * 排序
     *
     * @param array
     * @return
     */
    private int[] sort(int[] array) {
        if (array == null || array.length == 0 || array.length == 1) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                int min = array[i];
                //如果后一个比数组首个元素值大
                if (array[j] - min > 0) {
                    //swap
                    swap(array, i, j);
                }
            }
        }
        return array;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 显示数组数据
     *
     * @param array
     */
    private void printData(int[] array) {
        List<Integer> integerList = Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println(integerList);
    }

    public static void main(String[] args) {
//        int[] array = {1, 0, 0, 0, 3, 3, 2, 2, 6, 7, 9, 12, 10};
        int[] array = {1, 3, 2, 7, 5, 9, 10, 8};
        SelectSort sort = new SelectSort();
        System.out.println("before:");
        sort.printData(array);
        sort.sort(array);
        System.out.println("after");
        sort.printData(array);
    }
}
