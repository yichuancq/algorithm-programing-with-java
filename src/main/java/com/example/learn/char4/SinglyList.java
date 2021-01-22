package com.example.learn.char4;

/**
 * 单链表
 */
public class SinglyList<T> {

    public Node<T> head;

    /**
     *
     */
    public SinglyList() {
        this.head = new Node<T>();
    }

    /**
     * 用数组构造链表
     *
     * @param vale
     */
    public SinglyList(T... vale) {
        this();
        Node<T> rear = this.head;
        for (int i = 0; i < vale.length; i++) {
            rear.next = new Node<T>(vale[i], null);
            rear = rear.next;
        }
    }


    /**
     * 是否为空
     *
     * @return
     */
    private boolean isEmpty() {
        return head.next == null;
    }

    /**
     * @return
     */
    private int singleListSize() {
        int size = 0;
        //头节点不算长度
        Node<T> p = head.next;
        while (p != null) {
            size++;
            p = p.next;
        }
        return size;
    }

    /***
     * 替换元素
     * @param index
     * @param data
     */
    private void replaceElement(int index, T data) {
        Node<T> p = head.next;
        for (int j = 0; p != null && j <= index; j++) {
            //当索引匹配则替换元素
            if (j == index) {
                p.data = data;
            }
            //引用指向后一个元素
            p = p.next;
        }

    }

    /**
     * 获取元素
     *
     * @param index
     * @return
     */
    private T getElement(int index) {
        Node<T> p = head.next;
        for (int j = 0; p != null && j < index; j++) {
            p = p.next;
        }
        if (index >= 0 && p != null) {
            return p.data;
        }
        return null;
    }

    /**
     * 遍历链表
     */
    private void showNodes() {
        Node<T> p = head;
        while (p != null) {
            if (p.data != null) {
                System.out.println(p.toString());
            }
            p = p.next;
        }
    }

    public static void main(String[] args) {
        String[] strings = {"a", "b", "c", "d", "e", "f"};
        SinglyList singlyList = new SinglyList(strings);
        boolean isEmpty = singlyList.isEmpty();
        Object data = singlyList.getElement(1);
        System.out.println("data:" + data);
        System.out.println("isEmpty:" + isEmpty);
        int listSize = singlyList.singleListSize();
        System.out.println("listSize:" + listSize);
        System.out.println("遍历显示结点");
        singlyList.showNodes();
        System.out.println("替换元素");
        singlyList.replaceElement(2, "cc");
        System.out.println("遍历显示结点");
        singlyList.showNodes();
    }
}
