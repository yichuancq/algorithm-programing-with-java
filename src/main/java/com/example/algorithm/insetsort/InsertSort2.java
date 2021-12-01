package com.example.algorithm.insetsort;

import java.util.Arrays;
import java.util.Random;

public class InsertSort2 {
    /**
     * sort
     *
     * @param array
     * @return
     */
    public int[] sort(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        for (int i = 1; i < array.length; i++) {
            //假设第一个数位置时正确的；要往后移，必须要假设第一个。
            int j = i;
            int target = array[i]; //待插入的
            //后移
            while (j > 0 && target < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }
            //插入
            array[j] = target;
            System.out.println(Arrays.toString(array));
        }
        return array;
    }

    /**
     * 生成数组
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
        InsertSort2 insertSort = new InsertSort2();
        int[] array = insertSort.builderArray(10);
        System.out.println("before:");

        System.out.println(Arrays.toString(array));
        insertSort.sort(array);
        System.out.println("after");
        System.out.println(Arrays.toString(array));

    }
}
