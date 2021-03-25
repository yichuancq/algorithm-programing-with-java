package com.example.learn.tree.binarytree;

/**
 * 三叉链表结点类
 * 二叉树的三叉链表结点类，T指定结点的元素类型
 */
public class TriNode<T> {
    /**
     * 数据域，存储数据元素
     */
    public T data;
    /**
     * 地址域，分别指向父母结点、左和右孩子结点
     */
    public TriNode<T> parent, left, right;

    /**
     * 构造结点，参数分别指定元素、父母结点、左和右孩子结点
     *
     * @param data
     * @param parent
     * @param left
     * @param right
     */
    public TriNode(T data, TriNode<T> parent, TriNode<T> left, TriNode<T> right) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    //构造指定值的叶子结点
    public TriNode(T data) {
        this(data, null, null, null);
    }

    public TriNode() {
        this(null, null, null, null);
    }

    public String toString() {
        return this.data.toString();
    }

    //判断是否叶子结点
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
}
