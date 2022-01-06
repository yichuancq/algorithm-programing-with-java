package com.example.algorithm.lfucache;

public class Node {
    int key;
    int val;
    int freq;
    Node next;
    Node pre;

    public Node() {
    }

    public Node(int key, int val, int freq, Node pre, Node next) {
        this.key = key;
        this.val = val;
        this.freq = freq;
        this.next = next;
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "Node{" + "key=" + key + ", val=" + val + ", freq=" + freq + '}';
    }

}
