package com.example.algorithm.citytree;

/***6.5.3   树的父母孩子兄弟链表实现
 *树的父母孩子兄弟链表结点类，T指定结点的元素类型
 //1. 树的父母孩子兄弟链表结点类
 * @author yichuan**/
public class TreeNode<T> {
    /**
     * 数据域
     **/
    public T data;
    /**
     * 父母结点链、孩子结点链、兄弟结点链
     **/
    public TreeNode<T> parent, child, sibling;
    //结点层次
    public int level;

    /***构造结点，参数分别指定元素、结点层次、父母结点、孩子结点和兄弟结点
     *
     * @param data
     * @param level
     * @param parent
     * @param child
     * @param sibling
     */
    public TreeNode(T data, int level, TreeNode<T> parent, TreeNode<T> child, TreeNode<T> sibling) {
        this.data = data;
        this.level = level;
        this.parent = parent;
        this.child = child;
        this.sibling = sibling;
    }

    /**
     * 构造指定值和层次的叶子结点
     *
     * @param data
     * @param level
     */
    public TreeNode(T data, int level) {
        this(data, level, null, null, null);
    }

    @Override
    public String toString() {
        return this.data.toString();
    }

    /**
     * 判断是否叶子结点
     *
     * @return
     */
    public boolean isLeaf() {
        return this.child == null;
    }
}