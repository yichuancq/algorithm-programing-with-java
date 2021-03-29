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
 */
public class Solution {
    /***
     * 回文判断
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arrays = {1, 2, 2, 1};
        ListNode listNode = new ListNodeBuilder(arrays).buildListNode();
        System.out.println(listNode);
        //回文判断
        boolean isPalindrome = solution.isPalindrome(listNode);
        System.out.println(isPalindrome);
    }
}
