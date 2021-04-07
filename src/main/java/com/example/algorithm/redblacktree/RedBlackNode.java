package com.example.algorithm.redblacktree;

/**
 * 树结点
 */
class RedBlackNode {

    int value;
    Color color;
    RedBlackNode left;
    RedBlackNode right;
    RedBlackNode parent;

    public RedBlackNode(int value, RedBlackNode left, RedBlackNode right,
                        Color color) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.color = color;
    }

    public RedBlackNode(int value, Color color) {
        this.value = value;
        this.color = color;
    }

    /**
     * 用来构造NIL节点
     */
    public RedBlackNode(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "RedBlackNode{" +
                "value=" + value +
                ", color=" + color +
                '}';
    }
}