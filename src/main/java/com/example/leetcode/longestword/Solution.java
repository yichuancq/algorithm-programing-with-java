package com.example.leetcode.longestword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。
 * 若其中有多个可行的答案，则返回答案中字典序最小的单词。
 * <p>
 * 若无答案，则返回空字符串。
 * 示例：
 * 输入：
 * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出："apple"
 * 解释：
 * "apply"和"apple"都能由词典中的单词组成。但是"apple"的字典序小于"apply"。
 */
public class Solution {

    /**
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        String result = "";
        if (words == null || words.length == 0) {
            return result;
        }
        Stream<String> stream = Arrays.stream(words).sorted();
        List<String> wordsList = stream.collect(Collectors.toList());
        System.out.println(wordsList);
        //
        List<MyWord> myWordList = new ArrayList<>();
        for (int i = 0; i < wordsList.size(); i++) {
            MyWord myWord = new MyWord();
            myWord.index = i;
            myWord.wordsLen = wordsList.get(i).length();
            myWord.content = wordsList.get(i);
            myWordList.add(myWord);
        }
        for (MyWord myWord : myWordList) {
            System.out.println(myWord);
        }
        System.out.println("排序后");
//        Collections.sort(myWordList, Comparator.comparing(myWord -> myWord.wordsLen));
        List<MyWord> sort = myWordList.stream().sorted(Comparator.comparing(MyWord::getWordsLen)
                .thenComparing(MyWord::getIndex, Comparator.reverseOrder())).collect(Collectors.toList());
        for (MyWord myWord : sort) {
            System.out.println(myWord);
            result = myWord.getContent();
        }
        return result;
    }

    public static void main(String[] args) {
        String words[] = new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"};
        Solution solution = new Solution();
        String result = solution.longestWord(words);
        //期望输出:"latte"
        System.out.println(result);
    }
}
