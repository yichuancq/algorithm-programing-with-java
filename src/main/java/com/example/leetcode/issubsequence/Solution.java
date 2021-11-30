package com.example.leetcode.issubsequence;


/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * <p>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串
 * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）
 * <p>
 * 示例 1：
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 */
public class Solution {

    /**
     * /**
     * 动态规划：判断是否子串
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int slen = s.length();
        int tlen = t.length();
        int[][] dp = new int[slen + 1][tlen + 1];
        for (int i = 1; i <= slen; i++) {
            for (int j = 1; j <= tlen; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        if (dp[slen][tlen] == slen) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        //给定字符串s和t ，判断s是否为t的子序列
        Solution solution = new Solution();
        String s = "abc";
        String t = "ahbgdc";
        boolean flag = solution.isSubsequence(s, t);
        System.out.println("是否子串:" + flag);
    }
}
