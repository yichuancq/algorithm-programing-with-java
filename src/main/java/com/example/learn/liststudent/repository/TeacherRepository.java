package com.example.learn.liststudent.repository;

import com.example.learn.liststudent.base.LinkNode;
import com.example.learn.liststudent.base.Teacher;

/**
 * 教师链表
 *
 * @param <T>
 */
public class TeacherRepository<T> {

    public LinkNode head;

    public TeacherRepository() {
        this.head = new LinkNode<>();
    }

    /**
     * 构造链表
     *
     * @param arrays
     * @return
     */
    public TeacherRepository(T[] arrays) {
        this();
        //指向头结点
        LinkNode rear = head;
        for (T t : arrays) {
            rear.next = new LinkNode(t, null);
            rear = rear.next;
        }
    }


    /**
     * 删除结点
     *
     * @param teacher
     */
    public void delete(Teacher teacher) {
        if (teacher == null) {
            System.out.println("对象为空");
            return;
        }
        LinkNode rear = head;
        for (; rear != null && rear.next != null; ) {
            //编号相同
            Teacher node = (Teacher) rear.next.data;
            if (teacher.getNumber().equals(node.getNumber())) {
                rear.next = rear.next.next;
                break;
            }
            rear = rear.next;
        }
    }


    /**
     * 添加结点
     *
     * @param teacher
     */
    public void add(Teacher teacher) {
        LinkNode rear = head;
        if (teacher == null) {
            System.out.println("添加对象为空");
            return;
        }
        while (rear != null) {
            if (rear.next == null) {
                rear.next = new LinkNode(teacher);
                return;
            }
            rear = rear.next;
        }
    }


    /**
     * @param target
     * @return
     */
    public LinkNode<T> search(Teacher target) {
        LinkNode p = head;
        if (target == null) {
            return null;
        }
        while (p != null && p.next != null) {
            //课程对象
            Teacher temp = (Teacher) p.data;
            //number相同
            if (target.getNumber().equals(temp.getNumber())) {
                return p;
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
     * 打印链表
     */
    public void printNode() {
        LinkNode p = head;
        while (p != null) {
            p = p.next;
        }
    }

    /**
     * 链表结点生成对象数组
     *
     * @return
     */
    public Teacher[] listToArrays() {
        int size = this.size();
        Teacher[] arrays = new Teacher[size];
        LinkNode p = head.next;
        int i = 0;
        while (p != null) {
            arrays[i] = (Teacher) p.data;
            i++;
            p = p.next;
        }
        return arrays;
    }
}
