package com.example.leetcode.bstiterator;

import com.example.leetcode.node.TreeNode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 输入
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * 输出
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 * <p>
 * 解释
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // 返回 3
 * bSTIterator.next();    // 返回 7
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 9
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 15
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 20
 * bSTIterator.hasNext(); // 返回 False
 */
public class BSTIterator {
    private TreeNode root = null;
    private Queue queue = new PriorityQueue();

    public BSTIterator(TreeNode root) {
        this.root = root;
        //遍历
        this.traverse(root);
    }

    /**
     * 中序遍历
     */
    public void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        //left
        if (root.left != null) {
            traverse(root.left);
        }
        //middle
        System.out.println("" + root.val);
        //添加元素
        queue.offer(root.val);
        //right
        if (root.right != null) {
            traverse(root.right);
        }
    }

    /**
     * 将指针向右移动，然后返回指针处的数字
     *
     * @return
     */
    public int next() {
        if (queue.isEmpty()) {
            try {
                throw new Exception("没有下一个元素了!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //返回第一个元素
        int val = (Integer) queue.peek();
        //返回第一个元素，并在队列中删除
        queue.poll();
        return val;
    }

    /***
     * 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false
     * @return
     */
    public boolean hasNext() {
        if (queue.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 构造二叉树
     *
     * @param arrays
     * @return
     */
    public TreeNode buildTree(Object[] arrays, int n) {
        TreeNode treeNode = null;
        if (arrays.length == 0) {
            return null;
        }
        if (n < arrays.length) {
            if (arrays[n] != null) {
                int l = n * 2 + 1;
                int r = n * 2 + 2;
                treeNode = new TreeNode((Integer) arrays[n]);
                treeNode.left = buildTree(arrays, l);
                treeNode.right = buildTree(arrays, r);
            }
        }
        return treeNode;
    }

    public static void main(String[] args) {
        // 二叉搜索树迭代器
        Object[] arrays = {7, 3, 15, null, null, 9, 20};
        TreeNode root = null;
        BSTIterator bstIterator = new BSTIterator(root);
        root = bstIterator.buildTree(arrays, 0);
        System.out.println(String.format("tree:%s", root));
        bstIterator = new BSTIterator(root);
        System.out.println("has next:" + bstIterator.hasNext());
        System.out.println("next:" + bstIterator.next());
        System.out.println("next:" + bstIterator.next());
        System.out.println("next:" + bstIterator.next());
        System.out.println("next:" + bstIterator.next());
        System.out.println("has next:" + bstIterator.hasNext());
        System.out.println("has next:" + bstIterator.hasNext());
        System.out.println("has next:" + bstIterator.hasNext());
        System.out.println("next:" + bstIterator.next());
    }
}
