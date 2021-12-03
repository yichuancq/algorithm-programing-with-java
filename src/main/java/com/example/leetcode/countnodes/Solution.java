package com.example.leetcode.countnodes;

import com.example.leetcode.node.TreeNode;

/**
 * 完全二叉树的节点个数统计
 */
public class Solution {

    /**
     * 统计节点个数
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        int count = 0;
        if (root == null) {
            return 0;
        }
        count++;
        if (root.getLeft() != null) {
            count += countNodes(root.getLeft());
        }
        if (root.getRight() != null) {
            count += countNodes(root.getRight());
        }
        return count;
    }
    /**
     * @param arrays
     */
    private TreeNode builderTree(int[] arrays, int n) {
        TreeNode treeNode = null;
        if (arrays == null || arrays.length == 0) {
            return treeNode;
        }
        if (n < arrays.length) {
            System.out.println("" + arrays[n]);
            int val = arrays[n];
            int l = n * 2 + 1;
            int r = n * 2 + 2;
            treeNode = new TreeNode(val,
                    builderTree(arrays, l),
                    builderTree(arrays, r));
        }
        return treeNode;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = null;
        //
        int[] arrays = {1, 2, 3, 4, 5, 6};
        root = solution.builderTree(arrays, 0);
        int n = solution.countNodes(root);
        System.out.println("node size:" + n);
    }


}
