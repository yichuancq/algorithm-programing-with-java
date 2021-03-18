package com.example.learn.floyd;

import java.util.ArrayList;
import java.util.List;

/**
 * Floyd算法
 */
public class FloydInGraph {
    private static int INF = Integer.MAX_VALUE;
    private int[][] dist;
    //顶点i 到 j的最短路径长度，初值是i到j的边的权重
    private int[][] path;
    private List<Integer> result = new ArrayList<Integer>();


    public void findCheapestPath(int begin, int end, int[][] matrix) {
        floyd(matrix);
        result.add(begin);
        findPath(begin, end);
        result.add(end);
    }

    /**
     * 寻求路径
     *
     * @param i
     * @param j
     */
    public void findPath(int i, int j) {
        // 找到路由节点
        int k = path[i][j];
        if (k == -1)
            return;
        // 从i到路由节点进行递归寻找中间节点
        findPath(i, k);
        result.add(k);
        // 从j到路由节点进行递归寻找中间节点
        findPath(k, j);
    }

    public void floyd(int[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                path[i][j] = -1;
                dist[i][j] = matrix[i][j];
            }
        }
        //这里是弗洛伊德算法的核心部分
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        // 更新i和j两点间的距离
                        dist[i][j] = dist[i][k] + dist[k][j];
                        // 更新i和j两点间的路由信息
                        path[i][j] = k;
                    }
                }
            }
        }
    }

    public FloydInGraph(int size) {
        this.path = new int[size][size];
        this.dist = new int[size][size];
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        FloydInGraph graph = new FloydInGraph(5);
        int[][] matrix = {
                {INF, 30, INF, 10, 50},
                {INF, INF, 60, INF, INF},
                {INF, INF, INF, INF, INF},
                {INF, INF, INF, INF, 30},
                {50, INF, 40, INF, INF},
        };
        int begin = 0;
        int end = 4;
        graph.findCheapestPath(begin, end, matrix);
        List<Integer> list = graph.result;
        System.out.println(begin + " to " + end + ",the cheapest path is:");
        System.out.println(list.toString());
        System.out.println(graph.dist[begin][end]);
    }

}
