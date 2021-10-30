package com.example.leetcode.treecodec;

import com.example.leetcode.node.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 * 二叉树的序列化与反序列化
 */
public class Codec {
    private List<Integer> integerList = new ArrayList<>();

    /**
     * 编码
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : this.dfs(root)) {
            stringBuilder.append("," + (integer));
        }
        return stringBuilder.toString().replaceFirst(",", "");
    }

    /**
     * 解码
     *
     * @param data
     * @return
     */
    public TreeNode deserialize(String data) {
        String[] dString = data.split(",");
        Integer[] arrays = new Integer[dString.length];
        int i = 0;
        for (String string : dString) {
            arrays[i] = Integer.valueOf(string);
            i++;
        }
        return this.buildTree(arrays, 0);
    }

    /**
     * @param treeNode
     * @return
     */
    private List<Integer> dfs(TreeNode treeNode) {
        int val = treeNode.val;
        integerList.add(val);
        //层次遍历
        if (treeNode.getLeft() != null) {
            dfs(treeNode.getLeft());
        }
        if (treeNode.getRight() != null) {
            dfs(treeNode.getRight());
        }
        return integerList;
    }

    /**
     * 构建树
     *
     * @param arrays
     * @return
     */
    private TreeNode buildTree(Integer[] arrays, int n) {
        //定义节点
        TreeNode treeNode = null;
        if (arrays.length <= 0) {
            return null;
        }
        if (n < arrays.length) {
            if (arrays[n] != null) {
                int l = 2 * n + 1;
                int r = 2 * n + 2;
                int val = Integer.valueOf(arrays[n]);
                //递归调用
                treeNode = new TreeNode(val, buildTree(arrays, l), buildTree(arrays, r));
            }
        }
        return treeNode;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // todo  二叉树的序列化与反序列化
        Codec codec = new Codec();
        Integer[] array = {1, 2, 3, null, null, 4, 5};
        //1,2,null,null,3,4,null,null,5,null,null
        TreeNode root = codec.buildTree(array, 0);
        System.out.println(root);
        //加密
        String content = codec.serialize(root);
        System.out.println("content:" + content);
        //解码
        TreeNode treeNode = codec.deserialize(content);
        System.out.println(treeNode);
    }
}
