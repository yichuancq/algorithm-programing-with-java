package com.example.leetcode.bstiterator;

import com.example.leetcode.node.TreeNode;

/**
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。
 * 指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * int next()将指针向右移动，然后返回指针处的数字。
 */
public class Solution {

    /**
     * 二叉搜索树迭代器
     */
    static class BSTIterator {
        int size = 0;
        private TreeNode head;


        public BSTIterator() {

        }

        /**
         * @return
         */


        /**
         * 下一个元素
         *
         * @return
         */

        public int moveIndex = 0;

        /***
         * 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false
         * @return
         */
        public boolean hasNext() {
            TreeNode p = this.head;
            if (p != null) {
                if (p.right != null) {
                    return true;
                }
            }
            return false;
        }

        /**
         * @return
         */

        public int next() {
            TreeNode p = this.head;
            //如果向指针右侧遍历存在数字
            if (p == null) {
                return 0;
            }
            if (this.hasNext()) {
                if (p.left != null) {
                    return p.left.val;
                } else if (p.left == null) {
                    return p.val;
                } else if (p.left == null && p.right != null) {
                    return p.right.val;
                }
            }
            return 0;
        }

        /**
         * @param treeNode
         * @return
         */
        int len(TreeNode treeNode) {
            TreeNode temp = treeNode;
            if (treeNode == null) {
                return 0;
            }
            if (temp != null && temp.val != 0) {
                size += 1;
                //访问顺序 ，左中右
                if (temp.left != null) {
                    len(temp.left);
                }
                System.out.println("val-->" + treeNode.val);
                if (temp.right != null) {
                    len(temp.right);
                }
            }
            return size;
        }

        /**
         * @param insertNode
         * @param i
         */
        void addNode(TreeNode insertNode, int i) {
            if (insertNode == null) {
                return;
            }
            if (i < insertNode.val && insertNode.left == null) {
                TreeNode treeNodeTemp = new TreeNode(i);
                insertNode.left = treeNodeTemp;
            } else if (i < insertNode.val && insertNode.left != null) {
                this.addNode(insertNode.left, i);
            } else if (i > insertNode.val && insertNode.right == null) {
                TreeNode treeNode = new TreeNode(i);
                insertNode.right = treeNode;
            } else if (i > insertNode.val && insertNode.right != null) {
                this.addNode(insertNode.right, i);
            }
        }

        public void addNode(int i) {
            if (this.head == null) {
                TreeNode temp = new TreeNode(i);
                this.head = temp;
            } else {
                this.addNode(this.head, i);
            }
        }

        /**
         * @param arrays
         */
        public void initTree(int[] arrays) {
            if (arrays == null || arrays.length == 0) {
                return;
            }
            for (int i = 0; i < arrays.length; i++) {
                this.addNode(arrays[i]);
            }
        }
    }

    public static void main(String[] args) {
        int[] arrays = {7, 3, 15, 0, 0, 9, 20};
        //TreeNode treeNode = treeNodeBuilder.buildTree1(arrays, 0);
        //init
        BSTIterator bstIterator = new BSTIterator();
        bstIterator.initTree(arrays);
        System.out.println(bstIterator.head);
        int size = bstIterator.len(bstIterator.head);
        System.out.println("tree nodes amount:" + size);
        int next = bstIterator.next();
        boolean flag = bstIterator.hasNext();
        System.out.println("has next:" + flag);
        System.out.println(next);
        next = bstIterator.next();
        System.out.println(next);

    }
}
