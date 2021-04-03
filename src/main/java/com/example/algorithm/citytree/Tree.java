package com.example.algorithm.citytree;

/***6.5.3树的父母孩子兄弟链表实现
 *  树类，树的父母孩子兄弟链存储结构,T指定结点的元素类型**/
public class Tree<T> {
    /**
     * 根结点，树的父母孩子兄弟链表结点
     */
    public TreeNode<T> root;

    /***构造空树
     *
     */
    public Tree() {
        this.root = null;
    }

    /**
     * 判断是否空树
     *
     * @return
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * 返回树的横向凹入表示字符串，以先根次序遍历树
     * 树的横向凹入表示法
     ***/
    @Override
    public String toString() {
        return "树的横向凹入表示： \n " + toString(root, "");
    }

    /***先根次序遍历以p为根的子树，tab指定缩进量，返回子树的横向凹入表示字符串，递归算法
     *
     * @param p
     * @param tab
     * @return
     */
    private String toString(TreeNode<T> p, String tab) {
        if (p == null) {
            return "";
        }
        //递归调用
        return tab + p.data.toString() + "\n" + toString(p.child, tab + "\t") + toString(p.sibling, tab);
    }

    /*** 以树的横向凹入表示构造一棵城市树（森林）树的先根和后根次序遍历算法**/
    public void preorder() {
        //输出树的先根次序遍历序列，算法同二叉树
        System.out.print("树的先根次序遍历序列：  ");
        preorder(root);
        System.out.println();
    }

    /***先根次序遍历以p为根的子树，递归算法
     *
     * @param p
     */
    private void preorder(TreeNode<T> p) {
        if (p != null) {
            System.out.print(p.data + " ");
            //递归调用
            preorder(p.child);
            preorder(p.sibling);
        }
    }

    /***输出树的后根次序遍历序列
     *
     */
    public void postorder() {
        System.out.print("树的后根次序遍历序列：  ");
        postorder(root);
        System.out.println();
    }

    /**
     * 后根次序遍历以p为根的子树，递归算法，算法同二叉树的中根次序遍历
     *
     * @param p
     */
    private void postorder(TreeNode<T> p) {
        if (p != null) {
            postorder(p.child);
            System.out.print(p.data + " ");
            postorder(p.sibling);
        }
    }

    /***返回树的结点个数，算法同二叉树
     *
     * @return
     */
    public int size() {
        return size(root);
    }

    /***返回以p结点为根的子树的结点个数
     *
     * @param p
     * @return
     */
    public int size(TreeNode<T> p) {
        if (p == null) {
            return 0;
        }
        return 1 + size(p.child) + size(p.sibling);
    }

    /**
     * 拷贝构造方法，算法同三叉链表存储的二叉树
     *
     * @param tree
     */
    public Tree(Tree<T> tree) {
        this.root = copy(tree.root, null);
    }

    /***深拷贝，复制以p根的子树，parent是复制子树的父母结点，返回新建子树的根结点
     *
     * @param p
     * @param parent
     * @return
     */
    public TreeNode<T> copy(TreeNode<T> p, TreeNode<T> parent) {
        if (p == null) {
            return null;
        }
        TreeNode<T> q = new TreeNode<T>(p.data, p.level, parent, null, null);
        //复制孩子子树，递归调用
        q.child = copy(p.child, q);
        //复制兄弟子树，递归调用
        q.sibling = copy(p.sibling, q);
        //返回建立子树的根结点
        return q;
    }

    /**
     * 插入元素x作为根结点，原根结点作为其孩子结点，返回插入结点
     *
     * @param x
     * @return
     */
    public TreeNode<T> insertRoot(T x) {
        this.root = new TreeNode<T>(x, 1, null, this.root, null);
        if (this.root.child != null) {
            this.root.child.parent = this.root;
            //设置以this.root.child为根子树的所有结点层次
            setLevel(this.root.child, this.root.level + 1);
        }
        return this.root;
    }

    /***设置以p结点为根子树的所有结点层次
     *
     * @param p
     * @param level
     */
    protected void setLevel(TreeNode<T> p, int level) {
        if (p != null) {
            p.level = level;
            //递归调用
            setLevel(p.child, level + 1);
            //递归调用
            setLevel(p.sibling, level);
        }
    }

    /**
     * 删除树的所有结点
     */
    public void clear() {
        this.root = null;
    }
}