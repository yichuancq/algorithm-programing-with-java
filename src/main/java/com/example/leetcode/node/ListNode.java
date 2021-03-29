package com.example.leetcode.node;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    /**
     * @param val
     */
    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {

        if (this == null) {
            return "list is null";
        } else {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }

    }
}
