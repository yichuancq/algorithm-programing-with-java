package com.example.learn.singlylist;

/**
 * 自定义节点
 *
 * @param <T>
 */
public class Node<T> {
    /**
     * 数据域
     */
    public T data;
    /**
     * 地址域，引用后继结点
     */
    public Node<T> next;

    /**
     *
     */
    public Node() {
        this(null, null);
    }

    /**
     * @param data
     * @param next
     */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public String toString() {
        return this.data.toString();

    }


}
