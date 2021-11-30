package com.example.algorithm.bubble;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 冒泡排序
 */
public class BubbleSort {

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
                //swap
                if (array[i] < array[j]) {
                    this.swap(array, i, j);
                }
            }
        }
        return array;
    }

    private void printData(int[] array) {
        List<Integer> integerList = Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println(integerList);
    }

    /**
     * @param array
     * @param i
     * @param j
     */
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
//        int[] array = {1, 1, 0, 0, 0, 3, 3, 2, 2, 6, 7, 9, 12, 10};
        int[] array = {1, 3, 2, 5, 7, 9, 10, 8};
        BubbleSort bubbleSort = new BubbleSort();
        System.out.println("before:");
        bubbleSort.printData(array);
        bubbleSort.sort(array);
        System.out.println("after");
        bubbleSort.printData(array);
    }
}
