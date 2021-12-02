package com.example.leetcode.maxdepth;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int val;
    /**
     *
     */
    public List<Node> children = new ArrayList<>();

    public Node() {
    }

    public Node(int value) {
        this.val = value;
    }

    public Node(int value, List<Node> _children) {
        val = value;
        children = _children;
    }


    /**
     * 自定义添加节点的方法
     *
     * @param value
     * @param childList
     * @return
     */
    public Node builder(Integer value, List<Integer> childList) {
        if (value == null) {
            return null;
        }
        Node node = new Node(value);
        List<Node> children = new ArrayList<>();
        for (Integer integer : childList) {
            if (integer != null) {
                children.add(new Node(integer));
            }
        }
        node.setChildren(children);
        return node;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", children=" + children +
                '}';
    }
}
