package com.example.leetcode.designlinkedlist;

import com.example.leetcode.node.ListNode;

public class Solution {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addAtTail(1);
        linkedList.addAtTail(2);
        linkedList.addAtTail(4);
        linkedList.addAtHead(8);
        linkedList.addAtHead(9);
        linkedList.printNodes();
        linkedList.addAtIndex(3, 99);   //链表变为1-> 2-> 3
        int element = linkedList.get(4);            //返回2
        System.out.println("获取元素值:" + element);
        linkedList.printNodes();
        linkedList.deleteAtIndex(3);  //现在链表是1-> 3
        linkedList.printNodes();
    }
}

class LinkedList {
    //head node
    private ListNode head;
    public ListNode font;

    /**
     * Initialize your data structure here.
     */
    public LinkedList() {

    }

    public void printNodes() {
        System.out.println(head);
    }


    /**
     * LinkedList的长度
     *
     * @return
     */
    private int size() {
        int length = 0;
        ListNode p = head;
        while (p != null) {
            length++;
            p = p.next;
        }
        System.out.println("list size:" + length);
        return length;
    }

    /**
     * 根据索引获取当前结点
     *
     * @param index
     * @return
     */
    public ListNode getNodeByIndex(int index) {
        // ListNode p = null;
        ListNode p = head;
        int element = 0;
        if (head == null || index < 0) {
            return null;
        }
        //越界
        if (index > this.size()) {
            return null;
        }
        int pose = 0;
        while (p != null) {
            if (index == pose) {
                return p;
            }
            pose++;
            p = p.next;
        }
        return p;
    }

    /**
     * 获取链表中第 index 个节点的值。如果索引无效，则返回-1
     *
     * @param index
     * @return
     */
    public int get(int index) {
        int element = 0;
        if (head == null || index < 0) {
            return -1;
        }
        //越界
        if (index > this.size()) {
            return -1;
        }
        ListNode p = head;
        int pose = 0;
        while (p != null) {
            if (index == pose) {
                element = p.val;
                return element;
            }
            pose++;
            p = p.next;
        }
        return element;
    }

    /**
     * 在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点
     *
     * @param val
     */
    public void addAtHead(int val) {
        System.out.println("头部添加元素->" + val);
        ListNode q = new ListNode(val);
        if (head == null) {
            head = q;
            font = head;
            return;
        }
        q.next = head;
        head = q;
    }

    /**
     * 尾部添加元素
     *
     * @param val
     */
    public void addAtTail(int val) {
        //注意是font.next
        System.out.println("尾部添加元素->" + val);
        //如果头部结点为空
        if (head == null && font == null) {
            head = new ListNode(val);
            font = head;
            return;
        }
        font.next = new ListNode(val);
        font = font.next;


    }

    /**
     * 在链表中的第index个节点之前添加值为val的节点。如果index等于链表的长度，
     * 则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
     */
    public void addAtIndex(int index, int val) {
        //linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
        System.out.println("插入索引: " + index + "值：" + val);
        int size = this.size();
        if (index < 0 || index > size) {
            return;
        }
        ListNode p = head;
        ListNode insertNode = new ListNode(val);
        int pose = 0;
        while (p != null) {
            if (pose == index) {
                //insert node
                ListNode pre = getNodeByIndex(index);
                //新结点下一个指向当前结点的下一个结点
                insertNode.next = p.next;
                System.out.println("上一个结点：" + pre);
                //上一个结点的下一个结点指向新插入的结点
                pre.next = insertNode;
            }
            pose++;
            p = p.next;
        }
    }

    /**
     * 如果索引index有效，则删除链表中的第index个节点。
     */
    public void deleteAtIndex(int index) {
        System.out.println("删除的索引: " + index);
        int size = this.size();
        if (index < 0 || index > size) {
            return;
        }
        ListNode p = head;
        int pose = 0;
        while (p != null) {
            if (pose == index) {
                System.out.println("delete...");
                //赋值
                p.val = p.next.val;
                //结点向前移动一位
                p.next = p.next.next;
                return;
            }
            pose++;
            p = p.next;
        }
    }
}