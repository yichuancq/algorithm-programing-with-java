package com.example.leetcode.orderlist;

import com.example.leetcode.node.ListNode;
import com.example.leetcode.node.ListNodeBuilder;

/**
 * 给出链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 */
public class Solution {

    /**
     * 排序
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        /**
         *查找 当前链表的 “中点”
         */
        ListNode fastNode = head;
        ListNode slowHead = head;
        while (fastNode.next != null && fastNode.next.next != null) {
            slowHead = slowHead.next;
            fastNode = fastNode.next.next;
        }

        ListNode midNode = slowHead.next;
        slowHead.next = null;   // 将 两半链表 断开
        ListNode leftHead = sortList(head);
        ListNode rightHead = sortList(midNode);
        return merge(leftHead, rightHead);
    }

    /**
     * 为 两个链表 进行 “归并排序”
     *
     * @param leftHead  左链表头
     * @param rightHead 右链表头
     * @return 当前两个链表的 排序后的头
     */
    private ListNode merge(ListNode leftHead, ListNode rightHead) {
        if (leftHead == null) {
            return rightHead;
        }
        if (rightHead == null) {
            return leftHead;
        }
        if (leftHead.val < rightHead.val) {
            leftHead.next = merge(leftHead.next, rightHead);
            return leftHead;
        } else {
            rightHead.next = merge(leftHead, rightHead.next);
            return rightHead;
        }
    }

    public static void main(String[] args) {
        int[] arrays = {-1, 5, 3, 4, 0};
        Solution solution = new Solution();
        ListNode listNode = new ListNodeBuilder(arrays).buildListNode();
        System.out.println(listNode);
        System.out.println();
        //
        ListNode last = solution.sortList(listNode);
        //   ListNode last = solution.sortList(listNode);
        System.out.println(last);

    }
}
