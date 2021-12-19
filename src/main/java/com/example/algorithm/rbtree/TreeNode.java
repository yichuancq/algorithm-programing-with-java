package com.example.algorithm.rbtree;

/**
 * 红黑树节点
 *
 * @param <T>
 */
public class TreeNode<T> {
    public boolean black;
    public TreeNode<T> p;//parent reference
    public TreeNode<T> lchild;//left child reference
    public TreeNode<T> rchild;//right child reference
    public T key;

    public TreeNode() {
        black = false;
        p = this;
        lchild = this;
        rchild = this;
    }

    public TreeNode(T key) throws Exception {
        this();
        if (!(key instanceof Comparable)) {
            throw new Exception("key cann't be compared!");
        }
        this.key = key;
    }

    public char switchColor() {
        black = !black;
        return black ? 'b' : 'r';
    }
}
