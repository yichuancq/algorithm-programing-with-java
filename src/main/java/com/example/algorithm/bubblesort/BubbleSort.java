package com.example.algorithm.bubblesort;

import java.util.*;
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
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i; j >= 0; j--) {
                //swap
                if (array[j + 1] < array[j]) {
                    this.swap(array, j, j + 1);
                }
            }
        }
        return array;
    }

    private void printData(int[] array) {
        List<Integer> integerList = Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println(integerList);
    }


    private int[] builderArray(int amount) {
        int[] array = new int[amount];
        List<Integer> integerList = new ArrayList<>();
        int counter = 0;
        while (true) {
            int val = new Random().nextInt(amount);
            if (!integerList.contains(val)) {
                integerList.add(val);
                counter++;
                if (counter == amount) {
                    break;
                }
            }

        }
        Iterator iterator = integerList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            array[i] = integer;
            i++;
        }
        return array;
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
        BubbleSort bubbleSort = new BubbleSort();
        int[] array = bubbleSort.builderArray(10);
        System.out.println("before:");
        bubbleSort.printData(array);
        bubbleSort.sort(array);
        System.out.println("after");
        bubbleSort.printData(array);
    }
}
