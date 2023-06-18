package com.example.leetcode.canconstruct;

import java.util.*;

/***
 * 383. 赎金信
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 * 如果可以，返回 true ；否则返回 false 。magazine 中的每个字符只能在 ransomNote 中使用一次。
 示例 1：
 输入：ransomNote = "a", magazine = "b"
 输出：false

 示例 2：
 输入：ransomNote = "aa", magazine = "ab"
 输出：false

 示例 3：
 输入：ransomNote = "aa", magazine = "aab"
 输出：true

 */
public class Solution {

    public static void main(String[] args) {
        String ransomNote = "fffbfg";
        String magazine = "effjfggbffjdgbjjhhdegh";
//        String ransomNote = "aa";
//        String magazine = "ab";


        /**
         * 输入：
         * "aab"
         * "baa"
         * 输出：
         * false
         * 预期结果：
         * true
         */
        Solution solution = new Solution();
        boolean flag = solution.canConstruct(ransomNote, magazine);
        System.out.println(flag);
    }

    /**
     * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
     * 如果可以，返回 true ；否则返回 false 。magazine 中的每个字符只能在 ransomNote 中使用一次。
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.isEmpty() || magazine.isEmpty()) {
            return false;
        }
        if (magazine.contains(ransomNote)) {
            return true;
        }
        List<String> ransomNoteList = new ArrayList<>();
        List<String> magazineList = new ArrayList<>();
        for (int i = 0; i < ransomNote.length(); i++) {
            ransomNoteList.add(String.valueOf(ransomNote.charAt(i)));
        }
        for (int i = 0; i < magazine.length(); i++) {
            magazineList.add(String.valueOf(magazine.charAt(i)));
        }
//        Collections.sort(ransomNoteList);
//        Collections.sort(magazineList);
        Map<String, Integer> ransomNoteHashMap = new HashMap<>();
        Map<String, Integer> magazineHashMap = new HashMap<>();
        for (String string : ransomNoteList) {
            int number = 0;
            if (ransomNoteHashMap.containsKey(string)) {
                int oldNumber = ransomNoteHashMap.get(string);
                ransomNoteHashMap.remove(string);
                oldNumber += 1;
                ransomNoteHashMap.put(string, oldNumber);
            } else {
                ++number;
                ransomNoteHashMap.put(string, number);
            }
        }
        for (String string : magazineList) {
            int number = 0;
            if (magazineHashMap.containsKey(string)) {
                int oldNumber = magazineHashMap.get(string);
                magazineHashMap.remove(string);
                oldNumber += 1;
                magazineHashMap.put(string, oldNumber);
            } else {
                ++number;
                magazineHashMap.put(string, number);
            }
        }
        Set<Map.Entry<String, Integer>> entrySet = ransomNoteHashMap.entrySet();
        System.out.println("集合1：" + ransomNoteHashMap);
        System.out.println("集合2：" + magazineHashMap);
        //
        for (Map.Entry<String, Integer> entry : entrySet) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            boolean flag = magazine.contains(key);
            if (!flag) {
                return false;
            }
            int bigNumber = magazineHashMap.get(key);
            if (bigNumber >= value) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}