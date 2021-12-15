package com.example.leetcode.skiplist;

/**
 * 跳表是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，
 * 并且跳表的代码长度相较下更短，其设计思想与链表相似。
 * 例如，一个跳表包含 [30, 40, 50, 60, 70, 90]，然后增加 80、45 到跳表中，以下图的方式操作：
 * 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(n))，空间复杂度是 O(n)。
 * <p>
 * 你的设计应该要包含这些函数：
 * bool search(int target) : 返回target是否存在于跳表中。
 * void add(int num):插入一个元素到跳表。
 * bool erase(int num): 在跳表中删除一个值，如果num不存在，直接返回false. 如果存在多个num，删除其中任意一个即可。
 */
public class SkipLists {


    static final double FACTOR = 0.25;
    static final int MAX_LEVEL = 31;
    private Node head;
    private int curLevel = 0;

    public SkipLists() {
        head = new Node(-1, MAX_LEVEL);
    }

    public boolean search(int target) {
        Node startNode = head;
        for (int level = curLevel; level >= 0; level--) {
            startNode = findPreNode(target, startNode, level);
            if (startNode.next[level] != null && startNode.next[level].value == target) {
                return true;
            }
        }
        return false;
    }

    public void add(int num) {
        int newLevel = randomLevel();
        Node newNode = new Node(num, newLevel);
        Node startNode = head;
        for (int level = curLevel; level >= 0; level--) {
            startNode = findPreNode(num, startNode, level);
            if (level <= newLevel) {
                Node tmp = startNode.next[level];
                startNode.next[level] = newNode;
                newNode.next[level] = tmp;
            }
        }
        if (newLevel > curLevel) {
            for (int level = curLevel + 1; level <= newLevel; level++) {
                head.next[level] = newNode;
            }
            curLevel = newLevel;
        }
    }

    public boolean erase(int num) {
        Node startNode = head;
        boolean flag = false;
        for (int level = curLevel; level >= 0; level--) {
            startNode = findPreNode(num, startNode, level);
            if (startNode.next[level] != null && startNode.next[level].value == num) {
                startNode.next[level] = startNode.next[level].next[level];
                flag = true;
            }
        }
        return flag;
    }

    public Node findPreNode(int num, Node startNode, int level) {
        while (startNode.next[level] != null && startNode.next[level].value < num) {
            startNode = startNode.next[level];
        }
        return startNode;
    }

    public int randomLevel() {
        int level = 0;
        while (Math.random() < FACTOR && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    public static class Node {
        int value;
        Node[] next;

        public Node(int value, int size) {
            this.value = value;
            this.next = new Node[size + 1];
        }
    }


    /**
     * Your Skiplist object will be instantiated and called as such:
     * Skiplist obj = new Skiplist();
     * boolean param_1 = obj.search(target);
     * obj.add(num);
     * boolean param_3 = obj.erase(num);
     */
    public static void main(String[] args) {
        long startTime = 0;
        long endTime = 0;
        SkipLists skipList = new SkipLists();
        int size = 10000;
        //测试跳跃表性能
        startTime = System.currentTimeMillis();
        for (int i = 1; i < size; i++) {
            skipList.add(i);
        }
        endTime = System.currentTimeMillis();
        System.out.printf("createSkipList:%d纳秒\n", endTime - startTime);
        startTime = System.currentTimeMillis();
        System.out.printf("find(555)：%b\n", skipList.search(999));
        System.out.printf("find(88)：%b\n", skipList.search(88));
        endTime = System.currentTimeMillis();
        System.out.printf("skipListFindTime:%d\n\n", endTime - startTime);

    }
}
