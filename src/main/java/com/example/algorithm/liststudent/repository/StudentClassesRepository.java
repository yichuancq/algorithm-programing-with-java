package com.example.algorithm.liststudent.repository;

import com.example.algorithm.liststudent.base.LinkNode;
import com.example.algorithm.liststudent.base.StudentClasses;

/**
 * 学生课程信息链表
 *
 * @param <T>
 * @author yichuan
 */
public class StudentClassesRepository<T> {


    private LinkNode head;

    public StudentClassesRepository() {
        head = new LinkNode<>();
    }

    /**
     * 构造结点
     *
     * @param arrays
     */
    public StudentClassesRepository(T[] arrays) {
        this();
        LinkNode rear = head;
        for (T t : arrays) {
            rear.next = new LinkNode(t, null);
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
            StudentClasses element = (StudentClasses) elementT;
            StudentClasses classes = (StudentClasses) rear.next.data;
            //编号匹配
            if (element.scNumber.equals(classes.scNumber)) {
                rear.next = rear.next.next;
                break;
            }
            rear = rear.next;
        }
    }

    /**
     * 添加
     *
     * @param elementT
     */
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
     * 通过对象查找元素
     *
     * @param target
     * @return
     */
    public StudentClasses search(StudentClasses target) {
        LinkNode p = head;
        if (target == null) {
            return null;
        }
        while (p != null && p.next != null) {
            //person
            StudentClasses temp = (StudentClasses) p.next.data;
            //学生编码相同
            if (temp.stuNumber.equals(target.stuNumber)) {
                return (StudentClasses) p.next.data;
            }
            p = p.next;
        }
        return null;
    }


    /**
     * 链表结点生成对象数组
     *
     * @return
     */
    public StudentClasses[] listToArrays() {
        int size = this.size();
        StudentClasses[] arrays = new StudentClasses[size];
        LinkNode p = head.next;
        int i = 0;
        while (p != null) {
            arrays[i] = (StudentClasses) p.data;
            i++;
            p = p.next;
        }
        return arrays;
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
}
