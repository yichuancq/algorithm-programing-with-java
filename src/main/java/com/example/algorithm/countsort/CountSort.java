package com.example.algorithm.countsort;

import java.util.Arrays;
import java.util.Random;

/**
 * 计数排序
 */
public class CountSort {

    public int[] sort(int[] array) {
        int b[] = new int[array.length];
        int max = array[0], min = array[0];
        for (int i : array) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }//这里k的大小是要排序的数组中，元素大小的极值差+1
        int k = max - min + 1;
        int c[] = new int[k];
        for (int i = 0; i < array.length; ++i) {
            c[array[i] - min] += 1;//优化过的地方，减小了数组c的大小
        }
        for (int i = 1; i < c.length; ++i) {
            c[i] = c[i] + c[i - 1];
        }
        for (int i = array.length - 1; i >= 0; --i) {
            b[--c[array[i] - min]] = array[i];//按存取的方式取出c的元素
        }
        return b;
    }

    private int[] builderArray(int amount) {
        int[] array = new int[amount];
        for (int i = 0; i < amount; i++) {
            array[i] = new Random().nextInt(amount);
        }
        return array;
    }

    public static void main(String[] args) {
        CountSort sort = new CountSort();
        int[] array = sort.builderArray(10);
        System.out.println("before:");
        System.out.println(Arrays.toString(array));
        array = sort.sort(array);
        System.out.println("after");
        System.out.println(Arrays.toString(array));
    }
}
