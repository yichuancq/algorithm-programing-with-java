package com.example.learn.wordtree;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 1、构建单词数组，按A-Z排序，构建树，实现查找和排序功能
 * 2、结果存入文件，读取文件实现存档
 * 3、思考其他实现和优化方案
 */
public class WordTree {

    private TreeNode head = new TreeNode("root");

    public WordTree() {
    }

    /**
     * 构建树
     *
     * @param arrays
     * @return
     */
    public TreeNode buildWordTree(String[] arrays, int n, int children, TreeNode parent) {
        TreeNode treeNode = null;
        if (arrays == null || arrays.length == 0) {
            return null;
        }
        if (n < arrays.length) {
            String word = arrays[n];
            if (!word.isEmpty()) {
                List<TreeNode> treeNodeList = new ArrayList<>();
                treeNode = new TreeNode(word, treeNodeList, parent);
                //下一个索引
                int index = children * n + 1;
                for (int i = 0; i < children; i++) {
                    //递归调用
                    TreeNode p = new TreeNode(treeNode.val);
                    TreeNode temp = this.buildWordTree(arrays, index, children, p);
                    if (temp != null && temp.val != null) {
                        treeNodeList.add(temp);
                    }
                    index++;
                }
                //按值排序
                treeNodeList.sort(Comparator.comparing(TreeNode::getVal));
            }
        }
        return treeNode;
    }

    /**
     * @param treeNode
     */
    private void printNode(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.println("val:" + treeNode.toString());
//            for (TreeNode node : treeNode.getChildren()) {
//                printNode(node);
//            }
        }
    }

    public static void main(String[] args) {
        String[] wordArrays = {"a", "b", "c", "d", "e"};
        WordTree wordTree = new WordTree();
        int size = 50;
        String[] strings = new String[size];
        for (int i = 0; i < size; i++) {
            strings[i] = "单词" + i;
        }
        //子节点最大个数
        int children = 26;
        TreeNode root = wordTree.buildWordTree(strings, 0, children, wordTree.head);
        System.out.println(JSON.toJSON(root));
//        wordTree.printNode(root);
//        wordTree.printNode(root);
    }

}
