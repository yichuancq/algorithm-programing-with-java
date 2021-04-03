package com.example.algorithm.binarytree;

import org.junit.Test;

/**
 * @author yichuan
 */
public class BinarySortTreeTest {

    /**
     * 产生n个互异的排序的随机数，范围是0〜size－1，返回二叉排序树
     *
     * @param n
     * @param size
     * @return
     */
    public BinarySortTree<Integer> random(int n, int size) {
        //二叉排序树存储集合元素
        BinarySortTree<Integer> set = new BinarySortTree<Integer>();
        int i = 0;
        while (i < n) {
            int key = (int) (Math.random() * size);
            System.out.print(key + " ");
            //添加一个随机数到二叉排序树成功
            if (set.add(key)) {
                i++;
            }
        }
        //返回二叉排序树
        return set;
    }

    /**
     * 二叉排序树的插入、查找操作
     */
    @Test
    public void test() {
        System.out.print("关键字序列：");
        BinarySortTree<Integer> binarySortTree = random(10, 100);
        //中根次序遍历二叉排序树，输出按关键字升序排列的元素序列
        System.out.println("\n二叉排序树：" + binarySortTree.toString());
        binarySortTree.inorderPrevious();
        //输出平均查找长度ASL（查找成功）及计算公式，二叉树的层次遍历算法
        binarySortTree.printASL();
    }
}
