package com.example.leetcode.skiplist;

/**
 * 跳表是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，
 * 其设计思想与链表相似
 * <p>
 * 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(n))，
 * 空间复杂度是 O(n)。
 * <p>
 * 在本题中，你的设计应该要包含这些函数：
 * bool search(int target) : 返回target是否存在于跳表中。
 * void add(int num):插入一个元素到跳表。
 * bool erase(int num): 在跳表中删除一个值，如果num不存在，直接返回false. 如果存在多个num，删除其中任意一个即可
 * <p>
 * 跳跃表
 */
public class MySkipList {
    private static final float SIMPLEST_P = 0.5f;
    private static final int MAX_LEVEL = 16;

    private Node head;

    /**
     *
     */
    private class Node {
        private int val;
        private Node bw; //后退指针
        private Node[] fw; //前进指针

        public Node(int val) {
            this.val = val;
            fw = new Node[randomLevel()];
        }

        public Node(int val, int size) {
            this.val = val;
            fw = new Node[size + 1];
        }

        private int randomLevel() {
            int level = 1;
            while (Math.random() < SIMPLEST_P && level < MAX_LEVEL) level++;
            return level;
        }
    }

    public MySkipList() {
        head = new Node(-1, MAX_LEVEL);
    }

    public boolean search(int num) {
        Node p = searchNode(num);
        return p.val == num;
    }

    /**
     * 添加元素
     *
     * @param num
     */
    public void add(int num) {
        Node p = searchNode(num);
        Node n = new Node(num);
        n.bw = p;
        for (int i = 0; i < n.fw.length; i++) {
            Node f = p;
            while (f.bw != null && f.fw.length < i + 1) f = f.bw;
            if (i == 0 && f.fw[i] != null) f.fw[i].bw = n;
            n.fw[i] = f.fw[i];
            f.fw[i] = n;
        }
    }

    /**
     * 删除元素
     *
     * @param num
     * @return
     */
    public boolean erase(int num) {
        if (isEmpty()) return false;
        Node p = searchNode(num);
        if (p.val != num) return false;
        for (int i = 0; i < p.fw.length; i++) {
            Node f = p.bw;
            while (f.bw != null && f.fw.length < i + 1) f = f.bw;
            if (i == 0 && f.fw[i].fw[i] != null) f.fw[i].fw[i].bw = f;
            f.fw[i] = f.fw[i].fw[i];
        }
        return true;
    }

    /**
     * 查找节点
     *
     * @param target
     * @return
     */
    private Node searchNode(int target) {
        if (isEmpty()) return head;
        Node p = head;
        for (int i = MAX_LEVEL; i >= 0; i--) while (p.fw[i] != null && p.fw[i].val <= target) p = p.fw[i];
        return p;
    }

    /**
     * 是否为空
     *
     * @return
     */
    private boolean isEmpty() {
        return head.fw[0] == null;
    }

    public static void main(String[] args) {
        MySkipList skipList = new MySkipList();
        skipList.add(1);
        skipList.add(2);
        skipList.add(3);
        skipList.search(0);   // 返回 false
        skipList.add(4);
        boolean flag = skipList.search(1);   // 返回 true
        System.out.println("search:" + flag);
        skipList.erase(0);    // 返回 false，0 不在跳表中
        skipList.erase(1);    // 返回 true
        skipList.search(1);   // 返回 false，1 已被擦除
    }
}
