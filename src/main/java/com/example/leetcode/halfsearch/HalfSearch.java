package com.example.leetcode.halfsearch;

public class HalfSearch {
    private boolean flag = false;

    /**
     * @param array
     * @param element
     * @return
     */
    private boolean search(int[] array, int element) {
        if (array.length == 1) {
            int temp = array[0];
            if (element == temp) {
                flag = true;
                System.out.println("search element:" + temp);
            }
        }
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
            flag = search(tempLeft, element);
            flag = search(tempRight, element);
        }
        return flag;
    }

    public static void main(String[] args) {
        int[] arrays = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        HalfSearch search = new HalfSearch();
        search.search(arrays, 4);

    }

}
