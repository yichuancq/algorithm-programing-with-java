package com.example.leetcode.designlinkedlist;

import com.example.leetcode.node.ListNode;

public class Solution {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
//        linkedList.addAtTail(1);
//        linkedList.addAtTail(2);
//        linkedList.addAtTail(4);
        linkedList.addAtHead(8);
        linkedList.addAtTail(4);
//        linkedList.addAtHead(9);
//        linkedList.addAtHead(10);
//        linkedList.addAtTail(11);
//        linkedList.addAtIndex(1, 2);   //链表变为1-> 2-> 3
        int element = linkedList.get(1);            //返回2
        System.out.println("node val:" + element);
//        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
//        linkedList.get(1);            //返回3

        linkedList.printNodes();
    }
}

class LinkedList {
    //head node
    ListNode head = new ListNode(-1);
    ListNode font = head;
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
        System.out.println(length);
        return length;
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
            p = p.next;
            if (p != null && index == pose) {
                element = p.val;
                return element;
            }
            pose++;
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
        //
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
        font.next = new ListNode(val);
        font = font.next;


    }

    /**
     * 将值为 val 的节点追加到链表的最后一个元素
     */
    public void addAtIndex(int index, int val) {

    }

    /**
     * 在链表中的第index个节点之前添加值为val的节点。
     * 如果index等于链表的长度，则该节点将附加到链表的末尾。
     * 如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
     */
    public void deleteAtIndex(int index) {

    }
}