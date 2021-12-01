package com.example.algorithm.shellsort;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 希尔排序（插入排序变种版）
 * a、基本上和插入排序一样的道理
 * b、不一样的地方在于，每次循环的步长，通过减半的方式来实现
 * c、说明：基本原理和插入排序类似，不一样的地方在于。通过间隔多个数据来进行插入排序。
 */
public class ShellSort {


    /**
     * 排序
     *
     * @param array
     * @return
     */
    private int[] sort(int[] array) {
        //希尔排序
        int gap = array.length;
        while (true) {
            gap /= 2;   //增量每次减半
            for (int i = 0; i < gap; i++) {
                for (int j = i + gap; j < array.length; j += gap) {
                    //这个循环里其实就是一个插入排序
                    int k = j - gap;
                    while (k >= 0 && array[k] > array[k + gap]) {
                        int temp = array[k];
                        array[k] = array[k + gap];
                        array[k + gap] = temp;
                        k -= gap;
                    }

                }
            }
            if (gap == 1) {
                break;
            }
        }
        return array;
    }

    /**
     * @param array
     * @return
     */
    private int[] sort2(int[] array) {
        //希尔排序（插入排序变种版）
        //i层循环控制步长
        for (int i = array.length / 2; i > 0; i /= 2) {
            //j控制无序端的起始位置
            for (int j = i; j < array.length; j++) {
                for (int k = j; k > 0 && k - i >= 0; k -= i) {
                    if (array[k] < array[k - i]) {
                        int temp = array[k - i];
                        array[k - i] = array[k];
                        array[k] = temp;
                    } else {
                        break;
                    }
                }
            }
            //j,k为插入排序，不过步长为i
        }
        return array;
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

    public static void main(String[] args) {
        ShellSort sort = new ShellSort();
        int[] array = sort.builderArray(20);
        System.out.println("before:");
        sort.printData(array);
        sort.sort2(array);
        System.out.println("after");
        sort.printData(array);

    }
}
