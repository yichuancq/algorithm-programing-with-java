package com.example.algorithm.liststudent.repository;

import com.example.algorithm.liststudent.base.Course;
import com.example.algorithm.liststudent.base.LinkNode;

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
            rear.next = new LinkNode(t, null);
            //移动指针
            rear = rear.next;
        }
    }

    /**
     * 添加结点
     *
     * @param course
     */
    public void add(Course course) {
        LinkNode rear = head;
        if (course == null) {
            System.out.println("添加对象为空");
            return;
        }
        while (rear != null) {
            if (rear.next == null) {
                rear.next = new LinkNode(course);
                return;
            }
            rear = rear.next;
        }
    }

    /**
     * 删除结点
     *
     * @param course
     */
    public void delete(Course course) {
        if (course == null) {
            System.out.println("对象为空");
            return;
        }
        LinkNode rear = head;
        for (; rear != null && rear.next != null; ) {
            //编号相同
            Course node = (Course) rear.next.data;
            if (course.curseNumber.equals(node.curseNumber)) {
                rear.next = rear.next.next;
                break;
            }
            rear = rear.next;
        }
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
     * 链表结点生成对象数组
     *
     * @return
     */
    public Course[] listToArrays() {
        int size = this.size();
        Course[] arrays = new Course[size];
        LinkNode p = head.next;
        int i = 0;
        while (p != null) {
            arrays[i] = (Course) p.data;
            i++;
            p = p.next;
        }
        return arrays;
    }

}
