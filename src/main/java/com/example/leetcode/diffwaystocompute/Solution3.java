package com.example.leetcode.diffwaystocompute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分治法，碰到运算符号，递归求解前一半的值和后一半的值，然后根据运算符号，计算两者构成的值。
 */
public class Solution3 {

    public Map<String, List<Integer>> map = new HashMap<>();

    /**
     * 记录已经计算出来的字符串对应的值，避免重复计算。
     *
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input) {
        if (map.containsKey(input)) return map.get(input);
        List<Integer> list = new ArrayList<>();
        int len = input.length();
        for (int i = 0; i < len; i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {  // 出现运算符号，递归求解前半段和后半段。
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1, input.length()));
                // -1   =>  left:[[0]]  right:[[1]]
                for (int l : left) {
                    for (int r : right) {
                        switch (c) {
                            case '+':
                                list.add(l + r);
                                break;
                            case '-':
                                list.add(l - r);
                                break;
                            case '*':
                                list.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        if (list.size() == 0) list.add(Integer.valueOf(input));
        // 单独一个数字的情况 (可能出现多位数)
        map.put(input, list);
        return list;
    }

    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        //3个数，n=2.输出2种方案
        //4个数，n=3,输出5种方案
        //5个数，n=4,输出14种方案
        //6个数，n=5,输出132种方案
        String expression = "2*3-4*5";
        List<Integer> list = solution.diffWaysToCompute(expression);
        System.out.println(list);

    }
}
