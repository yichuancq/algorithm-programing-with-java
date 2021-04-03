package com.example.algorithm.wordtree;

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
     * 下一个结点集合（目前设置为26个）
     */
    public List<TreeNode> children;

    public TreeNode() {
    }

    public TreeNode(String val) {
        this.val = val;
    }

    public TreeNode(String val, List<TreeNode> children) {
        this.val = val;
        this.children = children;
    }

    public TreeNode(String val, List<TreeNode> children, TreeNode parent) {
        this.val = val;
        this.children = children;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val='" + val + '\'' +
                ", parent=" + parent +
                ", children=" + children +
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

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
