package com.example.leetcode.finddifference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 * 示例 1：
 * <p>
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 * <p>
 * 示例 2：
 * 输入：s = "", t = "y"
 * 输出："y"
 * <p>
 * 示例 3：
 * 输入：s = "a", t = "aa"
 * 输出："a"
 * <p>
 * 示例 4：
 * 输入：s = "ae", t = "aea"
 * 输出："a"
 */

public class Solution {

    /**
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        char nums1[] = s.toCharArray();
        char nums2[] = t.toCharArray();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        return calc(nums1, nums2);
    }

    /**
     * @param nums1
     * @param nums2
     * @return
     */
    private char calc(char nums1[], char nums2[]) {
        List<Character> list = new ArrayList<>();
        List<Character> characters = new ArrayList<>();
        List<Character> list2 = new ArrayList<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (characters.size() > 0) {
                break;
            }
            if (nums1[i] < nums2[j]) {
                characters.add(nums1[i]);
                i++;
            } else if (nums1[i] > nums2[j]) {
                characters.add(nums2[j]);
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        if (characters.size() > 0) {
            return characters.get(0);
        } else {
            for (Character character : nums2) {
                list2.add(character);
            }
            list2 = list2.subList(list.size(), list.size() + 1);
            return list2.get(0);
        }
    }


    public static void main(String[] args) {
        String s = "abcd", t = "abcde";
        Solution solution = new Solution();
        char a = solution.findTheDifference(s, t);
        System.out.println(a);
    }
}
