package com.example.leetcode.isanagram;

import java.util.Arrays;

/**
 * 给定两个字符串s和t ，编写一个函数来判断t是否是s的字母异位词。
 * 注意：若s和t中每个字符出现的次数都相同，则称s和t互为字母异位词。
 * <p>
 * 示例1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 */
public class IsAnagram {

    /**
     * 给定两个字符串s和t ，编写一个函数来判断t是否是s的字母异位词
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        char[] array1 = s.toCharArray();
        char[] array2 = t.toCharArray();
        Arrays.sort(array1);
        Arrays.sort(array2);
        if (new String(array1).contains(new String(array2))
                || new String(array2).contains(new String(array1))) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        String s = "anagram";
        String t = "nagaram";
        IsAnagram isAnagram = new IsAnagram();
        boolean flag = isAnagram.isAnagram(s, t);
        System.out.println(flag);
    }

}
