package com.example.algorithm.insetsort;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 插入排序
 */
public class InsertSort {

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
        for (int i = 1; i < array.length; i++) {
            //外层循环，从第二个开始比较
            for (int j = i; j > 0; j--) {
                //内存循环，与前面排好序的数据比较，如果后面的数据小于前面的则交换
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                }
            }
            System.out.println(String.format("第%d趟排序", i));
            printData(array);
        }
        return array;
    }


    /**
     * 交换元素
     *
     * @param array
     * @param i
     * @param j
     */
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void printData(int[] array) {
        List<Integer> integerList = Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println(integerList);
    }

    /**
     * 生成随机数数组，不重复
     *
     * @param amount
     * @return
     */
//    private int[] builderArray(int amount) {
//        int[] array = new int[amount];
//        List<Integer> integerList = new ArrayList<>();
//        int counter = 0;
//        while (true) {
//            int val = new Random().nextInt(amount);
//            if (!integerList.contains(val)) {
//                integerList.add(val);
//                counter++;
//                if (counter == amount) {
//                    break;
//                }
//            }
//
//        }
//        Iterator iterator = integerList.iterator();
//        int i = 0;
//        while (iterator.hasNext()) {
//            Integer integer = (Integer) iterator.next();
//            array[i] = integer;
//            i++;
//        }
//        return array;
//    }

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
        InsertSort insertSort = new InsertSort();
        int[] array = insertSort.builderArray(10);
        System.out.println("before:");
        insertSort.printData(array);
        insertSort.sort(array);
        System.out.println("after");
        insertSort.printData(array);

    }
}
