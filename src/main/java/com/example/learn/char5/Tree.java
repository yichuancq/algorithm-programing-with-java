package com.example.learn.char5;

/**
 * @param <T>
 */
public class Tree<T> {
    //根节点
    private Node<T> root;
    //结点数目
    private int rootCount = 0;
    //树的高度
    private int treeHeight = 0;
    //
    private int i = 0;

    /**
     * @param node
     */
    public Tree(T node) {
        this.root = (Node<T>) node;
    }

    /**
     * 构造方法
     */
    public Tree(T[] values) {
        this.root = this.createTree(values);
    }

    /**
     * 创建树
     *
     * @param preList
     * @return
     */
    public Node<T> createTree(T... preList) {
        Node<T> p = null;
        if (i < preList.length) {
            T element = preList[i];
            i++;
            if (element != null) {
                p = new Node<T>(element);
                //设置左孩子
                p.setLeftChild(createTree(preList));
                //设置右孩子
                p.setRightChild(createTree(preList));
            }
        }
        return p;
    }

    /**
     * 插入
     *
     * @param t
     * @return
     */
    public void insert(T t, boolean isLeftChild) {
        this.insert(this.root, t, isLeftChild);
    }

    /**
     * @param parent
     * @param x
     * @param isLeftChild
     */
    public void insert(Node<T> parent, T x, boolean isLeftChild) {
        if (x == null) {
            return;
        }
        if (isLeftChild) {
            parent.setLeftChild(new Node(x, parent.getLeftChild(), null));
        } else {
            parent.setRightChild(new Node(x, null, parent.getRightChild()));
        }
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
        if (this.root == null) {
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
     * 递归查找
     *
     * @param parent
     * @param searchNode
     * @return
     */
    public Node<T> findNode(Node<T> parent, Node<T> searchNode) {
        if (searchNode == null || parent == null) {
            return null;
        }
        //显示结点值
        boolean flag = parent.getData().equals(searchNode.getData());
        if (flag) {
            return parent;
        } else {
            //递归
            if (parent.getLeftChild() != null) {
                parent = findNode(parent.getLeftChild(), searchNode);
            }
            if (parent.getRightChild() != null) {
                parent = findNode(parent.getRightChild(), searchNode);
            }
        }
        return parent;
    }


    /**
     * 删除结点
     *
     * @param parent
     * @param isLeftChild
     */
    public void remove(Node<T> parent, boolean isLeftChild) {
        System.out.println("移除结点" + parent.toString());
        if (parent == null) {
            return;
        }
        if (isLeftChild) {
            //置空左孩子结点
            parent.setLeftChild(null);
        } else {
            //置空右孩子结点
            parent.setRightChild(null);
        }
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

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] preList = {"A", "B", "D", null, "G", null, null, null, "C", "E", null, null, "F", "H"};
        Tree<String> tree = new Tree<>(preList);
        //中根遍历
        tree.inorderTransversal();
//        //前根遍历
//        tree.preOderTransversal();
//        //后根遍历
//        tree.postTransversal();

        System.out.println("插入结点");
//        tree.insert("X", true);
        tree.insert(tree.root.getLeftChild(), "X", true);
        tree.insert(tree.root.getRightChild(), "Y", false);
        //中根遍历
        tree.inorderTransversal();
//        //前根遍历
        tree.preOderTransversal();
//        //后根遍历
        tree.postTransversal();
        //删除结点

        Node target = tree.findNode(tree.root, new Node<>("A"));

        tree.remove(target, true);
        //
        System.out.println("" + target);
        tree.inorderTransversal();


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
