package com.example.learn.char5;

/**
 * 树的根节点
 *
 * @param <T>
 */
public class Node<T> extends Object {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public Node(T data) {
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

