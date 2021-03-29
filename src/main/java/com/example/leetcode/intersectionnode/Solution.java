package com.example.leetcode.intersectionnode;

import com.example.leetcode.node.ListNode;
import com.example.leetcode.node.ListNodeBuilder;

/**
 * 输入两个链表，找出它们的第一个公共节点
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 */
public class Solution {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode front = null;
        //指向头结点
//        ListNode rear = front;
        ListNode p1 = headA;
        ListNode p2 = headB;
        //找出相同的元素并排序返回
        while (p1 != null) {
//           p2 = l2.next;
            while (p2 != null) {
                if (p1.val == p2.val) {
//                    System.out.println("" + p1.val);
                    //构造结点
                    front = new ListNode(p1.val);
                    //数据向下移动
                    return front;
                }
                //移动数据
                p2 = p2.next;
            }
            //这里是关键
            p2 = headB.next;
            //移动数据
            p1 = p1.next;
        }
        //返回结果，去掉头结点
        return front;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arrays1 = {4, 1, 8, 4, 5};
        int[] arrays2 = {5, 0, 1, 8, 4, 5};
        ListNode listNode1 = new ListNodeBuilder(arrays1).buildListNode();
        ListNode listNode2 = new ListNodeBuilder(arrays2).buildListNode();

        System.out.println("" + listNode1);

        System.out.println("" + listNode2);
        ListNode listNode = solution.getIntersectionNode(listNode1, listNode2);
        System.out.println(listNode);

    }
}
