package com.example.algorithm.btree;

/**
 * <p>
 * 搜索结果
 * </p>
 *
 * @author yichuan
 */

public class BPair<K extends Comparable<K>, V> {
    public BTree.Node node;
    public Entry<K, V> key;

    public BPair() {
    }

    public BPair(BTree.Node node, Entry<K, V> key) {
        this.node = node;
        this.key = key;
    }

    public BTree.Node getNode() {
        return node;
    }

    public void setNode(BTree.Node node) {
        this.node = node;
    }

    public Entry<K, V> getKey() {
        return key;
    }

    public void setKey(Entry<K, V> key) {
        this.key = key;
    }
}