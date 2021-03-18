package com.example.learn.singlylist;

/**
 * 单循环链表
 *
 * @param <T>
 */
public class CircularSingleList<T> {

    private Node<T> head;

    /**
     *
     */
    public CircularSingleList() {
        this.head = new Node<>();
        this.head.next = head;
    }

    /**
     * @param vales
     */
    public CircularSingleList(T... vales) {
        this();
        //指向头结点
        Node<T> rear = this.head;
        for (T t : vales) {
            //指向头结点
            rear.next = new Node<T>(t, null);

            //移动到下一个结点
            rear = rear.next;
            //
            rear.next = head;
        }
    }

    /**
     * @return
     */
    private String printNodes() {
        Node<T> font = this.head.next;
        String str = "[";
        while (font != null && font.next != head) {
            if (font.data != null) {
                str += "," + font.data.toString();
            }
            font = font.next;
        }
        str += ']';
        return str.replaceFirst(",", "");
    }

    public static void main(String[] args) {
        String[] string = {"a", "b", "c", "d", "e", "f", "g"};
        CircularSingleList singleList = new CircularSingleList(string);
        System.out.println(singleList.printNodes());

    }
}
