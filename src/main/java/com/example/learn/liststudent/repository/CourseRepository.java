package com.example.learn.liststudent.repository;

import com.example.learn.liststudent.base.Course;
import com.example.learn.liststudent.base.LinkNode;

/**
 * 课程链表
 *
 * @param <T>
 * @author yichuan
 */
public class CourseRepository<T> {

    public LinkNode head;

    public CourseRepository() {
        this.head = new LinkNode<T>();
    }

    /**
     * 构造链表
     *
     * @param arrays
     * @return
     */
    public CourseRepository(T[] arrays) {
        this();
        //指向头结点
        LinkNode rear = head;
        for (T t : arrays) {
            rear.data = t;
            rear.next = new LinkNode();
            //移动指针
            rear = rear.next;
        }
    }

    /**
     * 添加课程结点
     *
     * @param elementT
     */
    public void add(T elementT) {
    }

    /**
     * 删除结点
     *
     * @param index
     */
    public void delete(int index) {
    }

    /**
     * 按索引查找元素
     *
     * @param index
     * @return
     */
    public LinkNode<T> search(int index) {
        LinkNode p = head;
        for (int i = 0; p != null && p.next != null && i <= index; i++) {
            if (i == index) {
                return p;
            }
            p = p.next;
        }
        System.out.println("查找失败!");
        return null;
    }

    /**
     * 查询课程
     *
     * @param course
     * @return
     */
    public LinkNode<T> search(Course course) {
        LinkNode p = head;
        if (course == null) {
            return null;
        }
        while (p != null && p.next != null) {
            //课程对象
            Course temp = (Course) p.data;
            //学号相同
            if (course.curseNumber.equals(temp.curseNumber)) {
                System.out.println("eq");
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


}
