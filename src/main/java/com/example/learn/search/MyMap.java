package com.example.learn.search;

public interface MyMap<K, V> {
    /**
     * 存入元素
     *
     * @param k key
     * @param v value
     * @return key
     */
    V put(K k, V v);

    /**
     * 通过key移除元素
     *
     * @param k key
     * @return value
     */
    V remove(K k);

    /**
     * 通过key获取value
     *
     * @param k key
     * @return value
     */
    V get(K k);

    /**
     * 是否包含该key
     *
     * @param k key
     * @return boolean
     */
    boolean containsKey(K k);

    /**
     * 是否包含该value
     *
     * @param v value
     * @return boolean
     */
    boolean containsValue(V v);

    /**
     * 元素个数
     *
     * @return 元素个数
     */
    int size();

    /**
     * hash表是否为空
     *
     * @return boolean
     */
    boolean isEmpty();

    /**
     * 清空hash表
     *
     * @return 成功或者失败
     */
    void clear();

    /**
     * 键值对类型，表示一个节点
     *
     * @param <K>
     * @param <V>
     */
    class EntryNode<K, V> {
        K key;
        V value;
        EntryNode<K, V> next;

        EntryNode(K key, V value) {
            assert key != null;
            this.key = key;
            this.value = value;
        }

        /**
         * 判断传入的key与对象的key是否相等
         *
         * @param k key
         * @return boolean
         */
        boolean equalsKey(K k) {
            return this.key.equals(k);
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public EntryNode<K, V> getNext() {
            return next;
        }

        public void setNext(EntryNode<K, V> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

}
