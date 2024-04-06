package com.example.algorithm.bridge;

import java.util.List;

/**
 * @author yichuan
 * @version 1.0
 * @description: 节点
 * @date 2023/6/24 23:17
 */
class Node {
    String name;
    List<Edge> edges;

    public Node(String name) {
        this.name = name;
    }
}