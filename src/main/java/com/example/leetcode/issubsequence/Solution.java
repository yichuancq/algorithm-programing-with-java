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
     * 动态规划：判断是否子串
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();
        boolean[] dp = new boolean[m];
        boolean flag = false;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //条件
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i - 1] = true;
                    flag = dp[i - 1];
                }
            }
            //通项公式
            flag = flag && dp[i - 1];
            if (!flag) {
                return false;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        //给定字符串s和t ，判断s是否为t的子序列
        Solution solution = new Solution();
        String s = "com";
        String t = "computer";
        boolean flag = solution.isSubsequence(s, t);
        System.out.println("是否子串:" + flag);
    }
}
