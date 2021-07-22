package com.example.algorithm.wordsfrequency;

import java.util.HashMap;

/**
 * 设计一个方法，找出任意指定单词在一本书中的出现频率。
 * 示例：
 * WordsFrequency wordsFrequency = new WordsFrequency({"i", "have", "an", "apple", "he", "have", "a", "pen"});
 * wordsFrequency.get("you"); //返回0，"you"没有出现过
 * wordsFrequency.get("have"); //返回2，"have"出现2次
 * wordsFrequency.get("an"); //返回1
 * wordsFrequency.get("apple"); //返回1
 * wordsFrequency.get("pen"); //返回1
 */
public class WordsFrequency {
    private HashMap hashMap = new HashMap();

    public WordsFrequency(String[] book) {
        this.buildWordsMap(book);
    }


    private void buildWordsMap(String[] book) {
        for (String str : book) {
            if (!hashMap.containsKey(str)) {
                hashMap.put(str, 1);
            } else {
                int times = (int) hashMap.get(str);
                times++;
                hashMap.put(str, times);
            }
        }
    }

    public int get(String word) {
        if (!hashMap.containsKey(word)) {
            return 0;
        } else {
            int times = (int) hashMap.get(word);
            return times;
        }
    }

    public static void main(String[] args) {
        String[] words = {"i", "have", "an", "apple", "he", "have", "a", "pen", "a", "a"};
        WordsFrequency wordsFrequency = new WordsFrequency(words);
        int times = wordsFrequency.get("you");
        System.out.println(String.format("出现的次数 %s", times));
        times = wordsFrequency.get("have");
        System.out.println(String.format("出现的次数 %s", times));
        times = wordsFrequency.get("pen");
        System.out.println(String.format("出现的次数 %s", times));

    }
}
