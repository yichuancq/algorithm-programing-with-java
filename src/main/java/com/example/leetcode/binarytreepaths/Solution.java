package com.example.leetcode.binarytreepaths;

import com.example.leetcode.node.TreeNode;
import com.example.leetcode.node.TreeNodeBuilder;

import java.util.*;

/**
 * 二叉树所有路径
 *
 * @author yichuan
 */
public class Solution {
    /**
     * 存放所有路径
     */
    List<List<Integer>> res = new ArrayList<>();
    /**
     * 存放当前遍历到了的节点
     */
    List<Integer> curList = new ArrayList<>();

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
        curList.add(root.val);
        if (root.left == null && root.right == null) {
            List<Integer> cur0 = new ArrayList<>(curList);
            res.add(cur0);
        }
        if (root.left != null) {
            dfs(root.left);
        }
        if (root.right != null) {
            dfs(root.right);
        }
        //做剪枝，删除用掉了的叶子节点
        curList.remove(curList.size() - 1);

    }


    /**
     * 广度优先遍历
     *
     * @param root
     */
    public void printBinaryTreeWidthFirst(TreeNode root) {
        Queue queue = new LinkedList();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            root = (TreeNode) queue.poll();
            System.out.print(root.val + " ");
            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }
        }
    }

    /**
     * 深度优先遍历
     *
     * @param root
     */
    public void printBinaryTreeDepthFirst(TreeNode root) {
        Stack stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = (TreeNode) stack.pop();
            System.out.print(root.val + " ");
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
    }

    /**
     * 中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root, List<Integer> inOrderList) {
        if (root != null) {
            inorderTraversal(root.left, inOrderList);
            //添加结点
            inOrderList.add(root.val);
            inorderTraversal(root.right, inOrderList);
        }
        return inOrderList;
    }

    /**
     * 先序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root, List<Integer> preList) {
        if (root != null) {
            preList.add(root.val);
            preorderTraversal(root.left, preList);
            preorderTraversal(root.right, preList);
        }
        return preList;
    }

    /**
     * 后序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root, List<Integer> postList) {
        if (root != null) {
            postorderTraversal(root.left, postList);
            postorderTraversal(root.right, postList);
            postList.add(root.val);
        }
        return postList;
    }

    /**
     * 计算深度
     *
     * @param root
     * @return
     */
    public int calculateDepthBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = calculateDepthBinaryTree(root.left);
        int right = calculateDepthBinaryTree(root.right);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNodeBuilder treeNodeBuilder = new TreeNodeBuilder();
        int[] arrays = {1, 2, 3, 4, 5};
        TreeNode treeNode = treeNodeBuilder.buildTree1(arrays, 0);
        System.out.println("" + treeNode.toString());
        List<String> stringList = solution.binaryTreePaths(treeNode);
        System.out.println(stringList);
        treeNodeBuilder.preOrderTree(treeNode);

        int depth = solution.calculateDepthBinaryTree(treeNode);
        System.out.println("depth:" + depth);
        System.out.println("深度优先遍历:");
        solution.printBinaryTreeDepthFirst(treeNode);
        System.out.println();
        System.out.println("广度优先遍历:");
        solution.printBinaryTreeWidthFirst(treeNode);
        System.out.println();
        System.out.println("先序");
        List<Integer> list = solution.preorderTraversal(treeNode, new ArrayList<>());
        System.out.println(list);
        System.out.println("中序");
        list = solution.inorderTraversal(treeNode, new ArrayList<>());
        System.out.println(list);
        System.out.println("后序");
        list = solution.postorderTraversal(treeNode, new ArrayList<>());
        System.out.println(list);
    }

}