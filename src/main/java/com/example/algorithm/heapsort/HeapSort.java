package com.example.algorithm.heapsort;

import java.util.Arrays;
import java.util.Random;

/**
 * 堆排序
 */
public class HeapSort {

    /**
     * 堆筛选，除了start之外，start~end均满足大顶堆的定义。
     * 调整之后start~end称为一个大顶堆。
     *
     * @param array 待调整数组
     * @param start 起始指针
     * @param end   结束指针
     */
    public void heapAdjust(int[] array, int start, int end) {
        int temp = array[start];
        for (int i = 2 * start + 1; i <= end; i *= 2) {
            //左右孩子的节点分别为2*i+1,2*i+2
            //选择出左右孩子较小的下标
            if (i < end && array[i] < array[i + 1]) {
                i++;
            }
            if (temp >= array[i]) {
                break; //已经为大顶堆，=保持稳定性。
            }
            array[start] = array[i]; //将子节点上移
            start = i; //下一轮筛选
        }
        array[start] = temp; //插入正确的位置
    }


    public int[] heapSort(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        //建立大顶堆
        for (int i = array.length / 2; i >= 0; i--) {
            heapAdjust(array, i, array.length - 1);
        }

        for (int i = array.length - 1; i >= 0; i--) {
            swap(array, 0, i);
            heapAdjust(array, 0, i - 1);
        }
        return array;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int[] builderArray(int amount) {
        int[] array = new int[amount];
        for (int i = 0; i < amount; i++) {
            array[i] = new Random().nextInt(amount);
        }
        return array;
    }

    public static void main(String[] args) {
        HeapSort sort = new HeapSort();
        int[] array = sort.builderArray(10);
        System.out.println("before:");
        System.out.println(Arrays.toString(array));
        sort.heapSort(array);
        System.out.println("after");
        System.out.println(Arrays.toString(array));
    }
}
