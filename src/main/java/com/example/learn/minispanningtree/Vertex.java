package com.example.learn.minispanningtree;

/***
 *
 */
public class Vertex extends TreeNode {

    private char value; // 顶点的值

    public Vertex(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public TreeNode getRoot() {
        TreeNode root = this;
        while (root.getParent() != null) {
            root = root.getParent();
        }
        return root;
    }

    public void setRoot(TreeNode treeNode) {
        getRoot().setParent(treeNode);
    }

}