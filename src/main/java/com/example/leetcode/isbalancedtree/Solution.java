package com.example.leetcode.isbalancedtree;

import com.example.leetcode.node.TreeNode;
import com.example.leetcode.node.TreeNodeBuilder;

/**
 * https://leetcode-cn.com/problems/balanced-binary-tree/submissions/
 * 给定一个二叉树，判断它是否是高度平衡的二叉树
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1
 *
 * @author yichuan
 */
public class Solution {


    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    /**
     * 计算高度差
     * @param root
     * @return
     */
    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lh = height(root.left), rh = height(root.right);
        if (lh >= 0 && rh >= 0 && Math.abs(lh - rh) <= 1) {
            return Math.max(lh, rh) + 1;
        } else {
            return -1;
        }
    }


    public static void main(String[] args) {
        int[] arrays = {3, 9, 20, 0, 0, 15, 7};
        TreeNodeBuilder treeNodeBuilder = new TreeNodeBuilder();
        TreeNode root = treeNodeBuilder.buildTree(arrays, 0);
        System.out.println(root);
        Solution solution = new Solution();
        boolean flag = solution.isBalanced(root);
        System.out.println(flag);
        //
    }
}
