package com.example.leetcode.orderlist;

import com.example.leetcode.node.ListNode;
import com.example.leetcode.node.ListNodeBuilder;

import java.util.Arrays;

/**
 * 给出链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 */
public class Solution {

    /**
     * 排序链表
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //获取长度
        int len = getLength(head);
        int[] numbs = ListNode2Array(head, len);
        //排序
        Arrays.sort(numbs);
        ListNode newHead = new ListNode(-1);
        ListNode tmp = newHead;
        for (int i = 0; i <= len - 1; i++) {
            newHead.val = numbs[i];
            if (i < len - 1) {
                newHead.next = new ListNode(-1);
                newHead = newHead.next;
            }
        }

        return tmp;
    }

    public int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    public int[] ListNode2Array(ListNode head, int len) {
        int[] numbs = new int[len];
        int i = 0;
        while (head != null) {
            numbs[i] = head.val;
            head = head.next;
            i++;
        }
        return numbs;
    }

    public static void main(String[] args) {
        int[] arrays = {-1, 5, 3, 4, 0};
        Solution solution = new Solution();
        ListNode listNode = new ListNodeBuilder(arrays).buildListNode();
        System.out.println(listNode);
        System.out.println();
        //
        ListNode last = solution.sortList(listNode);
        System.out.println(last);

    }
}
