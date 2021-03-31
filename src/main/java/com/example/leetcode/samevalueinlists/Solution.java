package com.example.leetcode.samevalueinlists;

import com.example.leetcode.node.ListNode;
import com.example.leetcode.node.ListNodeBuilder;

/**
 * 找出相同的元素并返回A∩B
 */
public class Solution {

    public Solution() {
    }
    /**
     * 找出相同的元素并返回A∩B
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode findSameNodeInTwoLists(ListNode l1, ListNode l2) {
        ListNode front = new ListNode(0);
        //指向头结点
        ListNode rear = front;
        ListNode p1 = l1;
        ListNode p2 = l2;
        //找出相同的元素并排序返回
        while (p1 != null) {
//           p2 = l2.next;
            while (p2 != null) {
                if (p1.val == p2.val) {
//                    System.out.println("" + p1.val);
                    //构造结点
                    rear.next = new ListNode(p1.val);
                    //数据向下移动
                    rear = rear.next;
                }
                //移动数据
                p2 = p2.next;
            }
            //这里是关键
            p2 = l2.next;
            //移动数据
            p1 = p1.next;
        }
        //返回结果，去掉头结点
        return front.next;
    }

    /**
     * 输出: A∩B
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arrays1 = {1, 2, 4, 5, 9};
        int[] arrays2 = {1, 2, 3, 4, 5, 9};
        ListNode listNode1 = new ListNodeBuilder(arrays1).buildListNode();
        ListNode listNode2 = new ListNodeBuilder(arrays2).buildListNode();

        System.out.println("" + listNode1.toString());

        System.out.println("" + listNode2.toString());
        ListNode listNode = solution.findSameNodeInTwoLists(listNode1, listNode2);
        if (listNode != null) {
            System.out.println(listNode.toString());
        }

    }
}
