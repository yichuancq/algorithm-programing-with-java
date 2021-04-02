package com.example.learn.liststudent.repository;

import com.example.learn.liststudent.base.Classes;
import com.example.learn.liststudent.base.LinkNode;

/**
 * @author yichuan
 * @param <T>
 */
public class ClassesRepository<T> {

    private LinkNode head;

    public ClassesRepository() {
        head = new LinkNode<>();
    }

    /**
     * 构造结点
     *
     * @param arrays
     */
    public ClassesRepository(T[] arrays) {
        this();
        LinkNode rear = head;
        for (T t : arrays) {
            rear.next = new LinkNode(t, null);
            rear = rear.next;
        }
    }

    public void add(T elementT) {
        LinkNode rear = head;
        if (elementT == null) {
            System.out.println("添加对象为空");
            return;
        }
        while (rear != null) {
            if (rear.next == null) {
                rear.next = new LinkNode(elementT);
                return;
            }
            rear = rear.next;
        }
    }

    /**
     * 删除结点
     *
     * @param elementT
     */
    public void delete(T elementT) {
        if (elementT == null) {
            System.out.println("对象为空");
            return;
        }
        LinkNode rear = head;
        for (; rear != null && rear.next != null; ) {
            //编号相同
            Classes classes = (Classes) elementT;
            Classes node = (Classes) rear.next.data;
            //班级编号相同
            if (classes.classesNumber.equals(node.classesNumber)) {
                rear.next = rear.next.next;
                break;
            }
            rear = rear.next;
        }
    }

    /**
     * 通过对象查找元素
     *
     * @param elementT
     * @return
     */
    public LinkNode<T> search(T elementT) {
        LinkNode p = head;
        if (elementT == null) {
            return null;
        }
        while (p != null && p.next != null) {
            //person
            if (elementT instanceof Classes) {
                Classes element = (Classes) elementT;
                Classes classes = (Classes) p.next.data;
                //班级编号相同
                if (element.classesNumber.equals(classes.classesNumber)) {
                    return p.next;
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
     * 打印结点信息
     */
    public void printNode() {
        LinkNode p = head.next;
        while (p != null) {
            p = p.next;
        }
    }

    /**
     * 链表结点生成对象数组
     *
     * @return
     */
    public Classes[] listToArrays() {
        int size = this.size();
        Classes[] arrays = new Classes[size];
        LinkNode p = head.next;
        int i = 0;
        while (p != null) {
            arrays[i] = (Classes) p.data;
            i++;
            p = p.next;
        }
        return arrays;
    }
}
