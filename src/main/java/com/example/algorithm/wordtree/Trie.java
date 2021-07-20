package com.example.algorithm.wordtree;

public class Trie {
    /**
     * root节点
     */
    public Node root = new Node();

    /**
     * 节点信息
     */
    private static class Node {

        //该节点被num个单词路过
        int num = 0;
        //该节点上的单词个数
        int wordNum = 0;
        //
        boolean flag = false;
        //值
        String val = "";
        //a-z,26个字母
        Node[] child = new Node[26];

        public Node() {
        }
    }

    public Trie() {
    }


    /**
     * 插入单词
     *
     * @param string
     */
    private void insert(String string) {
        Node p = this.root;
        char[] array = string.toCharArray();
        for (char c : array) {
            int index = c - 'a';
            if (p.child[index] == null) {
                Node q = new Node();
                p.child[index] = q;
            }
            p.child[index].num++;
            p = p.child[index];
        }
        p.flag = true;
        p.val = string;
        p.wordNum++;
    }

    /**
     * 查找单词
     *
     * @param str
     * @return
     */
    private boolean search(String str) {
        Node p = this.root;
        char[] array = str.toCharArray();
        for (char c : array) {
            int index = c - 'a';
            if (p.child[index] == null) {
                return false;
            } else {
                p = p.child[index];
            }
        }
        return p.flag;
    }

    /**
     * 以str为开头的单词个数
     *
     * @param str
     * @return
     */
    private int startWithNum(String str) {
        Node p = this.root;
        char[] array = str.toCharArray();
        for (char c : array) {
            int index = c - 'a';
            if (p.child[index] == null) {
                return 0;
            } else {
                p = p.child[index];
            }
        }
        return p.num;
    }


    /**
     * 按字典序输出
     *
     * @param root
     */
    private static void printPre(Node root) {
        if (root != null) {
            int x = root.wordNum;
            while (x > 0) {
                System.out.println(root.val);
                x--;
            }
            for (Node p : root.child) {
                printPre(p);
            }
        }
    }

    public static void main(String[] args) {
        Trie root = new Trie();
        root.insert("apple");
        root.insert("app");
        root.insert("app");
        System.out.println(root.search("apple"));
        System.out.println(root.search("app"));
        System.out.println(root.search("apl"));
        System.out.println(root.search("sda"));
        System.out.println("----------------------");
        System.out.println(root.startWithNum("app"));
        System.out.println(root.startWithNum("ap"));
        System.out.println(root.startWithNum("appl"));
        System.out.println("---------------");
        //System.out.println(startWith("appp"));
        System.out.println("-----------------");
        printPre(root.root);
    }
}
