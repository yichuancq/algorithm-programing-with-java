package com.example.leetcode.listofdepth;

import com.example.leetcode.node.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * 输入：[1,2,3,4,5,null,7,8]
 * <p>
 * --------1
 * ======/  \
 * ======2--3
 * ====/ \    \
 * ===4==5=====7
 * ==/
 * =8
 * <p>
 * 输出：[[1],[2,3],[4,5,7],[8]]
 */

/**
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，
 * 则会创建出 D 个链表）。返回一个包含所有深度的链表的数组
 */
public class Solution {
    /**
     * 是否为叶子结点
     *
     * @param treeNode
     * @return
     */
    public boolean isLeaveChild(TreeNode treeNode) {
        if (treeNode != null && treeNode.val != -1) {
            if (treeNode.left == null && treeNode.right == null) {
                return true;
            }
            return false;
        }
        return false;
    }


    /**
     * 求树的结点数
     *
     * @param treeNode
     * @return
     */
    int n = 0;

    public int nodeCount(TreeNode treeNode) {
        if (treeNode != null && treeNode.val != -1) {
            n += 1;
            System.out.println("是否是叶子结点:" + isLeaveChild(treeNode));
            System.out.println("" + treeNode.toString());
            nodeCount(treeNode.left);
            nodeCount(treeNode.right);

        }
        return n;

    }

    /**
     * 层次遍历
     */
    public void levelOrder(TreeNode treeNode) {
        TreeNode p = treeNode;
        LinkedBlockingQueue<TreeNode> queue = new LinkedBlockingQueue();
        System.out.println("\r\n层次遍历");
        while (p != null) {
            // 访问P结点
            System.out.print("\t" + p.val + "");
            if (p.getLeft() != null) {
                queue.add(p.getLeft());
            }
            if (p.getRight() != null) {
                queue.add(p.getRight());
            }
            //p 指向出队结点
            p = queue.poll();
        }
        System.out.println(" ");
    }

    /**
     * @param value
     * @return
     */
    private TreeNode buildTree(int[] value) {
        TreeNode p = new TreeNode(value[0]);
        TreeNode q = p;
        Queue<TreeNode> queue = new LinkedList<>();
        int i = 0;
        while (p != null && p.val != -1) {
            if (2 * i + 1 < value.length) {
                p.left = new TreeNode(value[2 * i + 1]);
                queue.add(p.left);
            }
            if (2 * i + 2 < value.length) {
                p.right = new TreeNode(value[2 * i + 2]);
                queue.add(p.right);
            }
            p = queue.poll();
            i += 1;
        }
        return q;
    }

    /**
     * 层次遍历建立二叉树，这里使用的递归的方法
     * <p>
     * 利用二叉树结点位置的性质构造树
     *
     * @param arrays
     * @param n
     * @return
     */

    public TreeNode BuildTree(int[] arrays, int n) {
        TreeNode treeNode = null;
        if (arrays.length == 0) {
            return null;
        }
        if (n < arrays.length) {
            int l = n * 2 + 1;
            int r = n * 2 + 2;
            treeNode = new TreeNode(arrays[n],
                    BuildTree(arrays, l),
                    BuildTree(arrays, r));
        }
        return treeNode;
    }


    /**
     * 中序序遍历
     *
     * @param treeNode
     */
    public void inorder(TreeNode treeNode) {
        if (treeNode != null) {
            inorder(treeNode.left);
            System.out.println(treeNode.val);
            inorder(treeNode.right);
        }
    }

    /**
     * 中序遍历
     *
     * @param treeNode
     */
    public void postorder(TreeNode treeNode) {
        if (treeNode != null) {
            inorder(treeNode.left);
            inorder(treeNode.right);
            System.out.println(treeNode.val);
        }
    }

    //前序遍历
    public void preorder(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.println(treeNode.val);
            inorder(treeNode.left);
            inorder(treeNode.right);

        }
    }

    public static void main(String[] args) {
        int[] arrays = {1, 2, 3, 4, 5, -1, 7, 8};
        Solution solution = new Solution();
        //createTree
        TreeNode treeNode = solution.BuildTree(arrays, 0);
        System.out.println("" + treeNode);
        int n = solution.nodeCount(treeNode);
        System.out.println("count:" + n);

        solution.levelOrder(treeNode);
        System.out.println("中序遍历");
        solution.inorder(treeNode);
        System.out.println("前序遍历");
        solution.preorder(treeNode);
    }
}