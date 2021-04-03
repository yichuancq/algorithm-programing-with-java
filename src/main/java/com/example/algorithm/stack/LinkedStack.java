package com.example.algorithm.stack;

import com.example.algorithm.singlylist.SinglyList;

/**
 * 链式栈类，最终类，实现栈接口，T表示数据元素的数据类型
 */
public final class LinkedStack<T> implements Stack<T> {
    private SinglyList<T> list;                            //使用单链表（第2章）存储栈元素

    /**
     * //构造空栈
     */
    public LinkedStack() {
        this.list = new SinglyList<T>();                   //构造空单链表
    }

    /**
     * 判断栈是否空，若空返回true
     *
     * @return
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * 元素x入栈，空对象不能入栈
     *
     * @param x
     */
    public void push(T x) {
        this.list.insert(0, x);                            //单链表头插入元素x
    }

    /***
     *  返回栈顶元素（未出栈）；若栈空返回null
     * @return
     */
    public T peek() {
        return this.list.get(0);
    }

    /**
     * 出栈，返回栈顶元素；若栈空返回null
     *
     * @return
     */
    public T pop() {
        return this.list.remove(0);                        //若栈不空，单链表头删除，返回删除元素
    }

    /***
     * 返回栈所有元素的描述字符串，形式为“(,)”
     * @return
     */
    public String toString() {
        return this.getClass().getName() + " " + this.list.toString();
    }
}