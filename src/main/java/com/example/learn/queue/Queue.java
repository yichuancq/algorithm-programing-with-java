package com.example.learn.queue;

/**
 * 队列接口，描述队列抽象数据类型，T表示数据元素的数据类型
 *
 * @param <T>
 */
public interface Queue<T> {
    /**
     * 判断队列是否空
     *
     * @return
     */
    public abstract boolean isEmpty();

    /**
     * 元素x入队，若添加成功，则返回true；否则返回false
     *
     * @param x
     * @return
     */

    public abstract boolean add(T x);

    /**
     * 返回队头元素，没有删除。若队列空，则返回null
     *
     * @return
     */
    public abstract T peek();

    /**
     * 出队，返回队头元素。若队列空，则返回null
     *
     * @return
     */
    public abstract T poll();
}