package com.example.leetcode.samevalueinlists;

import com.example.leetcode.node.ListNode;
import com.example.leetcode.node.ListNodeBuilder;

/**
 * 找出相同的元素并排序返回
 */
public class Solution {

    public Solution() {
    }


    /**
     * 找出相同的元素并排序返回
     *
     * @param l1
     * @param l2
     * @return
     */
    public void findSameNodeInTwoLists(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        //TODO: 2021/3/29 找出相同的元素并排序返回

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arrays1 = {1, 2, 4, 5, 9};
        int[] arrays2 = {1, 2, 3, 4, 9};
        ListNode listNode1 = new ListNodeBuilder(arrays1).buildListNode();
        ListNode listNode2 = new ListNodeBuilder(arrays2).buildListNode();

        System.out.println("" + listNode1.toString());

        System.out.println("" + listNode2.toString());
        solution.findSameNodeInTwoLists(listNode1, listNode2);

    }
}
