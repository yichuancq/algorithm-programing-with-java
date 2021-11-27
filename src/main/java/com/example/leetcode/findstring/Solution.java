package com.example.leetcode.findstring;

import java.util.Arrays;

/**
 * 稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置
 * <p>
 * 示例1:
 * <p>
 * 输入: words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ta"
 * 输出：-1
 * 说明: 不存在返回-1。
 * 示例2:
 * <p>
 * 输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ball"
 * 输出：4
 */
public class Solution {


    /**
     * @param words
     * @param s
     * @return
     */
    public int findString(String[] words, String s) {
        int pos = -1;
        if (words == null || words.length == 0 || s == null || s.length() == 0) {
            return pos;
        }
        System.out.println(Arrays.asList(words));
        return binSearch(words, s);
    }

    /**
     * @param words
     * @param s
     * @return
     */
    public int binSearch(String[] words, String s) {
        int begin = 0;
        int end = words.length - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (words[mid].equals("")) {
                if (words[end].compareTo(s) == 0) {
                    return end;
                } else {
                    end = end - 1;
                }
            } else if (words[mid].compareTo(s) < 0) {
                begin = mid + 1;
            } else if (words[mid].compareTo(s) > 0) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] words = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        //String[] words = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"};
        String target = "ball";
        Solution solution = new Solution();
        int pos = solution.findString(words, target);
        System.out.println(pos);
    }


}
