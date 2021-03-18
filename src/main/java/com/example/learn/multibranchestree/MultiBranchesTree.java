package com.example.learn.multibranchestree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 多叉树
 */
public class MultiBranchesTree<T> extends TreeNode<T> {
    /**
     * 根节点
     */
    private TreeNode<T> root;
    /**
     * 结点个数
     */
    private int size;

    public MultiBranchesTree() {
    }

    /**
     * @param data
     * @param parent
     * @param childList
     */
    public MultiBranchesTree(T data, TreeNode<T> parent, List<TreeNode<T>> childList) {
        super(data, parent, childList);
    }

    /**
     * @param root
     */
    private void initTree(TreeNode<T> root) {
        System.out.println("init tree:" + root.getData());
        this.root = root;
        size++;
    }

    /**
     * 计算结点个数
     *
     * @param treeNode
     * @return
     */
    public int treeSize(TreeNode<T> treeNode) {
        if (treeNode == null) {
            return size;
        }
        if (treeNode.getChildList() == null || treeNode.getChildList().size() == 0) {
            return size;
        }
        for (TreeNode temp : treeNode.getChildList()) {
            size++;
            this.treeSize(temp);
        }
        return size;
    }

    /**
     * @param newNode
     * @param parent
     */
    public void insertNode(TreeNode<T> newNode, TreeNode<T> parent) {

    }


    /**
     * @param deleteNode
     */
    public void removeNode(TreeNode<T> deleteNode) {
        TreeNode<T> parent = deleteNode.getParent();
        if (parent == null || deleteNode == null) {
            return;
        }
        if (parent.getChildList() == null || parent.getChildList().size() == 0) {
            return;
        }
        //
        Iterator iterator = parent.getChildList().iterator();
        while (iterator.hasNext()) {
            TreeNode treeNode = (TreeNode) iterator.next();
            System.out.println("removeNode" + treeNode.toString());
            if (treeNode.toString().equals(deleteNode.toString())) {
                System.out.println("delete" + treeNode.toString());
                iterator.remove();
            }
        }
    }

    /**
     * @param key
     * @return
     */
    public boolean contains(Integer key) {
        System.out.println("search the key:" + key);
        //遍历树
        TreeNode treeNode = this.findKey(this.root, key);
        if (treeNode != null) {
            Menu menu = (Menu) treeNode.getData();
            System.out.println("" + menu.toString());
            boolean flag = key.equals(menu.getId());
            if (flag) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找指定元素的结点
     *
     * @param treeNode
     * @param key
     * @return
     */
    private TreeNode findKey(TreeNode<T> treeNode, Integer key) {
        if (treeNode == null) {
            return null;
        }
        if (treeNode.getChildList() == null || treeNode.getChildList().size() == 0) {
            return treeNode;
        }
        for (TreeNode temp : treeNode.getChildList()) {
            System.out.println("treeNode->" + temp.toString());
            //
            Menu menu = (Menu) treeNode.getData();
            boolean flag = key.equals(menu.getId());
            if (flag) {
                break;
            }
            treeNode = this.findKey(temp, key);
        }
        return treeNode;
    }

    /**
     * 遍历结点
     */
    public void traverse(TreeNode<T> treeNode) {
        if (treeNode == null) {
            return;
        }
        if (treeNode.getChildList() == null || treeNode.getChildList().size() == 0) {
            return;
        }
        for (TreeNode temp : treeNode.getChildList()) {
            System.out.println(temp.getData().toString());
            this.traverse(temp);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        MultiBranchesTree multiBranchesTree = new MultiBranchesTree();
        TreeNode treeNodeRoot = new TreeNode();
        Menu root = new Menu(0, "root");
        //
        Menu menu1 = new Menu(1, "一级目录1");
        Menu menu2 = new Menu(2, "一级目录2");
        Menu menu3 = new Menu(3, "一级目录3");
        //
        Menu menu11 = new Menu(11, "二级目录11");
        Menu menu12 = new Menu(12, "二级目录12");
        Menu menu13 = new Menu(13, "二级目录13");
        //
        Menu menu21 = new Menu(21, "二级目录21");
        Menu menu22 = new Menu(22, "二级目录22");
        //一级菜单1
        List<TreeNode> menu1ChildList = new ArrayList<>();
        //二级菜单21
        List<TreeNode> menu2ChildList = new ArrayList<>();
        //二级菜单11
        TreeNode treeNodeMenu1 = new TreeNode(menu1, treeNodeRoot, menu1ChildList);
        //一级菜单2
        TreeNode treeNodeMenu2 = new TreeNode(menu2, treeNodeRoot, menu2ChildList);
        //
        TreeNode treeNodeMenu11 = new TreeNode(menu11, treeNodeMenu1, null);
        //二级菜单12
        TreeNode treeNodeMenu12 = new TreeNode(menu12, treeNodeMenu1, null);
        //二级菜单13
        TreeNode treeNodeMenu13 = new TreeNode(menu13, treeNodeMenu1, null);
        //二级菜单21
        TreeNode treeNodeMenu21 = new TreeNode(menu21, treeNodeMenu2, null);
        //二级菜单22
        TreeNode treeNodeMenu22 = new TreeNode(menu22, treeNodeMenu2, null);
        //
        menu1ChildList.add(treeNodeMenu11);
        menu1ChildList.add(treeNodeMenu12);
        menu1ChildList.add(treeNodeMenu13);
        //
        menu2ChildList.add(treeNodeMenu21);
        menu2ChildList.add(treeNodeMenu22);
        //一级菜单3
        TreeNode treeNodeMenu3 = new TreeNode(menu3, treeNodeRoot, null);

        List<TreeNode> rootChildList = new ArrayList<>();
        rootChildList.add(treeNodeMenu1);
        rootChildList.add(treeNodeMenu2);
        rootChildList.add(treeNodeMenu3);

        treeNodeRoot.setData(root);
        treeNodeRoot.setChildList(rootChildList);
        multiBranchesTree.initTree(treeNodeRoot);
        //
        multiBranchesTree.traverse(multiBranchesTree.root);

        int treeNodeSize = multiBranchesTree.treeSize(multiBranchesTree.root);
        System.out.println("结点个数:" + treeNodeSize);
        boolean hasElement = multiBranchesTree.contains(12);
        System.out.println("包含元素:" + hasElement);
        //
        Menu menuDelete = new Menu(2, "一级目录2");
//   Menu menu2 = new Menu(2, "一级目录2");
        //treeNodeMenu2 = new TreeNode(menu2, treeNodeRoot, menu2ChildList);
        multiBranchesTree.removeNode(treeNodeMenu2);

        multiBranchesTree.traverse(multiBranchesTree.root);
    }
}
