package com.example.leetcode.missingnumber;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/***
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数…
 */
public class MissingNumber {
    private boolean flag = false;
    private List<Integer> integerList = new ArrayList<>();

    public int missingNumber(int[] nums) {
        for (int i = 0; i <= nums.length; i++) {
            integerList.add(i);
        }
        calc(nums);
        return integerList.get(0);
    }

    /**
     * @param array
     * @return
     */
    private boolean calc(int[] array) {
        printData(array);
        int count = array.length;
        int half = count / 2;
        int tempLeft[] = new int[half];
        int tempRight[] = new int[count - half];
        int j = 0;
        for (int i = 0; i < count; i++) {
            if (i < half) {
                tempLeft[i] = array[i];
            } else {
                tempRight[j] = array[i];
                j++;
            }
        }
        if (half == 0) {
            flag = true;
        } else {
            flag = false;
        }
        if (!flag) {
            flag = calc(tempLeft);
            flag = calc(tempRight);
        }
        return flag;
    }

    private void printData(int[] nums) {
        //Arrays.sort(nums);
        Iterator iterator = integerList.listIterator();
        for (int temp : nums) {
            while (iterator.hasNext()) {
                //删除已经存在的元素
                if (temp == (Integer) iterator.next()) {
                    iterator.remove();
                }
            }
        }
    }

    public static void main(String[] args) {
        //0,1,2,3,4,5,6,7,9
        int[] arrays = {0, 1, 2, 3, 4, 5, 6, 7, 9};
        // int[] arrays = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        //3,0,1
        MissingNumber missingNumber = new MissingNumber();
        int num = missingNumber.missingNumber(arrays);
        //8
        System.out.println(num);
    }
}
