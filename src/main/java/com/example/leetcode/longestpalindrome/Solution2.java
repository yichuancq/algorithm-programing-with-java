package com.example.leetcode.longestpalindrome;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串s,请你将s分割成一些子串，使每个子串都是回文串.返回s所有可能的分割方案
 * 回文串是正着读和反着读都一样的字符串
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 */
class Solution2 {

    /**
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        dfs(s, 0, new ArrayList<String>(), list);
        return list;
    }

    /**
     * @param s
     * @param start
     * @param path
     * @param list
     */
    private void dfs(String s, int start, ArrayList<String> path, List<List<String>> list) {
        if (start == s.length()) {
            list.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String s1 = s.substring(start, i + 1);
            if (!isPalindrome(s1)) {
                continue;
            }
            path.add(s1);
            dfs(s, i + 1, path, list);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 回文判断方法
     *
     * @param s
     * @return
     */
    private boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;

    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        List<List<String>> lists = solution2.partition("aab");
        for (List<String> stringList : lists) {
            System.out.println(stringList);
        }
    }
}