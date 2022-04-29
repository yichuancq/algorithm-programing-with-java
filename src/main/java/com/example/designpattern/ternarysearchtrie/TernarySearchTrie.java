package com.example.designpattern.ternarysearchtrie;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 三叉Trie树
 * 三叉Trie树在占用空间上要比N叉树好的多。
 * 在一个三叉搜索树（Ternary SearchTrie）中，每一个节点包括一个字符，但和数字搜索树不同，
 * 三叉搜索树只有三个指针：一个指向左边的树；一个指向右边的树；还有一个向下，指向单词的下一个数据单元三叉搜索树是二叉搜索树和数字搜索树的混合体。
 * 它有和数字搜索树差不多的速度但是和二叉搜索树一样只需要相对较少的内存空间。树是否平衡取决于单词的读入顺序。如果按排序后的顺序插入
 * ，则生成方式最不平衡。单词的读入顺序对于创建平衡的三叉搜索树很重要，但对于二叉搜索树就不太重要。通过选择一个排序后数据单元集合的中间值
 * ，并把它作为开始节点，我们可以创建一个平衡的三叉树。可以写一个专门的过程来生成平衡的三叉树词典。
 * Patricia Tree 简称PAT tree。 它是 trie 结构的一种特殊形式。是目前信息检索领域应用十分成功的索引方
 */
public class TernarySearchTrie {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        int size = 20;
        TernarySearchTrie tree = new TernarySearchTrie();
        for (int i = 0; i < size; i++) {
            map.put("tkey" + i, "value" + i);
            tree.addWord("tkey" + i);
        }
        System.out.println("search:");
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            Entry node = tree.search(key);
            System.out.println(node.data.get("value") + ",查找次数：" + node.data.get("count"));
        }
    }

    private Entry root = new Entry();

    public Entry addWord(String key) {
        if (key == null || key.trim().length() == 0) return null;
        // 调试的时候发现个问题很是不明白，为什么根节点一开始就有不为NULL的right节点，并且这个right节点的splitchar是k
        // 终于发现了，java程序在调试的时候可能存在一个预编译的问题，某些链表式的对象调试的时候DEBUG信息不是很准备，甚至错误，比如链表啊,i++等操作调试就会看到错误的信息，这样的情况用打印语句调试算了。
        Entry node = root;
        int i = 0;
        while (true) {
            int diff = key.charAt(i) - node.splitchar;
            char c = key.charAt(i);
            if (diff == 0) {// 当前单词和上一次的相比较，如果相同
                i++;
                if (i == key.length()) {
                    node.data = new HashMap<Object, Object>();
                    node.data.put("value", key);
                    return node;
                }
                if (node.equals == null) node.equals = new Entry(key.charAt(i));// 这里要注意，要获取新的单词填充进去，因为i++了
                node = node.equals;
            } else if (diff < 0) {// 没有找到对应的字符，并且下一个左或右节点为NULL，则会一直创建新的节点
                if (node.left == null) node.left = new Entry(c);
                node = node.left;
            } else {
                if (node.right == null) node.right = new Entry(c);
                node = node.right;
            }
        }
    }

    public Entry search(String key) {
        if (key == null || key.trim().length() == 0) return null;
        Entry node = root;
        int count = 0, i = 0;
        while (true) {
            if (node == null) return null;
            int diff = key.charAt(i) - node.splitchar;
            count++;
            if (diff == 0) {
                i++;
                if (i == key.length()) {
                    node.data.put("count", count);
                    return node;
                }
                node = node.equals;
            } else if (diff < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
    }

    /**
     * 三叉Trie树存在3个节点，左右子节点和二叉树类似，以前key都是存放在二叉树的当前节点中，在三叉树中单词是存放在中间子树的。
     */
    static class Entry {
        Entry left;
        Entry right;
        Entry equals;// 比对成功就放到中间节点
        char splitchar;// 单词
        Map<Object, Object> data;// 扩展数据域，存放 检索次数，关键码频率等信息。

        public Entry(char splitchar) {
            this.splitchar = splitchar;
        }

        public Entry() {
        }
    }
}
