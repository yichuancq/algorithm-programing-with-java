package com.example.leetcode.removefromlist;

import com.example.leetcode.node.ListNode;
import com.example.leetcode.node.ListNodeBuilder;

/**
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * 示例 1:
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * <p>
 * 示例 2：
 * 输入：head = [], val = 1
 * 输出：[]
 * <p>
 * 示例 3：
 * <p>
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 */
public class Solution {
    /**
     * 删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        //添加临时结点
        ListNode p = new ListNode(0);
        p.next = head;
        //添加临时结点
        ListNode q = p;
        while (p.next != null) {
            if (p.next.val == val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return q.next;

    }

    /**
     * 删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements3(ListNode head, int val) {
        //添加临时结点
        ListNode p = head.next;
        while (p.next != null) {
            if (p.next.val == val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return head;

    }


    /**
     * 删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode head, int val) {
        ListNode listNode = head.next;
        while (head.next != null) {
            if (head.next.val == val) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return listNode;
    }

    public static void main(String[] args) {
        int[] arrays = {1, 2, 6, 3, 4, 5, 6, 6, 5, 5};
        Solution solution = new Solution();
//        System.out.println("" + solution.head.next.toString());
//        // 方法1
//        ListNode listNode1 = solution.removeElements2(solution.head.next, 6);
//        System.out.println("" + listNode1);
//        //方法2
//        ListNode listNode2 = solution.removeElements2(solution.head.next, 6);
//        System.out.println("" + listNode2);
//        //removeElements3
        ListNode head = new ListNodeBuilder(arrays).buildListNode();
        ListNode listNode3 = solution.removeElements(head, 5);
        System.out.println("" + listNode3);
    }

}
