package com.example.leetcode.shortestworddistance;

import java.util.ArrayList;
import java.util.List;

/***
 * 题目保证了给定的字符串数组肯定包含需要查找最短距离的两个单词。
 *
 *当 word1 == word2 时：
 * 1、遍历 words，找出 word == word1 的所有索引，保存到 List 中；
 * 2、这些索引是按照从小到大排列的，从第 1 个开始，遍历 List(i) - List(i-1) 的所有值，找出最小值。
 * 当 word1 != word2 时：
 * 1、定义两个指针 idx1 和 idx2 ，分别表示 word1 和 word2 的索引；
 * 2、遍历 words，如果 word == word1，则更新 idx1；如果 word == word2，则更新 idx2；
 * 3、记录 idx1 - idx2 的绝对值最小的那个；
 * 因为遍历是从左到右，所以一定涵盖最小值。
 */
public class ShortestWordDistance {

    /**
     * 最短单词距离
     *
     * @param words
     * @param word1
     * @param word2
     * @return
     */
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int min = Integer.MAX_VALUE;
        if (word1.equals(word2)) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                if (word1.equals(words[i])) {
                    tmp.add(i);
                }
            }
            for (int i = 1; i < tmp.size(); i++) {
                min = Math.min(min, tmp.get(i) - tmp.get(i - 1));
            }
        } else {
            int idx1 = -1;   // word1 所在的索引
            int idx2 = -1;   // word2 所在的索引
            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(word1)) {
                    idx1 = i;
                }
                if (words[i].equals(word2)) {
                    idx2 = i;
                }
                if (idx1 != -1 && idx2 != -1) {
                    min = Math.min(min, Math.abs(idx1 - idx2));
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        ShortestWordDistance shortestWordDistance = new ShortestWordDistance();
        int minDistance = shortestWordDistance.shortestWordDistance(
                new String[]{"you", "make", "me", "happy", "happy"}, "you", "happy");
        System.out.println(minDistance);

    }
}
