package com.example.learn.huffmantree;

/**
 * 二叉树的静态三叉链表结点类
 */
public class TriElement {
    /**
     * 数据域
     */
    int data;
    /**
     * 父母结点和左、右孩子结点下标
     */
    int parent, left, right;

    /**
     * 构造结点，各参数依次指定元素、父母结点下标、左/右孩子结点下标
     *
     * @param data
     * @param parent
     * @param left
     * @param right
     */
    public TriElement(int data, int parent, int left, int right) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    /**
     * 构造元素值为data、无父母的叶子结点
     *
     * @param data
     */
    public TriElement(int data) {
        this(data, -1, -1, -1);
    }

    /**
     * 返回结点的描述字符串
     *
     * @return
     */
    public String toString() {
        return "(" + this.data + "," + this.parent + "," + this.left + "," + this.right + ")";
    }

    /**
     * 判断是否叶子结点
     */
    public boolean isLeaf() {
        return this.left == -1 && this.right == -1;
    }
    /* 以下没有用到
        public TriElement()
        {
            this(0, -1, -1, -1);
        }
        public boolean equals(Object obj)                      //比较是否相等 ，覆盖Object类的equals(obj)方法
        {                                                      //仅比较元素值
            return obj==this || obj instanceof TriElement && this.data==((TriElement)obj).data;
        }
    }
    */
}
