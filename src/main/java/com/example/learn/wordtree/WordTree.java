package com.example.learn.wordtree;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 1、构建单词数组，按A-Z排序，构建树，实现查找和排序功能
 * 2、结果存入文件，读取文件实现存档
 * 3、思考其他实现和优化方案
 */
public class WordTree {

    private int len = 0;

    private TreeNode head = new TreeNode("root");


    public WordTree() {
    }

    /**
     * 构建树
     *
     * @param arrays
     * @return
     */
    public TreeNode buildWordTree(String[] arrays, int n, int children, TreeNode parent) {
        TreeNode treeNode = null;
        if (arrays == null || arrays.length == 0) {
            return null;
        }
        if (n < arrays.length) {
            String word = arrays[n];
            if (!word.isEmpty()) {
                List<TreeNode> treeNodeList = new ArrayList<>();
                treeNode = new TreeNode(word, treeNodeList, parent);
                //下一个索引
                int index = children * n + 1;
                for (int i = 0; i < children; i++) {
                    //递归调用
                    TreeNode p = new TreeNode(treeNode.val);
                    TreeNode temp = this.buildWordTree(arrays, index, children, p);
                    if (temp != null && temp.val != null) {
                        treeNodeList.add(temp);
                    }
                    index++;
                }
                //按值排序
                // treeNodeList.sort(Comparator.comparing(TreeNode::getVal));
            }
        }
        return treeNode;
    }

    /**
     * 遍历打印结点
     *
     * @param treeNode
     */
    private void printNode(TreeNode treeNode) {
        if (treeNode != null && !treeNode.getChildren().isEmpty()) {
            // System.out.println("val:" + treeNode.val);
            for (TreeNode temp : treeNode.getChildren()) {
                System.out.println("val:" + temp.val);
                printNode(temp);
            }
        }
    }


    private int size(TreeNode treeNode) {
        if (treeNode != null) {
            len += 1;
            for (TreeNode temp : treeNode.getChildren()) {
                size(temp);
            }
        }
        return len;
    }

    /**
     * @param treeNode
     * @param keyWord
     * @return
     */
    private TreeNode search(TreeNode treeNode, String keyWord) {
        if (treeNode != null) {
            if (keyWord.equals(treeNode.val)) {
                System.out.println("find key:" + treeNode.val);
                return treeNode;
            } else {
                for (TreeNode childNode : treeNode.getChildren()) {
                    search(childNode, keyWord);
                }
            }
        }
        return treeNode;

    }

    /**
     * 生成随机大小的字符
     *
     * @param size
     * @return
     * @throws Exception
     */
    private String[] buildWords(int size) throws Exception {
        String[] words = new String[size];
        for (int i = 0; i < size; i++) {
            Thread.sleep(10);
            Random random = new Random();
            int currentBall = random.ints(1, 20)
                    .limit(20).findAny().getAsInt();
            String str = createRandomStr(currentBall);
            words[i] = str;
        }
        return words;
    }

    /**
     * 随机字符
     *
     * @param i
     * @return
     */
    public static String createRandomStr(int i) {
        Random rand = new Random();
        final String[] tmp = {
                "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < i; j++) {
            sb.append(tmp[rand.nextInt(tmp.length)]);
        }
        return sb.toString().toLowerCase();
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
        // String[] strings = wordTree.buildWords(50);
        int size = 100;
//        String[] strings = new String[size];
//        for (int i = 0; i < size; i++) {
//            strings[i] = "单词" + i;
//        }
        //子节点最大个数
        int children = 26;
        TreeNode root = wordTree.buildWordTree(wordArrays, 0, children, wordTree.head);
//        System.out.println(JSON.toJSON(root));
//        wordTree.printNode(root);
        TreeNode key = wordTree.search(root, "wacky");
        System.out.println("key=" + key.val);
        int len = wordTree.size(root);
        System.out.println("" + len);

    }

}
