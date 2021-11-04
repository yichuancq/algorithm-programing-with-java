package com.example.leetcode.longestpalindrome;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * 输入：s = "a"
 * 输出："a"
 * <p>
 * 输入：s = "ac"
 * 输出："a"
 */
public class LongestPalindrome {
    //字符串集合
    private List<String> arrayList = new ArrayList<>();

    /**
     * 字符串首尾元素对比法1
     *
     * @param string
     * @return
     */
    private boolean isPalindrome(String string) {
        if (string == null || string.equals("")) {
            return false;
        }
        int len = string.length();
        int time = len / 2;
        boolean flag = true;
        int step = 0;
        while (time > 0 && flag) {
            char begin = string.charAt(step);
            char end = string.charAt(string.length() - step - 1);
            // 如果第1个和倒数第1个相同，继续
            if (!(begin == end)) {
                flag = false;
                break;
            } else {
                flag = true;
            }
            step++;
            time--;
        }
        return flag;
    }

    /***
     * 字符串首尾元素对比法2
     * @param string
     * @return
     */
    private boolean isPalindrome1(String string) {
        if (string == null || string.equals("")) {
            return false;
        }
        int i;
        int j = string.length() - 1;
        String[] strings = string.split("");
        for (i = 0; i <= j; i++, j--) {
            if (!strings[i].equals(strings[j])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串倒置后逐一比较法
     *
     * @param string
     * @return
     */
    private boolean isPalindrome3(String string) {
        StringBuffer sb = new StringBuffer(string);
        sb.reverse();// 把字符串反转
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == sb.charAt(i)) {
                count++; //统计相同字符的个数
            }
        }
        if (count == string.length()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 最长回文子串
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        String str = "";
        if (s == null || s.length() == 0) {
            return str;
        }
        //babad
        String temp = s;
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            String words = temp.substring(i, s.length());
            subString(words);
        }
        Iterator<String> iterator = arrayList.listIterator();
        List<String> resultList = new ArrayList<>();
        while (iterator.hasNext()) {
            String stringNext = iterator.next();
            //是否回文判断
            boolean flag = isPalindrome(stringNext);
            //回文
            if (flag) {
                if (stringNext.length() > maxLen) {
                    maxLen = stringNext.length();
                }
                resultList.add(stringNext);
            }
        }
        //isLongestPalindrome
        for (String temps : resultList) {
            if (temps.length() == maxLen) {
                str = temps;
                return str;
            }
        }
        return str;
    }

    private void subString(String str) {
        String temp = "";
        for (int i = 0; i < str.length(); i++) {
            temp += str.charAt(i);
            //如果不重复
            if (!arrayList.contains(temp)) {
                arrayList.add(temp);
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        String content = "aacabdkacaa";
        String result = longestPalindrome.longestPalindrome(content);
        System.out.println(String.format("最长回文子串:%s ", result));
    }

}
