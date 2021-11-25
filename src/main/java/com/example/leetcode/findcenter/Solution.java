package com.example.leetcode.findcenter;

import java.util.Arrays;

/***
 * 有一个无向的 星型 图，由n个编号从1到n的节点组成。星型图有一个中心节点，并且恰有n-1条边将中心节点与其他每个节点连接起来。
 * 给你一个二维整数数组edges,其中edges[i] = [ui, vi]表示在节点ui和vi之间存在一条边。
 * 请你找出并返回edges所表示星型图的中心节点
 */
public class Solution {

    /**
     * @param edges
     * @return
     */
    public int findCenter(int[][] edges) {
        int n = edges.length + 1;
        int[] deg = new int[n + 1];
        Arrays.fill(deg, 0);
        for (int[] edge : edges) {
            deg[edge[0]]++;
            deg[edge[1]]++;
        }
        for (int i = 1; i <= n; i++) {
            if (deg[i] == n - 1) {
                return i;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        int edges[][] = {{1, 2}, {2, 3}, {4, 2}};
        Solution solution = new Solution();
        int n = solution.findCenter(edges);
        System.out.println(n);
    }
}
