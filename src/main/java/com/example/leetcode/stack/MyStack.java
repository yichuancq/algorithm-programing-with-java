package com.example.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 解释：
 * MyStack myStack = new MyStack();
 * myStack.push(1);
 * myStack.push(2);
 * myStack.top(); // 返回 2
 * myStack.pop(); // 返回 2
 * myStack.empty(); // 返回 False
 */
public class MyStack {

    // Deque 接口继承了 Queue 接口
    // 所以 Queue 中的 add、poll、peek等效于 Deque 中的 addLast、pollFirst、peekFirst
    Deque<Integer> deque;

    public MyStack() {
        deque = new ArrayDeque<>();
    }

    public void push(int x) {
        deque.addLast(x);
    }

    /**
     * 弹出元素
     *
     * @return
     */
    public int pop() {
        int size = deque.size();
        size--;
        // 将 que1 导入 que2 ，但留下最后一个值
        while (size-- > 0) {
            deque.addLast(deque.peekFirst());
            deque.pollFirst();
        }

        int res = deque.pollFirst();
        return res;
    }

    /**
     * 取顶部元素
     *
     * @return
     */
    public int top() {
        return deque.peekLast();
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean empty() {
        return deque.isEmpty();
    }

    public static void main(String[] args) {

        // TODO: 2021/10/28 用队列实现栈
        MyStack myStack = new MyStack();
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
