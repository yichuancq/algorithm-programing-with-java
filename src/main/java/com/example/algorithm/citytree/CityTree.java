package com.example.algorithm.citytree;


/**
 *
 * @author yichuan
 */
public class CityTree {
    /***以横向凹入表示构造树，plist数组存储树（森林）的横向凹入表示字符串序列
     非递归算法，逐个结点添加方式，没有调用返回、插入结点方法
     ***/
    public static Tree<String> create(String[] prelist) {
        //创建空树
        Tree<String> tree = new Tree<String>();
        if (prelist.length == 0) {
            //返回空树
            return tree;
        }
        //创建根结点，层次为1
        tree.root = new TreeNode<String>(prelist[0], 1);
        TreeNode<String> treeNode = tree.root;
        //将plist[i]插入作为森林中最后一棵子树的最后一个孩子
        for (int i = 1; i < prelist.length; i++) {
            int n = 0;
            while (n < prelist[i].length() && prelist[i].charAt(n) == '\t') {
                //统计plist[i]串中'\t'前缀个数
                n++;
            }
            //结点元素，去除plist[i]串中所有'\t'前缀
            String str = prelist[i].substring(n);
            if (n == treeNode.level) {
                //plist[i]比p多一个'\t'前缀，插入作为p的第0个孩子
                treeNode.child = new TreeNode<String>(str, treeNode.level + 1, treeNode, null, null);
                treeNode = treeNode.child;
            } else {
                while (n < treeNode.level - 1) {
                    //plist[i]比p的'\t'少，p向上寻找插入位置
                    treeNode = treeNode.parent;
                }
                treeNode.sibling = new TreeNode<String>(str, treeNode.level, treeNode.parent, null, null);
                //同层，插入作为p结点的下个兄弟
                treeNode = treeNode.sibling;
            }
        }
        return tree;
    }

    public static void main(String[] args) {
        String prelist[] = {"中国", "\t北京", "\t上海", "\t江苏", "\t\t南京", "\t\t\t江宁", "\t\t苏州",
                "\t\t无锡", "\t\t\t锡山", "\t浙江", "\t\t杭州", "\t\t宁波", "\t\t温州", "\t广东", "\t\t广州",
                "韩国", "\t首尔", "法国", "意大利", "美国", "\t华盛顿", "\t纽约州", "\t\t纽约"};
        //以树的横向凹入表示法构造树（森林）
        Tree<String> tree = CityTree.create(prelist);
        //输出树的先根次序遍历序列
        tree.preorder();
        //输出树的后根次序遍历序列
        tree.postorder();
        //先根次序遍历树并输出树的横向凹入表示字符串
        System.out.print(tree.toString());
    }
}
