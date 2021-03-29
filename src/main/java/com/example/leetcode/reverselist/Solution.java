package com.example.leetcode.reverselist;

/**
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 */

import com.example.leetcode.node.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class Solution {

    // 头部结点
    public ListNode head;

    public Solution() {
        this.head = new ListNode(0);
    }

    /**
     * 构造树结点
     *
     * @param values
     */
    public Solution(int[] values) {
        this();
        //指向头结点
        ListNode rear = this.head;
        for (Integer integer : values) {
            rear.next = new ListNode(integer);
            //移动到下一个结点
            rear = rear.next;
        }
    }

    /**
     * 反转链表（不带头结点0的单链表）
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode p = head;
        ListNode q = null;
        ListNode front = null;
        while (p != null) {
            front = p.next;
            p.next = front;
            p.next = q;
            q = p;
            p = front;
        }
        return q;
    }

    public static void main(String[] args) {
        int[] arrays = {1, 2, 3, 4, 5};
        Solution solution = new Solution(arrays);
        System.out.println("" + solution.head.next.toString());
        ListNode listNode1 = solution.reverseList(solution.head.next);
        System.out.println("" + listNode1.toString());
    }
}