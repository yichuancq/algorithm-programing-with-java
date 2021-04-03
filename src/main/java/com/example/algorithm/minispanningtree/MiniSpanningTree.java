package com.example.algorithm.minispanningtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 最小生成树
 */
public class MiniSpanningTree {
    /**
     * 构建树
     *
     * @param edges
     * @return
     */
    public List<Edge> miniSpanningTree(List<Edge> edges) {
        ArrayList<Edge> result = new ArrayList<>();
        Collections.sort(edges); // 根据边权重从小到大排序
        for (Edge edge : edges) {
            Vertex u = edge.getStart();
            Vertex v = edge.getEnd();
            // 如果 u 和 v 已经在同一颗树里则跳过
            if (u.getRoot() == v.getRoot()) {
                continue;
            }
            result.add(edge);
            // 将 u 和 v 放在同一颗树里
            // 合并两个树最直接的办法就是使用一个新的根结点，然后连接两个子树
            TreeNode newRoot = new TreeNode();
            u.setRoot(newRoot);
            v.setRoot(newRoot);
        }
        return result;
    }

    /**
     * 获取数据
     *
     * @return
     */
    public List<Edge> getTestData() {
        ArrayList<Edge> list = new ArrayList<>();
        Vertex[] vertexes = new Vertex[9];
        for (int i = 0; i < vertexes.length; i++) {
            // 'a' to 'i'
            vertexes[i] = new Vertex((char) (i + 97));
        }
        list.add(new Edge(vertexes[0], vertexes[1], 4)); // a-b
        list.add(new Edge(vertexes[0], vertexes[7], 8)); // a-h
        list.add(new Edge(vertexes[1], vertexes[2], 8)); // b-c
        list.add(new Edge(vertexes[1], vertexes[7], 11)); // b-h
        list.add(new Edge(vertexes[2], vertexes[3], 7)); // c-d
        list.add(new Edge(vertexes[2], vertexes[5], 4)); // c-f
        list.add(new Edge(vertexes[2], vertexes[8], 2)); // c-i
        list.add(new Edge(vertexes[3], vertexes[4], 9)); // d-e
        list.add(new Edge(vertexes[3], vertexes[5], 14)); // d-f
        list.add(new Edge(vertexes[4], vertexes[5], 10)); // e-f
        list.add(new Edge(vertexes[5], vertexes[6], 2)); // f-g
        list.add(new Edge(vertexes[6], vertexes[7], 1)); // g-h
        list.add(new Edge(vertexes[6], vertexes[8], 6)); // g-i
        list.add(new Edge(vertexes[7], vertexes[8], 7)); // h-i
        return list;
    }

    /**
     * 打印结点
     *
     * @param edges
     */
    public void printEdges(List<Edge> edges) {
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            System.out.println("(" + edge.getStart().getValue() + ", " + edge.getEnd().getValue() + ")");
        }
    }

}
