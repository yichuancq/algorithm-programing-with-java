package com.example.algorithm.citytree;

/**
 * 树抽象数据类型，T表示结点元素类型
 * 树接口
 *
 * @author yichuan
 * @param <T>
 */
public interface TTree<T> {
    /**
     * 判断是否空树
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 返回关键字为key结点所在的层次
     *
     * @param key
     * @return
     */
    int level(T key);

    /**
     * 返回树的结点数
     *
     * @return
     */
    int size();

    /**
     * 返回树的高度
     *
     * @return
     */
    int height();

    /**
     * 输出树的先根次序遍历序列
     */
    void preorder();

    /***输出树的后根次序遍历序列
     *
     */
    void postorder();

    /**
     * 输出树的层次遍历序列
     */
    void levelorder();

    /**
     * 插入元素x作为根结点并返回
     *
     * @param x
     * @return
     */
    TreeNode<T> insertRoot(T x);

    /**
     * 插入x作为p结点的第i（≥0）个孩子
     *
     * @param p
     * @param x
     * @param i
     * @return
     */
    TreeNode<T> insertChild(TreeNode<T> p, T x, int i);

    /**
     * 删除p结点的第i（≥0）棵子树
     *
     * @param p
     * @param i
     */
    void remove(TreeNode<T> p, int i);

    /**
     * 删除树的所有结点
     */
    void clear();

    /**
     * 查找并返回关键字为key的结点
     *
     * @param key
     * @return
     */
    TreeNode<T> search(T key);

    /**
     * 判断是否包含关键字为key元素
     *
     * @param key
     * @return
     */
    boolean contains(T key);

    /***删除以key结点为根的子树
     *
     * @param key
     * @return
     */
    T remove(T key);
}