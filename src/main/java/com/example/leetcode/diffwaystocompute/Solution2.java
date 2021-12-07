package com.example.leetcode.diffwaystocompute;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。
 * 有效的运算符号包含 +,-以及*。
 * <p>
 * 示例 1:
 * 输入: "2-1-1"
 * 输出: [0, 2]
 * 解释:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 */
public class Solution2 {

    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> list = new ArrayList<>();
        int len = expression.length();
        int start = 0;
        for (start = 0; start < len; start++) {
            if (Character.isDigit(expression.charAt(start))) continue;
            else break;
        }
        if (start == len) list.add(Integer.parseInt(expression));
        for (int i = start; i < len; i++) {
            if (Character.isDigit(expression.charAt(i))) continue;
            char op = expression.charAt(i);
            List<Integer> left = diffWaysToCompute(expression.substring(0, i));
            List<Integer> right = diffWaysToCompute(expression.substring(i + 1, len));
            System.out.println(left);
            System.out.println(right);
            for (int j : left) {
                for (int k : right) {
                    if (op == '+') list.add(j + k);
                    else if (op == '-') list.add(j - k);
                    else if (op == '*') list.add(j * k);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        //3个数，n=2.输出2种方案
        //4个数，n=3,输出5种方案
        //5个数，n=4,输出14种方案
        //6个数，n=5,输出132种方案
        String expression = "2*3-4*5";
        List<Integer> list = solution.diffWaysToCompute(expression);
        System.out.println(list);

    }
}
