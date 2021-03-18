package com.example.learn.tree;

/**
 * 树的根节点
 *
 * @param <T>
 */
public class Node<T> {
    /**
     * 结点的值
     */
    private T data;
    /**
     * 左子结点
     */
    private Node<T> rightChild;
    /**
     * 右子结点
     */
    private Node<T> leftChild;


    public Node(T data) {
        this.data = data;
    }

    /**
     * @param data
     * @param leftChild
     * @param rightChild
     */
    public Node(T data, Node<T> leftChild, Node<T> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    @Override
    public String toString() {
        return String.valueOf(data);
    }
}

