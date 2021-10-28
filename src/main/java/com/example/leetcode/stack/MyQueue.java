package com.example.leetcode.stack;


import java.util.Stack;

/**
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 * 实现 MyQueue 类：
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 * <p>
 * <p>
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 */
public class MyQueue {
    //stack in
    private Stack stackIn;
    //stack out
    private Stack stackOut;

    public MyQueue() {
        stackIn = new Stack<>(); // 负责进栈
        stackOut = new Stack<>(); // 负责出栈
    }

    public void push(int x) {
        //  System.out.println("push:" + x);
        stackIn.push(x);
    }

    public int pop() {
        copyStackOut();
        return (int) stackOut.pop();
    }

    public int peek() {
        copyStackOut();
        return (int) stackOut.peek();
    }

    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }

    // 如果stack2为空，那么将stack1中的元素全部放到stack2中
    private void copyStackOut() {
        if (stackOut.isEmpty()) {
            while (!stackIn.isEmpty()) {
                // System.out.println("转移元素");
                stackOut.push(stackIn.pop());
            }
        }
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.push(3);
        myQueue.push(4);
        System.out.println(myQueue.peek()); // return 1
        System.out.println(myQueue.pop()); // return 1, queue is [2]
        System.out.println(myQueue.empty());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
    }

}
