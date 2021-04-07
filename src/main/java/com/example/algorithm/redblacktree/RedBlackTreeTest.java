package com.example.algorithm.redblacktree;

public class RedBlackTreeTest {
    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();
        redBlackTree.insert(1);
        redBlackTree.insert(3);
        redBlackTree.insert(2);
        redBlackTree.insert(4);
        redBlackTree.insert(5);
        redBlackTree.insert(6);
        System.out.println("MAX:" + redBlackTree.findMax());
        System.out.println("MIN:" + redBlackTree.findMin());
        redBlackTree.preOrder(redBlackTree.root);
        redBlackTree.inOrder(redBlackTree.root);
        redBlackTree.postOrder(redBlackTree.root);
        redBlackTree.levelOrder(redBlackTree.root);
    }

}
