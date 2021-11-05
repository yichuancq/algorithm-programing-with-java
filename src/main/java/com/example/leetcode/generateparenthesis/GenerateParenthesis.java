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
public class GenerateParenthesis {

    List<String> res = new ArrayList<>();

    /**
     * 生成所有可能的并且有效的括号组合
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        dfs(n, n, "");
        return res;
    }

    /**
     * @param left
     * @param right
     * @param curStr
     */
    private void dfs(int left, int right, String curStr) {
        System.out.println(String.format("left:%d, right:%d ,curStr:%s", left, right, curStr));
        if (left == 0 && right == 0) { // 左右括号都不剩余了，递归终止
            res.add(curStr);
            return;
        }
        if (left > 0) { // 如果左括号还剩余的话，可以拼接左括号
            dfs(left - 1, right, curStr + "(");
        }
        if (right > left) { // 如果右括号剩余多于左括号剩余的话，可以拼接右括号
            dfs(left, right - 1, curStr + ")");
        }
    }

    public static void main(String[] args) {
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();
        int n = 4;
        List<String> stringList = generateParenthesis.generateParenthesis(n);
        System.out.println(stringList);
    }
}
