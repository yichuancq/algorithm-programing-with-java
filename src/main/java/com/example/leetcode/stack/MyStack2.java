package com.example.leetcode.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通栈的全部四种操作（push、top、pop 和 empty)
 * 实现 MyStack 类：
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 * <p>
 * 解释：
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // 返回 2
 * myStack.pop(); // 返回 2
 * myStack.empty(); // 返回 False
 */
public class MyStack2 {
    private Queue<Integer> a;//输入队列
    private Queue<Integer> b;//输出队列

    public MyStack2() {
        a = new LinkedList<>();
        b = new LinkedList<>();
    }

    /**
     * 添加元素
     *
     * @param x
     */
    public void push(int x) {
        a.offer(x);
        // 将b队列中元素全部转给a队列
        while (!b.isEmpty()) {
            a.offer(b.poll());
        }
        //交换a和b,使得a队列没有在push()的时候始终为空队列
        Queue temp = a;
        a = b;
        b = temp;
    }

    /**
     * 弹出元素
     *
     * @return
     */
    public int pop() {
        return b.poll();
    }

    /**
     * 取顶部元素
     *
     * @return
     */
    public int top() {
        return b.peek();
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean empty() {
        return b.isEmpty();
    }

    public static void main(String[] args) {

        MyStack2 myStack = new MyStack2();
        myStack.push(1);
        myStack.push(2);
        // myStack.top(); // 返回 2
        System.out.println(myStack.top());
        // myStack.pop(); // 返回 2
        System.out.println(myStack.pop());
        //myStack.empty(); // 返回 False
        System.out.println(myStack.empty());
    }
}
