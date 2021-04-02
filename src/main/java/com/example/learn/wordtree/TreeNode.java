package com.example.learn.wordtree;

import java.util.List;

/**
 * 单词结点
 */
public class TreeNode extends Object {
    /**
     * 值
     */
    public String val;
    /**
     * 父节点
     */
    public TreeNode parent;
    /**
     * 下一个结点（目前设置为26个）
     */
    public List<TreeNode> next;

    public TreeNode() {
    }

    public TreeNode(String val) {
        this.val = val;
    }
    public TreeNode(String val, List<TreeNode> next) {
        this.val = val;
        this.next = next;
    }
    public TreeNode(String val, List<TreeNode> next, TreeNode parent) {
        this.val = val;
        this.next = next;
        this.parent = parent;
    }
    @Override
    public String toString() {
        return "TreeNode{" +
                "val='" + val + '\'' +
                ", parent=" + parent +
                ", next=" + next +
                '}';
    }
    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public List<TreeNode> getNext() {
        return next;
    }

    public void setNext(List<TreeNode> next) {
        this.next = next;
    }
}
