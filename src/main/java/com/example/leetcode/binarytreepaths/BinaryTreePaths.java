package com.example.leetcode.binarytreepaths;

import com.example.leetcode.node.TreeNode;
import com.example.leetcode.node.TreeNodeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树所有路径
 *
 * @author yichuan
 */
public class BinaryTreePaths {

    /**
     * 存放所有路径
     */
    List<List<Integer>> res = new ArrayList<>();
    /**
     * 存放当前遍历到了的节点
     */
    List<Integer> cur = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        dfs(root);
        for (List<Integer> path : res) {
            StringBuilder sb = new StringBuilder();
            sb.append(path.get(0));
            for (int i = 1; i < path.size(); i++) {
                sb.append("->").append(path.get(i));
            }
            ans.add(sb.toString());
        }
        return ans;
    }

    /***
     *深度优先搜索
     * @param root
     */
    public void dfs(TreeNode root) {
        cur.add(root.val);
        if (root.left == null && root.right == null) {
            List<Integer> cur0 = new ArrayList<>(cur);
            res.add(cur0);
        }
        if (root.left != null) {
            dfs(root.left);
        }
        if (root.right != null) {
            dfs(root.right);
        }
        //做剪枝，删除用掉了的叶子节点
        cur.remove(cur.size() - 1);
    }

    public static void main(String[] args) {
        BinaryTreePaths binaryTreePaths = new BinaryTreePaths();
        TreeNodeBuilder treeNodeBuilder = new TreeNodeBuilder();
        int[] arrays = {1, 2, 3, 4, 5};
//      TreeNode treeNode = new TreeNodeBuilder().buildTree(arrays,0);
        TreeNode treeNode = treeNodeBuilder.buildTree1(arrays, 0);
        System.out.println("" + treeNode.toString());
        List<String> stringList = binaryTreePaths.binaryTreePaths(treeNode);
        System.out.println(stringList);
        treeNodeBuilder.preOrderTree(treeNode);
    }

}