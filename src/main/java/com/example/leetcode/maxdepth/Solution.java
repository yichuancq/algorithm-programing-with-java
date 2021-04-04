package com.example.leetcode.maxdepth;

import com.example.leetcode.node.TreeNode;
import com.example.leetcode.node.TreeNodeBuilder;

/**
 * 给定一个二叉树，找出其最大深度
 */
class Solution {


    /**
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明:叶子节点是指没有子节点的节点。
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * 返回它的最大深度 3
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        int max = 0;
        if (root == null) {
            return 0;
        } else {
            max += 1;
            //当左子树和右子树的末端结点null结束循环
            max += Math.max(maxDepth(root.left), maxDepth(root.right));
        }
        return max;
    }

    /**
     * 方法2
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth2(root.left);
        int right = maxDepth2(root.right);
        return Math.max(left, right) + 1;
    }


    public static void main(String[] args) {
        int[] arrays = {3, 9, 20, -0, -0, 15, 7};
        TreeNodeBuilder treeNodeBuilder = new TreeNodeBuilder();
        TreeNode root = treeNodeBuilder.buildTree(arrays, 0);
        System.out.println(root);
        //
        Solution solution = new Solution();
        int n = solution.maxDepth(root);
        System.out.println(n);
    }
}
