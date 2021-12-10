package com.example.leetcode.wordbreak;

import java.util.Arrays;
import java.util.List;

/**
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典，判定s 是否可以由空格拆分为一个或多个在字典中出现的单词。
 * 说明：拆分时可以重复使用字典中的单词。
 * <p>
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * <p>
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {
        int[] bp = new int[wordDict.size()];
        for (int i = 0; i < wordDict.size(); i++) {

        }
        return false;
    }

    public static void main(String[] args) {

        // TODO: 2021/12/8 给你一个字符串 s 和一个字符串列表 wordDict 作为字典，
        //  判定s 是否可以由空格拆分为一个或多个在字典中出现的单词。
        Solution solution = new Solution();
        String s = "leetcode";
        String[] wordDict = {"leet", "code"};
        boolean flag = solution.wordBreak(s, Arrays.asList(wordDict));
        System.out.println(flag);

    }

}
