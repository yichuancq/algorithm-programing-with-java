package com.example.learn.char5;

/**
 * @param <T>
 */
public class Tree<T extends Node<T>> {
    //根节点
    private Node<T> root;
    //结点数目
    private int rootCount = 0;
    //树的高度
    private int treeHeight = 0;

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
     * 先序遍历
     * 访问顺序：左中右
     * 中序遍历（LDR）是二叉树遍历的一种，也叫做中根遍历、中序周游。
     * 在二叉树中，中序遍历首先遍历左子树，然后访问根结点，最后遍历右子树。
     */
    public void inorderTransversal() {
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
    public void preOderTransversal() {
        System.out.println("前序遍历");
        if (this.root == null) {
            System.out.println("根结点为空");
            return;
        }
        this.preOrder(this.root);
    }

    /**
     * 后序遍历
     * 访问顺序：左右根
     */
    public void postTransversal() {
        System.out.println("后序遍历");
        if (this.root == null) {
            System.out.println("根结点为空");
            return;
        }
        this.postOrder(this.root);
    }

    /**
     * @param tNode
     */
    private void inorder(Node<T> tNode) {
        if (tNode == null) {
            return;
        }
        inorder(tNode.getLeftChild());
        //显示结点值
        System.out.println("" + tNode.toString());
        inorder(tNode.getRightChild());
    }

    /**
     * 统计结点
     *
     * @param tNode
     */
    private void calcTreeCount(Node<T> tNode) {
        if (tNode == null) {
            return;
        }
        this.rootCount++;
        calcTreeCount(tNode.getLeftChild());
        calcTreeCount(tNode.getRightChild());
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
    private void postOrder(Node<T> tNode) {
        if (tNode == null) {
            return;
        }
        //显示结点值
        postOrder(tNode.getLeftChild());
        postOrder(tNode.getRightChild());
        System.out.println("" + tNode.toString());
    }

    /**
     * 清空树
     */
    void clearTree() {
        System.out.println("清空树");
        this.root = null;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        if (this.rootCount == 0) {
            return true;
        }
        return false;
    }

    /**
     * 树结点树
     *
     * @return
     */
    public int size() {
        //计数结点
        this.calcTreeCount(this.root);
        return this.rootCount;
    }

    /**
     * 是否包含某个元素的
     *
     * @param key
     * @return
     */
    private boolean contains(String key) {
        //遍历树
        return this.findKey(this.root, key);

    }

    /**
     * 查找关键字匹配的结点
     *
     * @param tNode
     * @param key
     * @return
     */
    private boolean findKey(Node<T> tNode, String key) {
        boolean flag;
        if (tNode == null) {
            return false;
        }
        flag = findKey(tNode.getLeftChild(), key);
        int len = key.compareTo(tNode.toString());
        //显示结点值
        if (len == 0 && !flag) {
            flag = true;
        } else {
            if (!flag) {
                flag = findKey(tNode.getRightChild(), key);
            }
        }
        return flag;
    }

    /**
     * 删除关键字的结点
     *
     * @param
     * @param key
     */
    private void removeByKey(T key) {

    }

    /**
     * 获取树的高度
     *
     * @return
     */
    private int treeHeight() {
        //
        return this.treeHeight;
    }
    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    public int getRootCount() {
        return rootCount;
    }

    public void setRootCount(int rootCount) {
        this.rootCount = rootCount;
    }

    public int getTreeHeight() {
        return treeHeight;
    }

    public void setTreeHeight(int treeHeight) {
        this.treeHeight = treeHeight;
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
//        tree.printRootNode();
        tree.inorderTransversal();
//        //前序遍历
//        tree.preOderTransversal();
//        //后续遍历
//        tree.tailTransversal();
        //
        boolean hasElement = tree.contains("B");
        System.out.println("是否找到结点:" + hasElement);
        hasElement = tree.contains("E");
        System.out.println("是否找到结点:" + hasElement);
        hasElement = tree.contains("S");
        System.out.println("是否找到结点:" + hasElement);
        //移除结点
        int size = tree.size();
        System.out.println("树结点数目:" + size);
        // tree.removeByKey(new Node("G"));
        boolean isEmpty = tree.isEmpty();
        System.out.println("tree is empty:" + isEmpty);

        //树的高度
        int height = tree.treeHeight();
        System.out.println("树的高度:" + height);
        tree.clearTree();
    }


}
