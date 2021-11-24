package com.example.leetcode.finddifference;

import java.util.Arrays;

/**
 * * 给定两个字符串 s 和 t，它们只包含小写字母。
 * * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * * 请找出在 t 中被添加的字母。
 */
public class Solution1 {


    /**
     * 方法1
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference2(String s, String t) {
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        int len = arr1.length;
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        //排序后比较两个字符串相同索引是否匹配
        for (int i = 0; i < len; i++) {
            if (arr2[i] != arr1[i]) {
                return arr2[i];
            }
        }
        return arr2[len];
    }

    /**
     * 方法2
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference4(String s, String t) {
        int n = s.length();
        int sum1 = 0;
        for (int i = 0; i <= n; i++) {
            sum1 += t.charAt(i);
        }
        for (int i = 0; i < n; i++) {
            sum1 -= s.charAt(i);
        }
        return (char) sum1;
    }

    /**
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        char reslut = t.charAt(t.length() - 1);
        if (s.length() == 0) {
            return reslut;
        }
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            reslut ^= chars[i];
            reslut ^= chart[i];
        }
        return reslut;
    }

    public char findTheDifference3(String s, String t) {
        int[] a = new int[26];
        int[] b = new int[26];
        for (int i = 0; i < s.length(); i++) {
            a[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            a[t.charAt(i) - 'a']--;
            if (a[t.charAt(i) - 'a'] < 0) return t.charAt(i);
        }
        return 'a';
    }

    /**
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference5(String s, String t) {
        int c = 0;
        for (int i = 0; i < s.length(); i++) {
            c ^= s.charAt(i);
            c ^= t.charAt(i);
        }
        c ^= t.charAt(t.length() - 1);
        return (char) c;
    }

    public static void main(String[] args) {
        String s = "abcd", t = "abcde";
        Solution1 solution = new Solution1();
        char a = solution.findTheDifference5(s, t);
        System.out.println(a);
    }
}
