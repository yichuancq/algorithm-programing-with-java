package com.example.algorithm.liststudent.repository;

import com.example.algorithm.liststudent.base.LinkNode;
import com.example.algorithm.liststudent.base.Person;
import com.example.algorithm.liststudent.base.Student;

/**
 * 数据链表
 *
 * @author yichuan
 * @param <T>
 */
public class PersonRepository<T> {
    /**
     * 头结点
     */
    public LinkNode head;

    /**
     * 构造函数
     */
    public PersonRepository() {
        head = new LinkNode<>();
    }

    /**
     * 构造链表
     *
     * @param arrays
     * @return
     */
    public PersonRepository(T[] arrays) {
        //指向头结点
        this();
        LinkNode rear = head;
        for (int i = 0; i < arrays.length; i++) {
            rear.next = new LinkNode<T>(arrays[i], null);
            //移动到下一个结点
            rear = rear.next;
        }
    }

    /**
     * 添加结点
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
    public T remove(int i) {
        //front指向头结点
        LinkNode<T> front = this.head;
        //遍历寻找第i-1结点（front指向）
        front = front.next;
        //若front的后继结点存在，则删除之
        for (int j = 0; front.next != null && j < i; j++) {
            if (i >= 0 && front.next != null) {
                //获得待删除结点引用的对象
                T old = front.next.data;
                //删除front的后继结点，包括头删除、中间/尾删除
                front.next = front.next.next;
                return old;
            }
        }
        return null;
    }

    /**
     * 删除首个与key相等元素结点，返回被删除元素；查找不成功返回null。O(n)散列表用
     *
     * @param
     * @return
     */
    public T remove(T key) {
        LinkNode<T> front = this.head, p = front.next;
        //顺序查找首次出现的与key相等元素
        //注意equals要比较字符串
        while (p != null && !key.toString().equals(p.data.toString())) {
            //front指向p的前驱结点
            System.out.println("" + p.data.toString());
            front = p;
            p = p.next;
        }
        //若查找成功，删除front的后继（p结点）
        if (p != null) {
            //包括头删除、中间/尾删除
            front.next = p.next;
            return p.data;
        }
        return null;
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
            Person element = (Person) elementT;
            Person person = (Person) rear.next.data;
            if (element.getNumber().equals(person.getNumber())) {
                rear.next = rear.next.next;
                break;
            }
            rear = rear.next;
        }
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
            if (elementT instanceof Person) {
                Person element = (Person) elementT;
                Person person = (Person) p.next.data;
                //学号相同
                if (element.getNumber().equals(person.getNumber())) {
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
    public Student[] listToArrays() {
        int size = this.size();
        Student[] arrays = new Student[size];
        LinkNode p = head.next;
        int i = 0;
        while (p != null) {
            arrays[i] = (Student) p.data;
            i++;
            p = p.next;
        }
        return arrays;
    }

}
