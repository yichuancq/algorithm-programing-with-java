package com.example.learn.char5;

public class BinaryTree<T extends Node<T>> extends Tree<T> {
    /**
     * @param node
     */
    public BinaryTree(T node) {
        super(node);
    }

    /**
     * 插入结点t作为根节点,并返回结点
     *
     * @param t
     * @return
     */
    public BinaryTree<T> insert(T t) {
        return this;
    }

    /**
     * 插入x 作为P的左/右孩子结点并返回
     *
     * @param p
     * @param x
     * @param isLeftChild
     * @return
     */
    public BinaryTree<T> insert(BinaryTree<T> p, T x, boolean isLeftChild) {
        return this;
    }

    /**
     * 移除结点
     *
     * @param parent
     * @param isLeftChild
     */
    public void remove(BinaryTree<T> parent, boolean isLeftChild) {

    }

    /**
     * 查找结点
     *
     * @param key
     * @return
     */
    public BinaryTree<T> search(T key) {
        return this;
    }

    /**
     * 前序遍历
     */
    @Override
    public void inorderTransversal() {
        super.inorderTransversal();
    }

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
        BinaryTree binaryTree = new BinaryTree(root);
        //
        //树的高度
        int size = binaryTree.size();
        System.out.println("树结点数目:" + size);
        binaryTree.inorderTransversal();

    }

}
