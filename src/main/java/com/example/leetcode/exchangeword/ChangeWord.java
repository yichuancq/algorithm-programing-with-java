package com.example.leetcode.exchangeword;

/**
 * 给你两个单词word1 和word2，请你计算出将word1转换成word2 所使用的最少操作数
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 示例1：
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * <p>
 * 解答：
 * 1、状态定义：
 * <p>
 * dp[i][j]表示word1的前i个字母转换成word2的前j个字母所使用的最少操作。
 * <p>
 * 2、状态转移：
 * i指向word1，j指向word2
 * 若当前字母相同，则dp[i][j] = dp[i - 1][j - 1];
 * 否则取增删替三个操作的最小值 + 1， 即:
 * dp[i][j] = min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1]) + 1。
 */
public class ChangeWord {

    /**
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        //word1 = "intention", word2 = "execution"
        String word1 = "intention", word2 = "execution";
        ChangeWord changeWord = new ChangeWord();
        int n = changeWord.minDistance(word1, word2);
        System.out.println(n);
    }
}
