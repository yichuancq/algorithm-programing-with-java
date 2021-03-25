package com.example.learn.polynomial;

/**
 * 可相加接口，T表示数据元素的数据类型
 *
 * @param <T>
 */
public interface Addible<T> {
    /**
     * +=加法，约定两元素相加规则
     *
     * @param t
     */
    public void add(T t);

    /**
     * 约定删除元素条件
     *
     * @return
     */
    public boolean removable();
}
