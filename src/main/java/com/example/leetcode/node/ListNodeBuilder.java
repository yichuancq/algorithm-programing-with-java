package com.example.leetcode.node;

/**
 * 构造链表
 */
public class ListNodeBuilder {

    private int[] arrays;

    public ListNodeBuilder(int[] arrays) {
        this.arrays = arrays;
    }

    /**
     * 构造树结点
     *
     * @param
     */
    public ListNode buildListNode() {
        //生成一个临时结点
        ListNode head = new ListNode(0);
        //指向头结点
        ListNode rear = head;
        for (Integer integer : arrays) {
            rear.next = new ListNode(integer);
            //移动到下一个结点
            rear = rear.next;
        }
        return head.next;
    }
}
