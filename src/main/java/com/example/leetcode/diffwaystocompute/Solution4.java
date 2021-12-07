package com.example.leetcode.diffwaystocompute;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solution4 {

    //记忆录 优化以下现象：(1 + 1) + (1 + 1 + 1)、(1 + 1 + 1) + (1 + 1)
    HashMap<String, List<Integer>> memory = new HashMap<>();

    //函数作用：传递字符串表达式计算出结果
    public List<Integer> diffWaysToCompute(String expression) {
        if (memory.containsKey(expression)) return memory.get(expression);//优化

        List<Integer> res = new LinkedList<>();
        //遍历表达式
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            /****** 分 ******/
            //以运算符为中心，分割成两个字符串，分别递归计算
            if (c == '-' || c == '*' || c == '+') {
                //明确递归函数的定义是什么，相信并且利用好函数的定义
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1));
                /****** 治 ******/
                // 通过子问题的结果，合成原问题的结果
                for (int a : left) {
                    for (int b : right) {
                        if (c == '-') res.add(a - b);
                        if (c == '+') res.add(a + b);
                        if (c == '*') res.add(a * b);
                    }
                }
            }
        }
        // base case 递归的结束条件
        //当表达式一直分到只有一个数字的时候，结束递归，
        //如果 res 为空，说明算式是一个数字，没有运算符
        if (res.size() == 0) res.add(Integer.valueOf(expression));
        //System.out.println(Arrays.toString(res.toArray()));
        memory.put(expression, res);
        return res;
    }

    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        String expression = "2*3-4+8*4";
        List<Integer> list = solution.diffWaysToCompute(expression);
        System.out.println(list);

    }

}
