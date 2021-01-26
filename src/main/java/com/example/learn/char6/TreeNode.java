package com.example.learn.char6;

import java.util.List;

/**
 * 多叉树的结点
 */
public class TreeNode<T> {
    /**
     * 数据域
     */
    private T data;
    /**
     * 父节点
     */
    private TreeNode<T> parent;
    /**
     * 孩子结点
     */
    private List<TreeNode<T>> childList;


    public TreeNode(T data, TreeNode<T> parent, List<TreeNode<T>> childList) {
        this.data = data;
        this.parent = parent;
        this.childList = childList;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                '}';
    }



    public TreeNode() {
    }

    public T getData() {
        return data ;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public List<TreeNode<T>> getChildList() {
        return childList;
    }

    public void setChildList(List<TreeNode<T>> childList) {
        this.childList = childList;
    }
}
