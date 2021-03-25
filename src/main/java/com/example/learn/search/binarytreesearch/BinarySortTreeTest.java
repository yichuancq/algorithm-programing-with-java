package com.example.learn.search.binarytreesearch;

import com.example.learn.tree.binarytree.BinarySortTree;
import org.junit.Test;

public class BinarySortTreeTest {

    /**
     * 产生n个互异的排序的随机数，范围是0〜size－1，返回二叉排序树
     *
     * @param n
     * @param size
     * @return
     */
    public BinarySortTree<Integer> random(int n, int size) {
        BinarySortTree<Integer> set = new BinarySortTree<Integer>(); //二叉排序树存储集合元素
        int i = 0;
        while (i < n) {
            int key = (int) (Math.random() * size);
            System.out.print(key + " ");
            if (set.add(key))                    //添加一个随机数到二叉排序树成功
                i++;
        }
        return set;                              //返回二叉排序树
    }
    //不能调用addAll([])，因为要计数。

    /**
     * 二叉排序树的插入、查找操作
     */
    @Test
    public void test() {
        System.out.print("关键字序列：");
        BinarySortTree<Integer> set = random(10, 100);
        System.out.println("\n二叉排序树：" + set.toString());  //中根次序遍历二叉排序树，输出按关键字升序排列的元素序列
        set.inorderPrevious();
    }
}
