package com.example.algorithm.wordtree;


import java.util.HashMap;
import java.util.Map;

/**
 * 1、构建单词数组，按A-Z排序，构建树，实现查找和排序功能
 * 2、结果存入文件，读取文件实现存档
 * 3、思考其他实现和优化方案
 */
public class WordTree {
    //keyNode
    private TrieNode root;

    /**
     *
     */
    public WordTree() {
        //默认的root
        root = new TrieNode();
    }

    /**
     * 插入字串
     *
     * @param words
     */
    public void insert(String words) {
        insert(this.root, words);
    }

    /**
     * 插入节点
     *
     * @param root
     * @param words
     */
    private void insert(TrieNode root, String words) {
        words = words.toLowerCase();
        //转化为小写
        char[] chars = words.toCharArray();
        for (int i = 0, length = chars.length; i < length; i++) {
            ///用相对于a字母的值作为下标索引，也隐式地记录了该字母的值
            int index = chars[i] - 'a';
            if (root.childs[index] != null) {
                // 已经存在了，该子节点prefix_num++
            } else {
                ///如果不存在
                root.childs[index] = new TrieNode();
            }
            root.childs[index].prefixNum++;
            //如果到了字串结尾，则做标记
            if (i == length - 1) {
                root.childs[index].isLeaf = true;
                root.childs[index].repeatNum++;
            }
            //
            //root指向子节点，继续处理
            root = root.childs[index];

        }

    }

    /**
     * 遍历Trie树，查找所有的words以及出现次数
     */
    public HashMap<String, Integer> getAllWords() {
        return preTraversal(this.root, "");
    }


    /**
     * 按字典序输出
     *
     * @param root
     */
    private void printPre(TrieNode root) {
        if (root != null) {
            System.out.println(root);
            for (TrieNode p : root.childs) {
                printPre(p);
            }
        }
    }


    /**
     * 前序遍历
     *
     * @param root
     * @param prefixes
     * @return
     */
    private HashMap<String, Integer> preTraversal(TrieNode root, String prefixes) {
        HashMap<String, Integer> map = new HashMap<>();
        if (root != null) {
            if (root.isLeaf == true) {
                //当前即为一个单词
                //map 放入key 前缀,value为重复次数
                map.put(prefixes, root.repeatNum);
            }
            for (int i = 0, length = root.childs.length; i < length; i++) {
                if (root.childs[i] != null) {
                    char ch = (char) (i + 'a');
                    String tempStr = prefixes + ch;
                    //递归调用前序遍历
                    map.putAll(preTraversal(root.childs[i], tempStr));
                }
            }
        }
        return map;

    }

    /**
     * 判断某字串是否在字典树中
     *
     * @param word
     * @return true if exists ,otherwise  false
     */
    public boolean isExist(String word) {
        return search(this.root, word);
    }

    /**
     * 查询某字串是否在字典树中
     *
     * @param word
     * @return true if exists ,otherwise  false
     */
    private boolean search(TrieNode root, String word) {
        char[] chs = word.toLowerCase().toCharArray();
        for (int i = 0, length = chs.length; i < length; i++) {
            int index = chs[i] - 'a';
            if (root.childs[index] == null) {
                //如果不存在，则查找失败
                return false;
            }
            root = root.childs[index];
        }
        return true;
    }

    /**
     * 得到以某字串为前缀的字串集，包括字串本身！ 类似单词输入法的联想功能
     *
     * @param prefix 字串前缀
     * @return 字串集以及出现次数，如果不存在则返回null
     */
    public HashMap<String, Integer> getWordsForPrefix(String prefix) {
        return getWordsForPrefix(this.root, prefix);
    }

    /**
     * 得到以某字串为前缀的字串集，包括字串本身！
     *
     * @param root
     * @param prefix
     * @return 字串集以及出现次数
     */
    private HashMap<String, Integer> getWordsForPrefix(TrieNode root, String prefix) {
        char[] chars = prefix.toLowerCase().toCharArray();
        for (int i = 0, length = chars.length; i < length; i++) {
            int index = chars[i] - 'a';
            if (root.childs[index] == null) {
                return null;
            }
            root = root.childs[index];
        }
        //结果包括该前缀本身
        return preTraversal(root, prefix);
    }

    public static void main(String[] args) throws Exception {
        String[] wordArrays = {"ail", "ailment", "aimless", "aircraft", "aircrew",
                "babble", "baby", "bachelor", "cabin", "allow", "ersatz", "zipper", "year"
                , "xerography", "neophyte", "wacky", "waffle", "wainscot", "waistcoat", "waitress",
                "ukulele", "ultraviolet", "sabbatical", "sacrificial", "quadrangular", "quarter"
                , "qualification", "macroscopic", "madonna", "magnificence", "manhattan", "marxist"
                , "massachusetts", "kindergarten", "kindly", "kleptomania", "knighthood", "knowledgeable"
                , "haberdashery", "habitation", "haggard", "hammer", "handwriting", "harangue", "harem"
                , "Catchment", "headsman", "healthful", "hearthstone", "hemorrhage", "hercules", "heroism",
                "hidalgo"};
        WordTree wordTree = new WordTree();
        for (String word : wordArrays) {
            wordTree.insert(word);
        }
        HashMap<String, Integer> map = wordTree.getAllWords();
        for (String key : map.keySet()) {
            System.out.println(key + " 出现: " + map.get(key) + "次");
        }
        boolean flag = wordTree.search(wordTree.root, "zipper");
        System.out.println("是否查询到元素:" + flag);
        if (wordTree.isExist("allow")) {
            System.out.println("字典树中存在：allow ");
        }
        //getWordsForPrefix
        HashMap<String, Integer> hashMap = wordTree.getWordsForPrefix("ai");
        for (Map.Entry entry : hashMap.entrySet()) {
            System.out.println(String.format("key: %s, val: %s", entry.getKey(), entry.getValue()));
        }
        //System.out.println("root");
        //System.out.println(wordTree.root);

        wordTree.printPre(wordTree.root);
    }
}
