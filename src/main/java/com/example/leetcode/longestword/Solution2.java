package com.example.leetcode.longestword;

import java.util.HashSet;
import java.util.Set;

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
public class Solution2 {
    /**
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        String ans = "";
        Set<String> wordset = new HashSet();
        for (String word : words) {
            wordset.add(word);
        }
        for (String word : words) {
            if (word.length() > ans.length() || word.length() == ans.length() && word.compareTo(ans) < 0) {
                boolean good = true;
                for (int k = 1; k < word.length(); ++k) {
                    if (!wordset.contains(word.substring(0, k))) {
                        good = false;
                        break;
                    }
                }
                if (good) ans = word;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String words[] = new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"};
        Solution2 solution = new Solution2();
        String result = solution.longestWord(words);
        //期望输出:"latte"
        System.out.println(result);
    }

}
