package com.example.leetcode.mergelist;

import com.example.leetcode.node.ListNode;
import com.example.leetcode.node.ListNodeBuilder;

/**
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的
 */
public class Solution {

    public Solution() {
    }


    /**
     * 合并方法2
     *
     * @param l1
     * @param l2
     * @return
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = new ListNode();
        ListNode p = head;
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null && p2 != null) {
            //比较结点的大小
            if (p1.val < p2.val) {
                p.next = p1;
                //遍历下一个结点
                p1 = p1.next;
            } else {
                p.next = p2;
                //遍历下一个结点
                p2 = p2.next;
            }
            p = p.next;
        }
        while (p1 != null) {
            p.next = p1;
            //遍历下一个结点
            p1 = p1.next;
            p = p.next;
        }
        while (p2 != null) {
            p.next = p2;
            //遍历下一个结点
            p2 = p2.next;
            p = p.next;
        }
        return head.next;
    }


    /**
     * 合并链表
     * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        //指向头结点
        ListNode rear = head;
        while (l1 != null) {
            System.out.println("" + l1.val);
            rear.next = new ListNode(l1.val);
            //移动到下一个结点
            rear = rear.next;
            l1 = l1.next;
            if (l1 == null) {
                System.out.println("l1 end");
            }
        }
        while (l2 != null) {
            System.out.println("" + l2.val);
            rear.next = new ListNode(l2.val);
            //移动到下一个结点
            rear = rear.next;
            l2 = l2.next;
        }
        //排序链表
        return orderList(head.next);

    }

    /**
     * @param head
     * @return
     */
    private ListNode orderList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode left = new ListNode();
        ListNode right = new ListNode();
        ListNode p1 = left;
        ListNode p2 = right;
        ListNode p = head.next;
        ListNode base = head;  // 选取头节点为基准节点
        base.next = null;
        // 剩余节点中比基准值小就放left里,否则放right里,按照大小拆分为两条链表
        while (p != null) {
            ListNode pn = p.next;
            p.next = null;
            if (p.val < base.val) {
                p1.next = p;
                p1 = p1.next;
            } else {
                p2.next = p;
                p2 = p2.next;
            }
            p = pn;
        }
        // 递归对两条链表进行排序
        left.next = orderList(left.next);
        right.next = orderList(right.next);
        // 先把又链表拼到base后面
        base.next = right.next;
        // 左链表+基准节点+右链表拼接,左链表有可能是空,所以需要特殊处理下
        if (left.next != null) {
            p = left.next;
            // 找到左链表的最后一个节点
            while (p.next != null) {
                p = p.next;
            }
            // 把base拼接到左链表的末尾
            p.next = base;
            return left.next;
        } else {
            return base;
        }
    }

    /**
     * 合并数组链表，返回新链表
     * 给定一个链表数组，每个链表都已经按升序排列
     * 将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(0);
        //front指向头结点
        ListNode front = head;
        for (ListNode listNode : lists) {
            System.out.println("" + listNode.toString());
            //内部结点
            ListNode p = listNode;
            while (p != null) {
                //下一个结点赋值
                front.next = new ListNode(p.val);
                front = front.next;
                //移动外层结点元素
                p = p.next;
            }
        }
        //返回前排序元素
        return this.orderList(head.next);
    }

    public static void main(String[] args) {
        int[] arrays1 = {1, 2, 4, 5, 5, 6};
        int[] arrays2 = {1, 3, 4};
        int[] arrays3 = {1, 3, 10};
        Solution solution = new Solution();
        //构造结点1
        ListNode listNode1 = new ListNodeBuilder(arrays1).buildListNode();
        //构造结点2
        ListNode listNode2 = new ListNodeBuilder(arrays2).buildListNode();
        //构造结点3
        ListNode listNode3 = new ListNodeBuilder(arrays3).buildListNode();
        //
        System.out.println("" + listNode1.toString());
        System.out.println("" + listNode2.toString());
        System.out.println("" + listNode3.toString());
        //
        //  ListNode listNode = solution.merge(listNode1, listNode2);
//        ListNode listNode = solution.mergeTwoLists(listNode1, listNode2);
        //   System.out.println("合并后的链表：" + listNode.toString());
        //合并链表
        ListNode[] lists = {listNode1, listNode2, listNode3};
        ListNode last = solution.mergeKLists(lists);
        System.out.println(last);
    }
}
