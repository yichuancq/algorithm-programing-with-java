package com.example.leetcode.uniquepaths;

/**
 * 一个机器人位于一个 m x n网格的左上角,起始点在下图中标记为 “Start”
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角,在下图中标记为 “Finish”
 * 问总共有多少条不同的路径？
 */
public class Solution {

    int uniquePaths(int m, int n) {
        return dfs(1, 1, m, n);
    }

    /**
     * 递归遍历
     *
     * @param i
     * @param j
     * @param m
     * @param n
     * @return
     */
    int dfs(int i, int j, int m, int n) {
        if (i > m || j > n) return 0; // 越界了
        if (i == m && j == n) return 1; // 找到一种方法，相当于找到了叶子节点
        return dfs(i + 1, j, m, n) + dfs(i, j + 1, m, n);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int m = 3, n = 7;
        //28
        int ways = solution.uniquePaths(m, n);
        System.out.println(ways);
    }

}
