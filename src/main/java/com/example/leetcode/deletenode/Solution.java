package com.example.leetcode.deletenode;

import com.example.leetcode.node.ListNode;

/**
 * 实现一种算法，删除单向链表中间的某个节点（即不是第一个或最后一个节点），假定你只能访问该节点。
 * <p>
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode head;

    public Solution() {
        this.head = new ListNode(0);
    }

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
     * 输入：单向链表a->b->c->d->e->f中的节点c
     * 结果：不返回任何数据，但该链表变为a->b->d->e->f
     */
    public ListNode deleteNode(ListNode head, int del) {
        //设置一个临时结点
        //添加临时结点
        ListNode p = new ListNode(-1);
        p.next = head;
        while (p.next != null) {
            if (p.next.val == del && p.next != null) {
                System.out.println("delete: " + del);
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }


    /**
     * 改成for 循环
     *
     * @param head
     * @param del
     * @return
     */
    public ListNode deleteNode2(ListNode head, int del) {
        return head;

    }

    public static void main(String[] args) {
        // 删除结点'c'
        Solution solution = new Solution(new int[]{1, 2, 3, 4, 5, 7});
        System.out.println("" + solution.head.next.toString());
        ListNode listNode = solution.deleteNode(solution.head.next, 3);
        System.out.println(listNode.toString());
        // System.out.println("" + solution.head.next.toString());
    }


}



