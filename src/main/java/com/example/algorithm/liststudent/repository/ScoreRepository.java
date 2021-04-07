package com.example.algorithm.liststudent.repository;

import com.example.algorithm.liststudent.base.LinkNode;
import com.example.algorithm.liststudent.base.Score;

/**
 * 成绩信息
 */
public class ScoreRepository<T> {

    public LinkNode head;

    public ScoreRepository() {
        this.head = new LinkNode<T>();
    }

    /**
     * 构造链表
     *
     * @param arrays
     * @return
     */
    public ScoreRepository(T[] arrays) {
        this();
        //指向头结点
        LinkNode rear = head;
        for (T t : arrays) {
            rear.next = new LinkNode(t, null);
            //移动指针
            rear = rear.next;
        }
    }

    /**
     * 添加结点
     *
     * @param score
     */
    public void add(Score score) {
        LinkNode rear = head;
        if (score == null) {
            System.out.println("添加对象为空");
            return;
        }
        while (rear != null) {
            if (rear.next == null) {
                rear.next = new LinkNode(score);
                return;
            }
            rear = rear.next;
        }
    }

    /**
     * 删除结点
     *
     * @param score
     */
    public void delete(Score score) {
        if (score == null) {
            System.out.println("对象为空");
            return;
        }
        LinkNode rear = head;
        for (; rear != null && rear.next != null; ) {
            //Score
            Score temp = (Score) rear.next.data;
            if (score.scNumber.equals(temp.score)) {
                rear.next = rear.next.next;
                break;
            }
            rear = rear.next;
        }
    }

    /**
     * 查询课程
     *
     * @param score
     * @return
     */
    public Score search(Score score) {
        LinkNode p = head;
        if (score == null) {
            return null;
        }
        while (p != null && p.next != null) {
            //课程对象
            if (p.data != null) {
                Score temp = (Score) p.data;
                //学号相同
                if (score.scNumber.equals(temp.scNumber)) {
                    return (Score) p.data;
                }
            }

            p = p.next;
        }
        return null;
    }

    /**
     * 链表的长度
     *
     * @return
     */
    public int size() {
        int length = 0;
        LinkNode p = head;
        while (p != null && p.next != null) {
            length++;
            p = p.next;
        }
        return length;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        int size = this.size();
        if (size <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 链表结点生成对象数组
     *
     * @return
     */
    public Score[] listToArrays() {
        int size = this.size();
        Score[] arrays = new Score[size];
        LinkNode p = head.next;
        int i = 0;
        while (p != null) {
            arrays[i] = (Score) p.data;
            i++;
            p = p.next;
        }
        return arrays;
    }
}
