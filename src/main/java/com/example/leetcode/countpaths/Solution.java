package com.example.leetcode.countpaths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 你在一个城市里，城市由 n个路口组成，路口编号为0到n-1，某些路口之间有 双向道路。输入保证你可以从任意路口出发到达其他任意路口，且任意两个路口之间最多有一条路。
 * 给你一个整数n和二维整数数组roads，其中roads[i] = [ui, vi, timei]表示在路口ui和vi之间有一条需要花费timei时间才能通过的道路。你想知道花费 最少时间从路口 0 出发到达路口 n - 1 的方案数。
 * 请返回花费最少时间到达目的地的 路径数目。由于答案可能很大，将结果对109 + 7取余后返回
 * <p>
 * 输入：n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
 * 输出：4
 * 解释：从路口 0 出发到路口 6 花费的最少时间是 7 分钟。
 * 四条花费 7 分钟的路径分别为：
 * - 0 ➝ 6
 * - 0 ➝ 4 ➝ 6
 * - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
 * - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
 */
public class Solution {
    List<Edge>[] graph;
    // INF 不能是经典的 0x3f3f3f3f，要变大！
    final long INF = (Long.MAX_VALUE >> 1) - (long) 1e5;
    final int MOD = (int) 1e9 + 7;

    private class Edge {
        int to;
        long len;

        Edge(int _to, long _len) {
            to = _to;
            len = _len;
        }
    }


    private int dijkstra(int ed) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Long.compare(a.len, b.len));
        boolean[] vis = new boolean[ed];
        long[] dist = new long[ed];
        int[] cnt = new int[ed];
        pq.offer(new Edge(0, 0));
        Arrays.fill(dist, INF);
        dist[0] = 0;
        cnt[0] = 1;

        while (!pq.isEmpty()) {
            int u = pq.poll().to;
            if (vis[u]) {
                continue;
            }
            vis[u] = true;
            graph[u].forEach(next -> {
                int v = next.to;
                long w = next.len;
                if (dist[v] > dist[u] + w) {
                    // 新的最短路继承前驱的最短路径条数
                    cnt[v] = cnt[u];
                    dist[v] = dist[u] + w;
                    pq.offer(new Edge(v, dist[v]));
                } else if (dist[v] == dist[u] + w) {
                    // 相同的最短路走法（指走到 v）则进行累加
                    cnt[v] = (cnt[v] + cnt[u]) % MOD;
                }
            });
        }

        return cnt[ed - 1];
    }

    /**
     * 路径计算
     *
     * @param n
     * @param roads
     * @return
     */
    public int countPaths(int n, int[][] roads) {
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        Arrays.stream(roads).forEach(p -> {
            int u = p[0], v = p[1], w = p[2];
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        });

        return dijkstra(n);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 7;
        int[][] roads = {{0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, {6, 3, 3}, {3, 5, 1}, {6, 5, 1}, {2, 5, 1}, {0, 4, 5}, {4, 6, 2}};
        int rs = solution.countPaths(n, roads);
        System.out.println(rs);
    }
}
