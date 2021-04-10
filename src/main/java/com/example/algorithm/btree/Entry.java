package com.example.algorithm.btree;

import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 关键字key value
 * </p>
 *
 * @author yichuan
 */

public class Entry<K extends Comparable<K>, V> implements Map.Entry<K, V> {
    public K key;
    public V value;

    public Entry() {
        super();
    }

    public Entry(K key, V value) {
        super();
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    /**
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return this == null;
        }
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry<K, V> entry = (Entry<K, V>) obj;
        return Objects.equals(this.key, entry.key) && Objects.equals(this.value, entry.value);
    }

    @Override
    public String toString() {
        return "Entry{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}