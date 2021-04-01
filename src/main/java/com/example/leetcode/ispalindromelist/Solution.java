package com.example.leetcode.ispalindromelist;

import com.example.leetcode.node.ListNode;
import com.example.leetcode.node.ListNodeBuilder;

/**
 * 编写一个函数，检查输入的链表是否是回文的
 * 示例 1：
 * <p>
 * 输入： 1->2
 * 输出： false
 * 示例 2：
 * <p>
 * 输入： 1->2->2->1
 * 输出： true
 *
 * @author yichuan
 */
public class Solution {
    /***
     * 回文判断
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 只有一个节点肯定是true
        ListNode part1 = head;
        ListNode part2 = head;
        ListNode temp = null;
        while (part1 != null) {
            /**
             * 1.快速找到中间的节点
             * 2.将慢指针指向的前半部分翻转
             * 3.从中间开始向左右两边遍历比较
             */
            // 偶数个节点
            if (part1.next != null) {
                part2 = part2.next;
                part1 = part1.next.next;
                // 遍历时顺便翻转前半部分
                // 翻转代码
                head.next = temp;
                temp = head;
                head = part2;
            } else { // 奇数个节点
                part2 = part2.next;
                // 遍历时顺便翻转前半部分
                // 翻转代码
                head.next = temp;
                head = part2;
                break;
            }

        }
        while (temp != null) {
            if (temp.val != head.val) {
                return false;
            }
            temp = temp.next;
            head = head.next;
        }
        return true;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arrays = {1, 2, 0, 2, 1};
        ListNode listNode = new ListNodeBuilder(arrays).buildListNode();
        System.out.println(listNode);
        //回文判断
        boolean isPalindrome = solution.isPalindrome(listNode);
        System.out.println(isPalindrome);
    }
}
