package com.example.algorithm.wordtree;

import java.util.Arrays;

/**
 * 单词结点
 */
public class TrieNode {
    /**
     * 重复次数
     * 该字串的重复数目,该属性统计重复次数的时候有用,取值为0->n...
     */
    public int repeatNum;
    /**
     * 以该字串为前缀的字串数， 应该包括该字串本身
     */
    public int prefixNum;
    /**
     * 父节点
     */
    public TrieNode parent;
    /**
     * 下一个结点集合（目前设置为26个）
     */
    public TrieNode childs[];
    /**
     * 是否为单词节点
     */
    public boolean isLeaf;
    /**
     * 值
     */
    public String val;

    public TrieNode() {
        prefixNum = 0;
        repeatNum = 0;
        isLeaf = false;
        val = "";
        childs = new TrieNode[26];
    }


    @Override
    public String toString() {
        return "TrieNode{" +
                "repeatNum=" + repeatNum +
                ", prefixNum=" + prefixNum +
                ", parent=" + parent +
                ", childs=" + Arrays.toString(childs) +
                ", isLeaf=" + isLeaf +
                ", val='" + val + '\'' +
                '}';
    }
}
