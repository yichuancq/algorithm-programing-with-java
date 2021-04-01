package com.example.leetcode.designlinkedlist;

import com.example.leetcode.node.ListNode;

public class Solution {

    /**
     * 头部添加元素->1
     * 尾部添加元素->3
     * ListNode{val=1, next=ListNode{val=3, next=null}}
     * list size:2
     * 插入索引: 1值：2
     * ListNode{val=1, next=ListNode{val=2, next=ListNode{val=3, next=null}}}
     * 删除的索引: 2
     * ListNode{val=1, next=ListNode{val=2, next=null}}
     * find element:2
     *
     * @param args
     */
    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.printNodes();
        int size = linkedList.size();
        System.out.println("list size:" + size);
        linkedList.addAtIndex(1, 2);
        linkedList.printNodes();//链表变为1-> 2-> 3
        linkedList.get(1);
        //返回2
        linkedList.addAtTail(4);
        linkedList.addAtTail(5);
        linkedList.deleteAtIndex(0);
        linkedList.printNodes();//现在链表是1-> 3
        int element = linkedList.get(1);//返回3
        System.out.println("find element:" + element);
    }
}

class LinkedList {

    private ListNode head;
    //设置一个长度变量
    public int length;

    public LinkedList() {
        length = 0;
        head = new ListNode(0);
    }

    /**
     * 获取元素
     *
     * @param index
     * @return
     */
    public int get(int index) {
        if (index >= length || index < 0) {
            return -1;
        }
        ListNode current = head;
        for (int i = 0; i <= index; i++) {
            current = current.next;
        }
        return current.val;
    }

    public void addAtHead(int val) {
        ListNode temp = new ListNode(val);
        temp.next = head.next;
        head.next = temp;
        length++;
    }

    public int size() {
        return this.length;
    }

    public void addAtTail(int val) {
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        ListNode temp = new ListNode(val);
        current.next = temp;
        length++;
    }


    public void addAtIndex(int index, int val) {
        if (index > length) {
            return;
        }
        if (index == length) {
            addAtTail(val);
            return;
        }
        if (index < 0) {
            index = index + length + 1;
        }
        ListNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        ListNode ptr = current.next;
        ListNode temp = new ListNode(val);
        current.next = temp;
        temp.next = ptr;
        length++;
    }

    public void deleteAtIndex(int index) {
        if (index >= length || index < 0) {
            return;
        }
        ListNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        length--;
    }

    public void printNodes() {
        ListNode current = head;
        StringBuilder builder = new StringBuilder();
        while (current != null) {
            builder.append(current.val + " ");
            current = current.next;
        }
        System.out.println(builder);
    }
}