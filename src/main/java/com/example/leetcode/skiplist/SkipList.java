package com.example.leetcode.skiplist;

import java.util.Random;

/**
 * skiplist
 * 跳跃表的构造特征：
 * <p>
 * 1、一个跳跃表应该有若干个层（Level）链表组成
 * 2、跳跃表中最底层的链表包含所有数据；每一层链表中的数据都是有序的
 * 2、如果一个元素 X 出现在第i层，那么编号比 i 小的层都包含元素 X
 * 4、第i层的元素通过一个指针指向下一层拥有相同值的元素
 * 5、在每一层中，-∞ 和 +∞ 两个元素都出现(分别表示 INT_MIN 和 INT_MAX)
 * 6、头指针（head）指向最高一层的第一个元素
 */
public class SkipList {
    public int level = 0;
    public SkipListNode top;

    public SkipList() {
        this(4);
    }

    /**
     * 初始化
     *
     * @param level
     */
    public SkipList(int level) {
        this.level = level;
        SkipListNode skipListNode = null;
        SkipListNode temp = top;
        SkipListNode tempDown = null;
        SkipListNode tempNextDown = null;
        int tempLevel = level;
        while (tempLevel-- != 0) {
            skipListNode = createNode(Integer.MIN_VALUE);
            temp = skipListNode;
            skipListNode = createNode(Integer.MAX_VALUE);
            temp.setNext(skipListNode);
            temp.setDownNext(tempDown);
            temp.getNext().setDownNext(tempNextDown);
            tempDown = temp;
            tempNextDown = temp.getNext();
            System.out.println("" + temp.toString());
        }
        top = temp;
    }

    /**
     * 随机产生数k，k层下的都需要将值插入
     */
    public int randomLevel() {
        int k = 1;
        while (new Random().nextInt() % 2 == 0) {
            k++;
        }
        return k > level ? level : k;
    }

    /**
     * 查找
     */
    public SkipListNode find(int value) {
        SkipListNode node = top;
        while (true) {
            while (node.getNext().getValue() < value) {
                node = node.getNext();
            }
            if (node.getDownNext() == null) {
                //返回要查找的节点的前一个节点
                return node;
            }
            node = node.getDownNext();
        }
    }

    /**
     * @param value
     * @return
     */
    public SkipListNode search(int value) {
        SkipListNode node = top;
        while (node != null && node.getNext() != null) {
            while (node.getNext().getValue() < value) {
                node = node.getNext();
            }
            if (node.getDownNext() == null) {
                //返回要查找的节点的前一个节点
                return node;
            }
            node = node.getDownNext();
        }
        return node;
    }


    /**
     * 删除一个节点
     */
    public boolean delete(int value) {
        int tempLevel = level;
        SkipListNode skipListNode = top;
        SkipListNode temp = skipListNode;
        boolean flag = false;
        while (tempLevel-- != 0) {
            while (temp.getNext().getValue() < value) {
                temp = temp.getNext();
            }
            if (temp.getNext().getValue() == value) {
                temp.setNext(temp.getNext().getNext());
                flag = true;
            }
            temp = skipListNode.getDownNext();
        }
        return flag;
    }

    /**
     * 插入一个节点
     *
     * @param value
     */
    public void insert(int value) {
        SkipListNode skipListNode = null;
        int k = randomLevel();
        SkipListNode temp = top;
        int tempLevel = level;
        SkipListNode tempNode = null;
        //当在第n行插入后，在第n - 1行插入时需要将第n行backTempNode的DownNext域指向第n - 1的域
        SkipListNode backTempNode = null;
        int flag = 1;
        while (tempLevel-- != k) {
            temp = temp.getDownNext();
        }
        tempLevel++;
        tempNode = temp;
        //小于k层的都需要进行插入
        while (tempLevel-- != 0) {
            //在第k层寻找要插入的位置
            while (tempNode.getNext().getValue() < value) {
                tempNode = tempNode.getNext();
            }
            skipListNode = createNode(value);
            //如果是顶层
            if (flag != 1) {
                backTempNode.setDownNext(skipListNode);
            }
            backTempNode = skipListNode;
            skipListNode.setNext(tempNode.getNext());
            tempNode.setNext(skipListNode);
            flag = 0;
            tempNode = tempNode.getDownNext();
        }
    }


    /***
     * 创建一个节点
     * @param value
     * @return
     */
    private SkipListNode createNode(int value) {
        SkipListNode node = new SkipListNode();
        node.setValue(value);
        return node;
    }

    public static void main(String[] args) {
        long startTime = 0;
        long endTime = 0;
        SkipList skipList = new SkipList(20);
        int size = 10000;
        //测试跳跃表性能
        startTime = System.currentTimeMillis();
        for (int i = 1; i < size; i++) {
            skipList.insert(i);
        }
        endTime = System.currentTimeMillis();
        System.out.printf("createSkipList:%d纳秒\n", endTime - startTime);
        startTime = System.currentTimeMillis();
        System.out.printf("find(555)：%d\n", skipList.find(999).getNext().getValue());
        System.out.printf("find(88)：%d\n", skipList.search(88).getNext().getValue());
        endTime = System.currentTimeMillis();
        System.out.printf("skipListFindTime:%d\n\n", endTime - startTime);

    }

}
