package com.example.leetcode.removeduplicatesfromlist;


import com.example.leetcode.node.ListNode;
import com.example.leetcode.node.ListNodeBuilder;

/**
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素只出现一次 。
 * <p>
 * 返回同样按升序排列的结果链表。
 * <p>
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 */
public class Solution {

    /**
     * 链表去重复
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {

        if (head == null) return head;
        if (head.next == null) return head;
        ListNode p = head; // head.val 为链表中的第一个节点的元素值
        while (p.next != null) {
            if (p.val == p.next.val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;
    }


    public static void main(String[] args) {
        //head = [1,1,2,3,3]
        int[] arrays = {1, 1, 2, 3, 3, 7, 7, 8, 8};
        Solution solution = new Solution();
        //输出：[1,2,3]
        ListNode listNode= new ListNodeBuilder(arrays).buildListNode();

        System.out.println("" + listNode.toString());

        ListNode last = solution.deleteDuplicates2(listNode);
        System.out.println("result:" + last);
    }

}
