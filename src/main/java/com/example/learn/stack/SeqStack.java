package com.example.learn.stack;


import com.example.learn.seqlist.SeqList;


/***
 * 顺序栈类，最终类，实现栈接口，T表示数据元素的数据类型
 * @param <T>
 */
public final class SeqStack<T> implements Stack<T> {
    //使用顺序表（第2章）存储栈元素
    private SeqList<T> list;

    /**
     * 构造容量为length的空栈
     *
     * @param length
     */
    public SeqStack(int length) {
        //执行顺序表构造方法
        this.list = new SeqList<T>(length);
    }

    /**
     * 构造默认容量的空栈
     */
    public SeqStack() {
        this(64);
    }

    /**
     * 判断栈是否空，若空返回true
     *
     * @return
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * 元素x入栈，空对象不能入栈
     *
     * @param x
     */
    public void push(T x) {
        //顺序表尾插入元素x，自动扩充容量
        this.list.insert(x);
    }

    /**
     * 返回栈顶元素（未出栈），若栈空返回null
     *
     * @return
     */
    public T peek() {
        //若栈空，get(i)返回null
        return this.list.get(list.size() - 1);
//        return this.isEmpty() ? null : this.list.get(list.size()-1);
    }

    /**
     * 出栈，返回栈顶元素；若栈空返回null
     *
     * @return
     */
    public T pop() {
        //若栈不空，顺序表尾删除，返回删除元素
        return this.list.remove(list.size() - 1);
//        return this.isEmpty() ? null : this.list.remove(list.size()-1); //若栈不空，顺序表尾删除，返回删除元素
    }

    /**
     * 返回栈所有元素的描述字符串，形式为“(,)”
     *
     * @return
     */
    public String toString() {
        return this.getClass().getName() + " " + this.list.toString();
    }
}

