package com.example.leetcode.node;

public class TreeNodeBuilder {

    public TreeNodeBuilder() {
    }

    /**
     * 构造结点方式1
     *
     * @param arrays
     * @return
     */
    public TreeNode buildTree1(int[] arrays, int i) {
        TreeNode treeNode = null;
        if (i < arrays.length) {
            int val = arrays[i];
            treeNode = new TreeNode(val);
            //打印结点
            //System.out.println("" + treeNode.toString());
            //设置左孩子
            treeNode.setLeft(buildTree1(arrays, 2 * i + 1));
            //设置右孩子
            treeNode.setRight(buildTree1(arrays, 2 * i + 2));
        }
        return treeNode;
    }

    /**
     * @param treeNode
     */
    public void preOrderTree(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.println(treeNode.val);
            preOrderTree(treeNode.left);
            preOrderTree(treeNode.right);
        }
    }

    /**
     * @param treeNode
     */
    public void inOrderTree(TreeNode treeNode) {
        if (treeNode != null) {
            inOrderTree(treeNode.left);
            System.out.println(treeNode.val);
            inOrderTree(treeNode.right);
        }
    }

    /**
     * 层次遍历构造
     * 构造结点方式2
     *
     * @param arrays
     * @param n
     * @return
     */
    public TreeNode buildTree(int[] arrays, int n) {
        TreeNode treeNode = null;
        if (arrays.length == 0) {
            return null;
        }
        if (n < arrays.length) {
            int l = n * 2 + 1;
            int r = n * 2 + 2;
            treeNode = new TreeNode(arrays[n],
                    buildTree(arrays, l),
                    buildTree(arrays, r));
        }
        return treeNode;
    }
}
