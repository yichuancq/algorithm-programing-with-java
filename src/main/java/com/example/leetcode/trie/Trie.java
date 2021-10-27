package com.example.leetcode.trie;

/**
 * 前缀树是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查
 * <p>
 * 请你实现 trie 类：
 * <p>
 * trie() 初始化前缀树对象。
 * void insert(string word) 向前缀树中插入字符串 word 。
 * boolean search(string word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startswith(string prefix) 如果之前已经插入的字符串word 的前缀之一为 prefix ，返回 true ；否则，返回 false
 * <p>
 * 示例：
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 */
public class Trie {
    //定义一个boolean类型来判断是否是单词的末尾
    private boolean flag;
    //指向子节点的指针数组
    private Trie[] children;

    public Trie() {
        //26个节点代表26个小写字母 a-z
        children = new Trie[26];
        //默认flag是false
        flag = false;
    }

    /**
     * @param word
     */
    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            //单词都是a-z
            int index = word.charAt(i) - 'a';
            //如果没有这个子节点的话就新建一个子节点
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            //如果有就插入
            node = node.children[index];
        }
        //循环结束，整个单词插入完成
        node.flag = true;
    }

    /**
     * 查找
     *
     * @param word
     * @return
     */
    public boolean search(String word) {
        Trie node = searchTree(word);
        //如果节点不是null并且单词已经到末尾
        if (node != null && node.flag == true) {
            return true;
        }
        return false;
    }

    /**
     * @param word
     * @return
     */
    public Trie searchTree(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.children[index] == null) {
                return null;//如果没有查找返回null
            }
            node = node.children[index];
        }
        return node;//找到就返回

    }

    /**
     * 前匹配
     *
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        if (searchTree(prefix) != null)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        trie.search("app");     // 返回 False
        trie.startsWith("app"); // 返回 True
        trie.insert("app");
        trie.search("app");     // 返回 True

    }
}
