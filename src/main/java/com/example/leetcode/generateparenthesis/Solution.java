package com.example.leetcode.generateparenthesis;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合
 * 括号组合需满足：左括号必须以正确的顺序闭合。
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 */
public class Solution {
    /**
     * 生成所有可能的并且有效的括号组合
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        generate(res, "", 0, 0, n);

        return res;
    }

    //count1统计“(”的个数，count2统计“)”的个数
    public void generate(List<String> res, String ans, int count1, int count2, int n) {
        if (count1 > n || count2 > n) {
            return;
        }
        if (count1 == n && count2 == n) {
            res.add(ans);
        }
        if (count1 >= count2) {
            String ans1 = new String(ans);
            generate(res, ans + "(", count1 + 1, count2, n);
            generate(res, ans1 + ")", count1, count2 + 1, n);

        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 4;
        List<String> stringList = solution.generateParenthesis(n);
        System.out.println(stringList);
    }


}
