package com.example.leetcode.designskiplist;

import java.util.Random;

/**
 * 跳跃表
 * 跳表的性质
 * 1 由很多层结构组成，level是通过一定的概率随机产生的
 * 2 每一层都是一个有序的链表，默认是升序
 * 3 最底层(Level 1)的链表包含所有元素
 * 4 如果一个元素出现在Level i 的链表中，则它在Level i 之下的链表也都会出现
 * 5 每个节点包含两个指针，一个指向同一链表中的下一个元素，一个指向下面一层的元素
 *
 * @author yichuan
 */
public class SkipList {
    private static final int MAX_LEVEL = 16;
    private static final float SIMPLEST_P = 0.5f;
    private int levelCount = 1;
    // 带头链表
    private LinkNode head = new LinkNode();
    private Random r = new Random();

    public SkipList() {

    }

    /**
     * @param value
     * @return
     */
    public LinkNode find(int value) {
        LinkNode p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.next[i] != null && p.next[i].val < value) {
                p = p.next[i];
            }
        }
        if (p.next[0] != null && p.next[0].val == value) {
            return p.next[0];
        } else {
            return null;
        }
    }

    private int randomLevel() {
        int level = 1;

        while (Math.random() < SIMPLEST_P && level < MAX_LEVEL) {
            level += 1;
        }
        return level;
    }

    /**
     * 输出结点
     */
    public void printAll() {
        LinkNode p = head;
        while (p.next[0] != null) {
            System.out.print(p.next[0] + " ");
            p = p.next[0];
        }
        System.out.println();
    }


    /**
     * 插入结点
     *
     * @param value
     */
    public void insert(int value) {
        int level = randomLevel();
        LinkNode newNode = new LinkNode();
        newNode.val = value;
        newNode.maxLevel = level;
        LinkNode[] linkNodes = new LinkNode[level];
        for (int i = 0; i < level; ++i) {
            linkNodes[i] = head;
        }
        LinkNode p = head;
        for (int i = level - 1; i >= 0; --i) {
            while (p.next[i] != null && p.next[i].val < value) {
                p = p.next[i];
            }
            linkNodes[i] = p;
        }
        for (int i = 0; i < level; ++i) {
            newNode.next[i] = linkNodes[i].next[i];
            linkNodes[i].next[i] = newNode;
        }
        if (levelCount < level) {
            levelCount = level;
        }
    }


    /**
     * @param value
     */
    public void delete(int value) {
        LinkNode[] linkNodes = new LinkNode[levelCount];
        LinkNode p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.next[i] != null && p.next[i].val < value) {
                p = p.next[i];
            }
            linkNodes[i] = p;
        }

        if (p.next[0] != null && p.next[0].val == value) {
            for (int i = levelCount - 1; i >= 0; --i) {
                if (linkNodes[i].next[i] != null && linkNodes[i].next[i].val == value) {
                    linkNodes[i].next[i] = linkNodes[i].next[i].next[i];
                }
            }
        }
    }

    /**
     * 跳跃表结点
     *
     * @param <T>
     */
    class LinkNode<T> {
        /**
         * 数据域
         */
        public int val;
        /**
         * next结点
         */
        public LinkNode<T>[] next = new LinkNode[MAX_LEVEL];

        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(val);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");
            return builder.toString();
        }
    }

    public static void main(String[] args) {

        SkipList skipList = new SkipList();
        int len = 10;
        for (int i = 0; i < len; i++) {
            skipList.insert(i);
        }
        //print all
        skipList.printAll();
        //
        SkipList.LinkNode searchKey = skipList.find(8);
        if (searchKey != null) {
            System.out.println("key->" + searchKey.val);
        }
        //delete
        skipList.delete(4);
        //print all
        skipList.printAll();
        //delete
        skipList.delete(0);
        skipList.printAll();

        searchKey = skipList.find(9);
        if (searchKey != null) {
            System.out.println("key->" + searchKey.val);
        }
        //add
        // skipList.insert(0);
        //print all
        skipList.printAll();
    }
}
