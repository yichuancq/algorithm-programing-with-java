package com.example.learn.singlylist;

/**
 * 单链表
 */
public class SinglyList<T> {
    //链表的头结点
    public Node<T> head;
    //链表长度
    public int len = 0;

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
        //指向头结点
        Node<T> rear = this.head;
        for (int i = 0; i < vale.length; i++) {
            rear.next = new Node<T>(vale[i], null);
            //移动到下一个结点
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
            //size 累计
            size++;
            //指向下一个元素
            p = p.next;
        }
        this.len = size;
        return size;
    }

//（5）查找，散列表用
    //功能及参数：返回首个与key相等元素结点，若查找不成功返回null
    //特殊情况：若key为空对象，Java将抛出空对象异常
    //算法及效率：顺序查找，O(n)
    //用于7.2.2节图的邻接表，必须返回结点，因为要求后继结点。2014年8月6日，对其他影响未修改

    //顺序查找关键字为key元素，返回首次出现的元素，若查找不成功返回null
    //key可以只包含关键字数据项，由T类的equals()方法提供比较对象相等的依据
    public Node<T> search(T key) {
        for (Node<T> p = this.head.next; p != null; p = p.next)
            if (key.equals(p.data))                        //执行T类的equals(Object)方法，运行时多态
                return p;
        return null;
    }

    /**
     * 删除首个与key相等元素结点，返回被删除元素；查找不成功返回null。O(n)散列表用
     *
     * @param key
     * @return
     */
    public T remove(T key) {
        Node<T> front = this.head, p = front.next;
        //顺序查找首次出现的与key相等元素
        while (p != null && !key.equals(p.data)) {
            //front指向p的前驱结点
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

    //（4）删除

    /**
     * 删除第i个元素，0≤i<n，返回被删除元素；若i越界，返回null。O(n)
     *
     * @param i
     * @return
     */
    public T remove(int i) {
        //front指向头结点
        Node<T> front = this.head;
        //遍历寻找第i-1结点（front指向）
        for (int j = 0; front.next != null && j < i; j++)
            front = front.next;
        //若front的后继结点存在，则删除之
        if (i >= 0 && front.next != null) {
            //获得待删除结点引用的对象
            T old = front.next.data;
            //删除front的后继结点，包括头删除、中间/尾删除
            front.next = front.next.next;
            //由Java虚拟机稍后释放结点占用存储单元
            return old;
        }
        return null;                                       //若i<0或i>表长
//        throw new IndexOutOfBoundsException(i+"");       //抛出序号越界异常
    }

    /**
     * 尾插入互异元素x，若查找到与x的关键字相同元素，不插入，返回x结点；覆盖。//散列表用
     *
     * @param x
     * @return
     */
    public Node<T> insertDifferent(T x) {
        //front是p的前驱结点
        Node<T> front = this.head, p = front.next;
        //顺序查找
        while (p != null && !p.data.equals(x)) {
            front = p;
            p = p.next;
        }
        //查找成功，元素重复，不插入，返回p结点
        if (p != null) {
            System.out.println("x=" + x + "，元素重复，未插入。");
            return p;
        }
        return front.next = new Node<T>(x, null);           //尾插入值为x结点，返回插入结点
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
     * 清空链表
     */
    public void clear() {
        this.head.next = null;
    }

    /**
     * 遍历链表
     */
    private void showNodes() {
        Node<T> p = head;
        while (p != null) {
            //当数据不为空
            if (p.data != null) {
                System.out.println(p.toString());
            }
            //指向下一个元素
            p = p.next;
        }
    }

    /**
     * 递归显示所有元素
     *
     * @param p
     */
    private void getChildNode(Node<T> p) {
        if (p == null) {
            return;
        }
        //当数据不为空
        if (p.data != null) {
            System.out.println(p.toString());
        }
        p = p.next;
        //递归调用
        this.getChildNode(p);
    }

    /**
     * 中间插入元素
     *
     * @param index
     * @param data
     * @return
     */
    private Node<T> insert(int index, T data) {
        //front 指向头结点
        Node<T> front = this.head;
        //如果为空直接返回
        if (data == null) {
            return this.head;
        }
        for (int j = 0; front.next != null && j <= index; j++) {
            front = front.next;
        }
        //在front 后插入新的结点
        front.next = new Node<>(data, front.next);
        return front;
    }

    /**
     * 头部插入元素
     *
     * @param data
     * @return
     */
    private Node<T> insertHead(T data) {
        int i = Integer.MIN_VALUE;
        //front 指向头结点
        Node<T> front = this.head;
        //如果为空直接返回
        if (data == null) {
            return this.head;
        }
        for (int j = 0; front.next != null && j < i; j++) {
            front = front.next;
        }
        //在front 后插入新的结点
        front.next = new Node<>(data, front.next);
        return front;
    }

    /**
     * 插入到链表尾部
     *
     * @param data
     * @return
     */
    private Node<T> insertTail(T data) {
        Node<T> front = null;
        //如果插入元素为空
        if (data == null) {
            return this.head;
        }
        //遍历所有元素
        for (front = this.head; front != null; front = front.next) {
            //最后一个结点
            if (front.next == null) {
                //在front 后插入新的结点
                front.next = new Node<>(data, front.next);
                //返回
                break;
            }
        }
        return front;
    }


    /**
     * 打印元素
     *
     * @return
     */
    private String listToString() {
        StringBuffer stringBuffer = new StringBuffer();
        Node<T> p = head;
        stringBuffer.append("[");
        while (p != null) {
            if (p.data != null) {
                stringBuffer.append("," + p);
            }
            p = p.next;
        }
        stringBuffer.append("]");
        return stringBuffer.toString().replaceFirst(",", "");
    }

    /**
     * 打印元素
     *
     * @return
     */
    private String listToString2() {
        String string = "[";
        Node<T> p = head.next;
        for (; p != null; p = p.next) {
            string += p.data;
            //不是最后一个结点时
            if (p.next != null) {
                string += ',';
            }
        }
        string += ']';
        return string;
    }

    /**
     * 获取指定元素的索引
     *
     * @param data
     * @return
     */
    private int getElementIndex(T data) {
        int elementIndex = 0;
        Node<T> front = this.head;
        for (int index = 0; front != null; front = front.next) {
            if (front.data != null) {
                //元素匹配
                if (front.data.equals(data)) {
                    //获取相同元素的索引下标
                    elementIndex = index;
                    break;
                }
            }
            index += 1;
        }
        return elementIndex;
    }

    /**
     * @param data
     */
    private Node<T> removeElement(T data) {
        Node<T> front = this.head;
        for (; front != null; front = front.next) {
            if (front.data != null && front.next != null) {
                //元素匹配
                if (front.data.equals(data)) {
                    //移除front的后继
                    front = front.next;
                    break;
                }
            }
        }
        return front;
    }


    /**
     * 移除指定索引的元素，方式2
     *
     * @param index
     */
    private Node<T> removeElement2(int index) {
        Node<T> front = this.head;
        //遍历元素，且不为最后一个结点。且小于当前索引
        for (int j = 0; front != null && j <= index; front = front.next) {
            //当索引匹配则删除
            j++;
            if (j == index && front.next != null) {
                //移除front的后继
                front.next = front.next.next;
                break;
            }
        }
        return front;

    }


    /**
     * 移除指定索引的元素，方式1
     *
     * @param index
     */
    private Node<T> removeElement(int index) {
        Node<T> front = this.head;
        int j = 0;
        //遍历元素，且不为最后一个结点。且小于当前索引
        for (; front != null && j <= index; j++) {
            //当索引匹配则删除
            if (j == index && front.next != null) {
                //移除front的后继
                front.next = front.next.next;
                break;
            }
            //引用指向后一个元素
            front = front.next;
        }
        return front;

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
     * 链表反转
     * 1、把头结点指向最后一个元素。
     * 2、中间结点p.next=front
     *
     * @return
     */
    private Node<T> reverse2() {
        //p指向第0个结点
        Node<T> p = this.head;
        Node<T> front = null;
        for (; p != null; p = p.next) {
            //front 指向p的前驱
            Node<T> q = p.next;
            p.next = front;
            front = q;
            //如果是最后一个结点
            if (p.next == null) {
                //设置头结点的地址域指向原单链表的最后一个结点
                this.head.next = front;
                break;
            }
        }
        return this.head;
    }

    /**
     * 链表反转
     *
     * @return
     */
    private Node<T> reverse() {
        Node<T> p = this.head.next;
        Node<T> front = null;
        while (p != null) {
            Node<T> q = p.next;
            p.next = front;
            front = p;
            p = q;
        }
        //设置头结点的地址域指向原单链表的最后一个结点
        this.head.next = front;
        return this.head;
    }

    public static void main(String[] args) {
        String[] strings = {"a", "b", "c", "d", "e", "f", "g"};
        SinglyList singlyList = new SinglyList(strings);
        boolean isEmpty = singlyList.isEmpty();
//        Object data = singlyList.getElement(1);
//        System.out.println("data:" + data);
//        System.out.println("isEmpty:" + isEmpty);
//        int listSize = singlyList.singleListSize();
//        System.out.println("listSize:" + listSize);
//        System.out.println("遍历显示结点");
//        singlyList.showNodes();
//        System.out.println("替换元素");
//        singlyList.replaceElement(2, "cc");
//        System.out.println("遍历显示结点");
//        singlyList.showNodes();
//        System.out.println("递归显示结点");
//        singlyList.getChildNode(singlyList.head);
//        System.out.println("遍历打印元素方式1");
//        System.out.println(singlyList.listToString());
//        System.out.println("遍历打印元素方式2");
//        System.out.println(singlyList.listToString2());
//        System.out.println("插入结点元素");
//        //插入结点元素
//        singlyList.insertHead("ss");
//        System.out.println("遍历显示结点");
//        singlyList.showNodes();
//        System.out.println("遍历打印元素方式2");
//        //尾部插入
//        singlyList.insertTail("z");
//        System.out.println("遍历打印元素方式2");
//        System.out.println(singlyList.listToString2());
//        listSize = singlyList.singleListSize();
//        System.out.println("listSize:" + listSize);
//        //
//        singlyList.insert(2, "new");
//        System.out.println("遍历打印元素方式2");
//        System.out.println(singlyList.listToString2());
//        //移除指定索引的元素
//        System.out.println("删除结点元素");
//        singlyList.removeElement(3);
////        singlyList.removeElement2(3);
//        //
//        System.out.println("遍历打印元素方式2");
//        System.out.println(singlyList.listToString2());
//        //删除元素
//        System.out.println("删除匹配值相同的结点元素");
//        singlyList.removeElement("cc");
//        System.out.println("遍历打印元素方式2");
//        System.out.println(singlyList.listToString2());
//        //获取某个元素
//        int index = singlyList.getElementIndex("ss");
//        System.out.println("index" + index);
//        singlyList.removeElement2(index);
        System.out.println("遍历打印元素方式2");
        System.out.println(singlyList.listToString2());
        //反转链表
        System.out.println("反转链表");
        singlyList.reverse();
        System.out.println("遍历打印元素方式2");
        System.out.println(singlyList.listToString2());
        ////
        System.out.println("反转链表");
        singlyList.reverse2();
        System.out.println("遍历打印元素方式2");
        System.out.println(singlyList.listToString2());
        //清空链表
        singlyList.clear();
    }
}
