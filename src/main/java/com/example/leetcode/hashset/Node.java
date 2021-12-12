package com.example.leetcode.hashset;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义节点
 */
public class Node {
    public int key;
    public List<Integer> value;
    public Node left;
    public Node right;
    public int height = 0;

    public Node(int K, int V) {
        key = K;
        value = new ArrayList<>();
        value.add(V);
    }

}