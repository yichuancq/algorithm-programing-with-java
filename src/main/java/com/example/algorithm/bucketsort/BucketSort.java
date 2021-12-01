package com.example.algorithm.bucketsort;

import java.util.*;

/**
 * 桶排序
 */
public class BucketSort {
    private int bucketNum = 10; //这里默认为10，规定待排数[0,100)

    public int[] bucketSort(int[] array) {
        if (array == null && array.length == 0) {
            return array;
        }
        List<List<Integer>> buckets = new ArrayList<List<Integer>>(); //桶的索引
        for (int i = 0; i < bucketNum; i++) {
            buckets.add(new LinkedList<Integer>()); //用链表比较合适
        }
        //划分桶
        for (int i = 0; i < array.length; i++) {
            buckets.get(func(array[i])).add(array[i]);
        }
        //对每个桶进行排序
        for (int i = 0; i < buckets.size(); i++) {
            if (!buckets.get(i).isEmpty()) {
                Collections.sort(buckets.get(i)); //对每个桶进行快排
            }
        }
        //还原排好序的数组
        int k = 0;
        for (List<Integer> bucket : buckets) {
            for (int ele : bucket) {
                array[k++] = ele;
            }
        }
        return array;
    }

    /**
     * 映射函数
     *
     * @param x
     * @return
     */
    public int func(int x) {
        return x / bucketNum;
    }

    private int[] builderArray(int amount) {
        int[] array = new int[amount];
        for (int i = 0; i < amount; i++) {
            array[i] = new Random().nextInt(amount);
        }
        return array;
    }

    public static void main(String[] args) {
        BucketSort sort = new BucketSort();
        int[] array = sort.builderArray(10);
        System.out.println("before:");
        System.out.println(Arrays.toString(array));
        array = sort.bucketSort(array);
        System.out.println("after");
        System.out.println(Arrays.toString(array));
    }
}
