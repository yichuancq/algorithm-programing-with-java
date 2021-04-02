package com.example.leetcode.increasingbst;

import com.example.leetcode.node.TreeNode;
import com.example.leetcode.node.TreeNodeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个树，请你 按中序遍历 重新排列树，使树中最左边的结点现在是树的根，
 * 并且每个结点没有左子结点，只有一个右子结点
 * <p>
 * 输入：[5,3,6,2,4,null,8,1,null,null,null,7,9]
 * <p>
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 *
 * @author yichuan
 */
public class Solution {

    List<Integer> integerList = new ArrayList<>();

    /**
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        TreeNode last = null;
        if (root == null) {
            return null;
        } else {
            this.inOrderTree(root);
            last = this.builder(integerList, 0);
        }
        return last;
    }

    /**
     * 构建新的树
     *
     * @param integerList
     * @param n
     * @return
     */
    public TreeNode builder(List<Integer> integerList, int n) {
        if (integerList == null || integerList.size() == 0) {
            return null;
        }
        TreeNode treeNode = null;
        if (n < integerList.size()) {
            int rootVal = integerList.get(n);
            treeNode = new TreeNode(rootVal, null, builder(integerList, n + 1));
        }
        return treeNode;
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    public void inOrderTree(TreeNode root) {
        if (root != null) {
            inOrderTree(root.left);
            System.out.println(root.val);
            integerList.add(root.val);
            inOrderTree(root.right);
        }
    }

    public static void main(String[] args) {

        int[] arrays = {1, 2, 3, 4, 5};
        TreeNodeBuilder treeNodeBuilder = new TreeNodeBuilder();
        TreeNode treeNode = treeNodeBuilder.buildTree(arrays, 0);
//        System.out.println("中序遍历");
//        treeNodeBuilder.inOrderTree(treeNode);

        //increasingBSt
        Solution solution = new Solution();
        solution.increasingBST(treeNode);

    }
}
