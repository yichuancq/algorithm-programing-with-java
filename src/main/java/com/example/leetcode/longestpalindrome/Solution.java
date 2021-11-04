package com.example.leetcode.longestpalindrome;

/**
 * 整体思路
 * 根据回文串的一个核心思想来解题：从中心点往两边扩散，来寻找回文串，这种方向相当于穷举每一个点为中心点
 * 如果回文串是奇数时，比如 "bab" ，它的中心点就只有一个 "a"，所以从 "a" 开始向两边扩散
 * 如果回文串是偶数时，比如 "baab"，它的中心点有两个 "aa"，所以从 "aa" 开始向两边扩散
 * 编写一个辅助函数来寻找回文串，当中心点确定了之后调用辅助函数，直接返回找到的回文串
 */
public class Solution {
    // 主函数
    public String longestPalindrome(String s) {
        // 记录最长回文串
        String res = "";
        // 穷举以所有点（奇数一个点，偶数两个点）为中心的回文串
        for (int i = 0; i < s.length(); i++) {
            // 当回文串是奇数时，由一个中心点向两边扩散
            String s1 = palindrome(s, i, i);
            // 当回文串是偶数时，由中间的两个中心点向两边扩散
            String s2 = palindrome(s, i, i + 1);
            // 三元运算符：判断为真时取冒号前面的值，为假时取冒号后面的值
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }

        return res;
    }

    // 辅助函数：寻找回文串
    private String palindrome(String s, int left, int right) {
        // 在区间 [0, s.length() - 1] 中寻找回文串，防止下标越界
        while (left >= 0 && right < s.length()) {
            // 是回文串时，继续向两边扩散
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }

        // 循环结束时的条件是 s.charAt(left) != s.charAt(right), 所以正确的区间为 [left + 1, right), 方法 substring(start, end) 区间是 [start, end), 不包含 end
        return s.substring(left + 1, right);
    }

    public static void main(String[] args) {
        Solution palindrome = new Solution();
        String content = "aacabdkacaa";
        String result = palindrome.longestPalindrome(content);
        System.out.println(String.format("最长回文子串:%s ", result));
    }
}
