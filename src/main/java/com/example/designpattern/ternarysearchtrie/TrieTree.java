package com.example.designpattern.ternarysearchtrie;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Trie树，又称单词查找树或键树，是一种树形结构，是一种哈希树的变种.
 * 典型应用是用于统计和排序大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计.
 * 它的优点是：最大限度地减少无谓的字符串比较，查询效率比哈希表高。
 * <p>
 * 网上有许多人错误的把Trie树理解为二叉的，其实Trie树可以是二叉，也可以是多叉的，
 * 本例建立的就是多叉的Trie树，每个节点的子节点集合是一个HashMap，可以这样理解：
 * 根节点下面有N个子节点，第K个子节点下面也是N个子节点。
 */
public class TrieTree {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        TrieTree tree = new TrieTree();
        for (int i = 0; i < 20; i++) {
            map.put("key,value" + i, "value" + i);
            tree.addWord("key,value" + i, "value" + i);
        }
        System.out.println("search:");
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            System.out.println(tree.search(key));
        }
        System.out.println(tree.search("ke1"));
    }

    private Entry root = new Entry();

    public void addWord(String key, String o) {
        Entry node = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (node.children.get(c) == null) {
                node.children.put(c, new Entry(c));
            }
            node = node.children.get(c);
        }
        node.o = o;
    }

    public Object search(String key) {
        Entry node = root;
        int count = 0;
        for (int i = 0; i < key.length(); i++) {
            count++;
            if (node.children.get(key.charAt(i)) == null) return null;
            if (node.children.get(key.charAt(i)).o != null) {
                System.out.println("key:" + key + ",count:" + count);
                return node.children.get(key.charAt(i)).o;
            }
            node = node.children.get(key.charAt(i));
        }
        return null;
    }

    static class Entry {
        Map<Character, Entry> children = new HashMap<Character, TrieTree.Entry>();
        char c;
        String o;

        public Entry(char c) {
            this.c = c;
        }

        public Entry() {
        }
    }
}