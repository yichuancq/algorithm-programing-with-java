package com.example.learn.liststudent.base;

/**
 * 数据结点
 *
 * @param <T>
 */
public class LinkNode<T> {
    /**
     * 数据域
     */
    public T data;
    /**
     * 结点
     */
    public LinkNode<T> next;

    public LinkNode() {

    }

    public LinkNode(T data) {
        this.data = data;
    }

    public LinkNode(T data, LinkNode next) {
        this.data = data;
        this.next = next;

    }

    @Override
    public String toString() {
        return "LinkNode{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}
