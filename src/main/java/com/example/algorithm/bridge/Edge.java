package com.example.algorithm.bridge;

/**
 * @author yichuan
 * @version 1.0
 * @description: 边
 * @date 2023/6/24 23:18
 */
class Edge {
    private Node from;
    private Node to;
    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
    }
}