package com.example.algorithm.stack;

/**
 * ADT Stack<T>栈抽象数据类型，T表示数据元素的数据类型
 *
 * @param <T>
 */
public interface Stack<T> {
    /**
     * 判断栈是否空
     *
     * @return
     */
    public abstract boolean isEmpty();

    /**
     * 元素x入栈
     */
    public abstract void push(T x);

    /**
     * 返回栈顶元素，未出栈
     *
     * @return
     */

    public abstract T peek();

    /**
     * 出栈，返回栈顶元素
     * @return
     */
    public abstract T pop();
}
