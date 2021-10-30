package com.example.leetcode.worddictionary;

/**
 * 设计一个数据结构，支持添加新单词和查找字符串是否与任何先前添加的字符串匹配
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与word 匹配，则返回 true ；否则，返回false 。
 * word 中可能包含一些 '.' ，每个. 都可以表示任何一个字母。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 * <p>
 * 解释：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 */
class WordDictionary {

    private TrieNode root;

    /**
     * 初始化
     */
    public WordDictionary() {
        root = new TrieNode();
    }

    /**
     * 添加单词
     */
    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int ch = word.charAt(i) - 'a';
            if (node.next[ch] == null) {
                node.next[ch] = new TrieNode();
            }
            node = node.next[ch];
        }
        //加上一个标记，表示一个单词添加结束
        node.isEnd = true;
    }

    /**
     * 查找单词
     */
    public boolean search(String word) {
        TrieNode node = root;
        return dfs(node, word, 0);
    }

    /**
     * 深度优先遍历
     *
     * @param curr
     * @param word
     * @param id
     * @return
     */
    public boolean dfs(TrieNode curr, String word, int id) {
        if (curr != null) {
            //递归终止条件：
            if (word.length() == id) {
                return curr.isEnd;
            }
            //分为字符中含有‘.’和不含有‘.’的情况：
            if (word.charAt(id) == '.') {
                for (TrieNode node : curr.next) {
                    if (dfs(node, word, id + 1)) {
                        return true;
                    }
                }
            } else {
                return dfs(curr.next[word.charAt(id) - 'a'], word, id + 1);
            }
        }
        return false;
    }


    //字典树节点：
    private class TrieNode {
        private boolean isEnd;
        private TrieNode[] next;

        public TrieNode() {
            isEnd = false;
            next = new TrieNode[26];
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        wordDictionary.search("pad");// return False
        wordDictionary.search("bad"); // return True
        wordDictionary.search(".ad"); // return True
        wordDictionary.search("b.."); // return True
    }
}