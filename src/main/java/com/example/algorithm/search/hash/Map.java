package com.example.algorithm.search.hash;

/**
 * 自定义map
 *
 * @param <K>
 * @param <V>
 */
public interface Map<K, V> {

    /**
     * 判断是否空
     *
     * @return
     */
    public abstract boolean isEmpty();

    /**
     * 返回元素个数
     *
     * @return
     */
    public abstract int size();

    /**
     * 返回所有元素的描述字符串
     *
     * @return
     */
    public abstract String toString();

    /**
     * 返回关键字key映射的值
     *
     * @param key
     * @return
     */
    public abstract V get(K key);

    /**
     * 添加映射元素(键,值)，关键字相同时，替换值
     *
     * @param key
     * @param value
     * @return
     */
    public abstract V put(K key, V value);

    /**
     * 删除关键字为key元素，返回被删除元素的值
     *
     * @param key
     * @return
     */
    public abstract V remove(K key);

    /**
     * 判断是否包含关键字为key元素
     *
     * @param key
     * @return
     */
    public abstract boolean containsKey(K key);

    /**
     * 删除所有元素
     */
    public abstract void clear();

    /**
     * 返回包含值集合的数组，值可重复
     *
     * @return
     */
    public abstract Object[] values();

}
