package com.example.learn.char5;

/**
 * @param <T>
 */
public class Tree<T extends Node<T>> {

    //根节点
    private Node<T> root;

    /**
     * @param node
     */
    public Tree(T node) {
        this.root = (Node<T>) node;
    }

    public void printRootNode() {
        System.out.println(this.root.toString());
    }

    /**
     * 访问顺序：左中右
     * 中序遍历（LDR）是二叉树遍历的一种，也叫做中根遍历、中序周游。
     * 在二叉树中，中序遍历首先遍历左子树，然后访问根结点，最后遍历右子树。
     */
    private void inorderTransversal() {
        System.out.println("中序遍历");
        if (this.root == null) {
            System.out.println("根结点为空");
            return;
        }
        this.inorder(this.root);
    }

    /**
     * 前序遍历
     * 访问顺序：根左右
     */
    private void preOderTransversal() {
        System.out.println("前序遍历");
        if (this.root == null) {
            System.out.println("根结点为空");
            return;
        }
        this.preOrder(this.root);
    }

    /**
     * 访问顺序：左右根
     */
    private void tailTransversal() {
        System.out.println("后序遍历");
        if (this.root == null) {
            System.out.println("根结点为空");
            return;
        }
        this.rightOrder(this.root);
    }

    /**
     * @param tNode
     */
    public void inorder(Node<T> tNode) {
        if (tNode == null) {
            return;
        }
        inorder(tNode.getLeftChild());
        //显示结点值
        System.out.println("" + tNode.toString());
        inorder(tNode.getRightChild());
    }

    /**
     * @param tNode
     */
    public void preOrder(Node<T> tNode) {
        if (tNode == null) {
            return;
        }

        //显示结点值
        System.out.println("" + tNode.toString());
        preOrder(tNode.getLeftChild());
        preOrder(tNode.getRightChild());

    }

    /**
     * @param tNode
     */
    public void rightOrder(Node<T> tNode) {
        if (tNode == null) {
            return;
        }
        //显示结点值
        rightOrder(tNode.getLeftChild());
        rightOrder(tNode.getRightChild());
        System.out.println("" + tNode.toString());
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        //添加一个根节点
        Node root = new Node<>("A");
        System.out.println("root:" + root.toString());
        //生成一个B结点
        Node node1 = new Node("B");
        //给B结点添加左子结点B
        node1.setLeftChild(new Node("D"));
        //给B添加右子结点E
        node1.setRightChild(new Node("E"));
        //添加新的结点
        Node node2 = new Node("C");
        //给B结点添加左子结点B
        node2.setLeftChild(new Node("F"));
        //给B添加右子结点E
        node2.setRightChild(new Node("G"));
        root.setLeftChild(node1);
        root.setRightChild(node2);

        Tree tree = new Tree(root);
        tree.printRootNode();
        tree.inorderTransversal();
        //前序遍历
        tree.preOderTransversal();
        //后续遍历
        tree.tailTransversal();

    }
}
