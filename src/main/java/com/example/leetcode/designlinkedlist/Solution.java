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
        linkedList.get(1);            //返回2

        linkedList.addAtTail(4);
        linkedList.addAtTail(5);
        linkedList.deleteAtIndex(3);
        linkedList.printNodes();//现在链表是1-> 3
        int element = linkedList.get(1);//返回3
        System.out.println("find element:" + element);
    }
}

class LinkedList {
    //head node
    private ListNode head;

    /**
     * Initialize your data structure here.
     */
    public LinkedList() {
        head = new ListNode();
    }

    public void printNodes() {
        System.out.println(head);
    }

    /**
     * LinkedList的长度
     *
     * @return
     */
    public int size() {
        int length = 0;
        ListNode p = this.head;
        while (p != null) {
            length++;
            p = p.next;
        }
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
        q.next = head.next;
        head = q;
    }

    /**
     * 尾部添加元素
     *
     * @param val
     */
    public void addAtTail(int val) {
        System.out.println("尾部添加元素->" + val);
        //注意是font.next
        ListNode rear = head;
        while (rear != null) {
            if (rear.next == null) {
                rear.next = new ListNode(val, null);
                return;
            }
            rear = rear.next;
        }
    }

    /**
     * 在链表中的第index个节点之前添加值为val的节点。
     * 如果index等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
     *
     * @param index
     * @param val
     */
    public void addAtIndex(int index, int val) {
        //front 指向头结点
        System.out.println("插入索引: " + index + "值：" + val);
        ListNode front = this.head;
        for (int j = 1; front.next != null && j < index; j++) {
            front = front.next;
        }
        //在front 后插入新的结点
        front.next = new ListNode(val, front.next);
    }

    /**
     * 如果索引index有效，则删除链表中的第index个节点。
     */
    public void deleteAtIndex(int index) {
        System.out.println("删除的索引: " + index);
        ListNode front = this.head;
        //遍历元素，且不为最后一个结点。且小于当前索引
        int size = this.size();
        //只有一个结点
        if (size == (index + 1) && size == 1) {
            head = null;
            return;
        }
        int j = 0;
        while (front != null && front.next != null) {
            j++;
            //当索引匹配则删除
            if (j == index) {
                //移除front的后继
                front.next = front.next.next;
                break;
            }
            front = front.next;
        }
    }
}