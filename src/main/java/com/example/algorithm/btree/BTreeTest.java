package com.example.algorithm.btree;

/**
 * @author yichuan
 */
public class BTreeTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        BTree<Integer, String> tree = new BTree<>();
        for (int i = 0; i < 100; i++) {
            tree.put(i, "" + i);
        }
        tree.remove(21);
        tree.remove(21);
        System.out.println(tree.get(50));
        System.out.println(tree.get(100));
        System.out.println(tree.degree);
        System.out.println(tree.maxKeys);
        System.out.println(tree.toString());
        //打印结点
        tree.printTree(tree.root);
    }

}
