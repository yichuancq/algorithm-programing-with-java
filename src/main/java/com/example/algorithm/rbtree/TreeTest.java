package com.example.algorithm.rbtree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class TreeTest<T> {

    /**
     *
     */
    public final static int MAX = 10000;


    public int dfsCheckRBT(RBTree<T> tree, TreeNode<T> now) {
        //property 1(Every node is either red or black.) must be true
        if (now.p == null || now.lchild == null || now.rchild == null) return -1;
        if (now == tree.nil) return 1;
        if (now != tree.getRoot()) {//check the link between the node and his parent
            if (now.p != tree.nil && now.p.lchild != now && now.p.rchild != now) {
                System.out.println("Link error between " + now.key + " and its parent.");
                return -1;
            } else if (now.p != tree.nil && now.p.lchild == now && now.p.rchild == now) {
                System.out.println("Link error between " + now.key + " and its parent.");
                return -1;
            }
        }
        if ((!now.black) && (!now.p.black)) {
            //property 4(If a node is red, then both its children are black.)
            System.out.println("A node and his parent are both RED");
            tree.inorderTreeWalk();
            return -2;
        }
        //property 5 is checked below
        //(For each node, all paths from the node to descendant leaves contain the same number of black nodes)
        int l = -1, r = -1;
        l = dfsCheckRBT(tree, now.lchild);
        r = dfsCheckRBT(tree, now.rchild);
        if (l < 0 || r < 0) return -2;
        if (l != r) {
            System.out.println("Two sub trees have different black height in : " + now.key);
            System.out.println("	They are : " + l + " and " + r);
            System.out.println("	Now the root is : " + tree.getRoot().key);
            tree.inorderTreeWalk();
            return -2;
        }
        if (now.black) return l + 1;
        else return l;
    }

    public boolean TreeTest(RBTree<T> tree) {
        //property 2(The root is black.) is checked below
        if (!tree.getRoot().black || tree.getRoot().p != tree.nil || !tree.nil.black) return false;
        if (dfsCheckRBT(tree, tree.getRoot()) > 0) return true;
        return false;
    }


    public static void main(String[] args) {
        int[] array = new int[MAX];
        for (int i = 0; i < MAX; ++i) {
            array[i] = (int) (Math.random() * MAX * 100);
        }
        System.out.println("array creating finish.");
        TreeTest<Integer> test = new TreeTest<Integer>();
        try {
            //time cost of my Red-Black Tree
            long begin, create, search, delete;
            begin = System.currentTimeMillis();
            RBTree<Integer> tree = new RBTree<Integer>();
            for (int i = 0; i < MAX; ++i)
                tree.insert(array[i]);
            create = System.currentTimeMillis();
            System.out.println("test tree : " + test.TreeTest(tree));
            create = System.currentTimeMillis();
            int count = 0;
            for (int i = 0; i < MAX; ++i)
                if (tree.search(array[i])) ++count;
            System.out.println("search ok : " + (count == MAX));
            search = System.currentTimeMillis();
            for (int i = 0; i < MAX; ++i) {
                tree.delete(tree.getRoot().key);
            }
            delete = System.currentTimeMillis();
            System.out.println("My Red-Black Tree test result :");
            System.out.printf("create: %.3fs, search:%.3fs, delete:%.3fs\n", 1.0 * (create - begin) / 1000, 1.0 * (search - create) / 1000, 1.0 * (delete - search) / 1000);

            //time cost of my HashSet
            begin = System.currentTimeMillis();
            Set<Integer> hs = new HashSet<Integer>();
            for (int i = 0; i < MAX; ++i)
                hs.add(array[i]);
            create = System.currentTimeMillis();
            for (int i = 0; i < MAX; ++i)
                hs.contains(array[i]);
            search = System.currentTimeMillis();
            Iterator<Integer> it = hs.iterator();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
            delete = System.currentTimeMillis();

            System.out.println("HashSet test result :");
            System.out.printf("create:%.3fs, search:%.3fs, delete:%.3fs\n", 1.0 * (create - begin) / 1000, 1.0 * (search - create) / 1000, 1.0 * (delete - search) / 1000);

            //time cost of my TreeSet
            begin = System.currentTimeMillis();
            Set<Integer> ts = new TreeSet<Integer>();
            for (int i = 0; i < MAX; ++i)
                ts.add(array[i]);
            create = System.currentTimeMillis();
            for (int i = 0; i < MAX; ++i)
                ts.contains(array[i]);
            search = System.currentTimeMillis();
            it = ts.iterator();
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
            delete = System.currentTimeMillis();
            System.out.println("TreeSet test result :");
            System.out.printf("create:%.3fs, search:%.3fs, delete:%.3fs\n", 1.0 * (create - begin) / 1000, 1.0 * (search - create) / 1000, 1.0 * (delete - search) / 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
