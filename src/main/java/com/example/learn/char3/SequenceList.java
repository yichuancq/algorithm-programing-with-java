package com.example.learn.char3;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 线性表
 */
public class SequenceList<T> {
    private Object[] elementData;
    //默认大小
    private int defaultSize = 0;
    //数组大小
    private int size = 0;
    //数组容量
    private int capacity;

    /**
     * 初始化
     */
    public SequenceList() {
        capacity = this.defaultSize;
        elementData = new Object[capacity];
    }

    public SequenceList(T element) {
        this();
        elementData[0] = element;
        size++;
    }

    /**
     * 返回线性表的长度
     *
     * @return
     */
    public int length() {
        return this.size;
    }

    /**
     * 根据索引返回元素
     *
     * @param index
     * @return
     * @throws Exception
     */
    private T getElementByIndex(final int index) throws Exception {
        if (this.size == 0) {
            return null;
        }
        if (index < 0 || index > size - 1) {
            throw new Exception("索引超出范围");
        }
        return (T) elementData[index];
    }

    /**
     * 删除元素
     *
     * @param index
     * @throws Exception
     */
    private void delete(final int index) throws Exception {
        if (this.size == 0) {
            return;
        }
        if (index < 0 || index > size - 1) {
            throw new Exception("索引超出范围");
        }
        for (int j = index; j < size - 1; j++) {
            elementData[j] = elementData[j + 1];
        }
        size--;
    }

    /**
     * 显示所有元素
     */
    public void printElement() {
        for (int i = 0; i < size; i++) {
            System.out.println("" + elementData[i]);
        }
        System.out.print("\n");
    }

    /**
     * @param element
     */
    public void add(T element) {
        ensureCapacity();
        elementData[size] = element;
        size++;
    }

    /**
     * 扩充线性表容量
     */
    public void ensureCapacity() {
        while ((size + 1) > capacity) {
            capacity = capacity + 1;
            elementData = Arrays.copyOf(elementData, capacity);
        }
    }

    public static void main(String[] args) throws Exception {
        SequenceList sequenceList = new SequenceList();
        sequenceList.add("a");
        sequenceList.add("this ");
        sequenceList.add("java");
        sequenceList.add(12);
        sequenceList.add(11);
        sequenceList.add(1.34);
        sequenceList.add(new BigDecimal(23.23f));
        Person person = new Person("xiaoming", 34);
        sequenceList.add(person);
        sequenceList.printElement();

        System.out.println("获取指定的元素");
        Object element = sequenceList.getElementByIndex(1);
        System.out.println("" + element);
        //
        int len = sequenceList.length();
        System.out.println("元素数量:" + len);
        System.out.println("删除指定索引的元素");
        sequenceList.delete(2);
        len = sequenceList.length();
        System.out.println("元素数量:" + len);
        sequenceList.printElement();

    }
}
