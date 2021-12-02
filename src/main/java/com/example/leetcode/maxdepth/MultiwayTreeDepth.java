package com.example.leetcode.maxdepth;


import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 给定一个 N 叉树，找到其最大深度。
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 */
public class MultiwayTreeDepth {

    public Node root = new Node();

    public MultiwayTreeDepth() {
    }

    /**
     * @param treeNode
     */
    public void MultiwayTreeDept(Node treeNode) {
        this.root = treeNode;
    }

    /**
     * dsf计算深度
     *
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        for (int i = 0; i < root.getChildren().size(); i++) {
            depth = Math.max(depth, maxDepth(root.getChildren().get(i)));
        }
        return depth + 1;
    }

    /**
     * 层次遍历
     */
    public void levelOrder(Node root) {
        Node p = root;
        LinkedBlockingQueue<Node> queue = new LinkedBlockingQueue();
        System.out.println("\r\n层次遍历");
        while (p != null) {
            // 访问P结点
            System.out.print(p.getVal() + " ");
            if (p.getChildren() != null && p.getChildren().size() > 0) {
                System.out.println();
                for (Node node : p.getChildren()) {
                    queue.add(node);
                }
            }
            //p 指向出队结点
            p = queue.poll();
        }
    }

    //层序遍历解法
    public int maxDepth2(Node root) {
        if (root == null)
            return 0;
        if (root.getChildren().size() == 0)
            return 1;
        int depth = 0;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            depth++;
            while (count > 0) {
                Node node = queue.poll();
                if (node.getChildren().size() != 0)
                    queue.addAll(node.getChildren());
                count--;
            }
        }
        return depth;
    }


    public int maxDepth3(Node root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            int len = queue.size();
            for (int j = 0; j < len; j++) {
                Node temp = queue.poll();
                for (int i = 0; i < temp.children.size(); i++) {
                    if (temp.children.get(i) != null) {
                        queue.add(temp.children.get(i));
                    }
                }
            }
        }
        return res;
    }

    /**
     * 节点数统计
     *
     * @param root
     * @return
     */
    public int nodeCount(Node root) {
        int n = 0;

        if (root != null && root.getVal() != -1) {
            n += 1;
            System.out.println("" + root);
            if (root.getChildren() != null && root.getChildren().size() > 0) {
                for (Node nodeChild : root.getChildren()) {
                    n += nodeCount(nodeChild);
                }
            }
        }
        return n;
    }

    /**
     * @param numbers
     * @return
     */
    int counter = 0;

    private Node buildTree(List<Integer> numbers) {
        Queue<Node> queue = new LinkedList<>();
        Node node = null;
        if (node != null) {
            queue.add(node);
        }
        System.out.println("size:" + numbers.size());
        if (numbers.size() > 0) {
            List<Node> childrenList = new ArrayList<>();
            Iterator iterator = numbers.iterator();
            while (iterator.hasNext()) {
                Integer tempInteger = (Integer) iterator.next();
                counter++;
                if (tempInteger != null) {
                    Node nodeTemp = new Node(tempInteger);
                    System.out.println("node:" + nodeTemp);
                    iterator.remove();
                    //设置子节点
                    nodeTemp.setChildren(childrenList);
                    if (node == null) {
                        node = nodeTemp;
                    } else {
                        node.setChildren(childrenList);
                    }
                    //
                    buildTree(numbers);
                }
            }

        }
        return node;
    }

    private void test() {
        List<List<Integer>> listList = new ArrayList<>();
        Integer[] arrays = {1, null, 3, 2, 4, null, 5, 6};
        LinkedBlockingQueue queue = new LinkedBlockingQueue();
        int i = 0;
        while (i < arrays.length) {
            if (arrays[i] != null) {
                queue.add(arrays[i]);
            } else {
                System.out.println("----");
                List<Integer> integers = new ArrayList<>();

                while (!queue.isEmpty()) {
                    Integer childVal = (Integer) queue.poll();
                    System.out.println("child val:" + childVal);

                    integers.add(childVal);
                }
                listList.add(integers);
            }
            i++;
        }
        System.out.println("----");
        List<Integer> integers = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer childVal = (Integer) queue.poll();
            System.out.println("child val:" + childVal);
            integers.add(childVal);
        }
        listList.add(integers);

        System.out.println(listList);

    }


    /**
     * 数组生成双层集合
     *
     * @param arrays
     * @return
     */

    private List<List<Integer>> builder(final Integer[] arrays) {
        List<List<Integer>> listList = new ArrayList<>();
        LinkedBlockingQueue queue = new LinkedBlockingQueue();
        int i = 0;
        while (i < arrays.length) {
            if (arrays[i] != null) {
                queue.add(arrays[i]);
            } else {
                if (arrays[i + 1] != null) {
                    List<Integer> integers = new ArrayList<>();
                    while (!queue.isEmpty()) {
                        Integer childVal = (Integer) queue.poll();
                        integers.add(childVal);
                    }
                    listList.add(integers);
                }
            }
            i++;
        }
        List<Integer> integers = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer childVal = (Integer) queue.poll();
            integers.add(childVal);
        }
        listList.add(integers);
        return listList;
    }


    private Node builderTest(Integer[] arrays, Node node) {
        int i = 0;
        int j = 0;
        int k = 0;
        //Node node = new Node();
        List<List<Integer>> listList = this.builder(arrays);
        for (List<Integer> integers : listList) {
            System.out.println("==");
            i++;
            System.out.println("第 " + i + "层");
            List<Node> childs = new ArrayList<>();
            for (Integer integer : integers) {
                j++;
                Node childNode = new Node(integer);
                childs.add(childNode);
                k++;
                if (node != null && node.getVal() == 0) {
                    node.setVal(integer);
                }
                System.out.println("in  -->" + childNode);
            }
            System.out.println("i=" + i);
            if (i > 1 && childs.size() > 0) {
                if (node.getChildren().size() == 0) {
                    node.setChildren(childs);
                    k++;
                } else {
                    for (Node node1 : node.getChildren()) {
                        k++;
                        node1.setChildren(childs);

                    }
                }

            }

        }
        System.out.println("node amount:" + j);
        System.out.println("k amount:" + k);
        return node;
    }


    /**
     * @param arrays
     * @param node
     * @return
     */
    int ii = 0;
    int empty = 0;
    int notEmpty = 0;

    private Node builder2(final Integer[] arrays, Node node) {
        LinkedBlockingQueue queue = new LinkedBlockingQueue();

        while (ii < arrays.length) {
            if (arrays[ii] != null) {
                notEmpty++;
                queue.add(arrays[ii]);
                if (node != null && node.getVal() == 0) {
                    node.setVal(arrays[ii]);
                    node.setChildren(null);
                }
            } else {

                empty++;
                //child node list
                List<Node> childNodes = new ArrayList<>();
                while (!queue.isEmpty()) {
                    Integer childVal = (Integer) queue.poll();
                    childNodes.add(new Node(childVal));
                }
                if (ii > 1) {
                    node.setChildren(childNodes);
                }
                System.out.println(node);
            }
            ii++;
        }
        // System.out.println("----");
        System.out.println("node is=" + (notEmpty));
        System.out.println("total is=" + (ii));
        int leave = ii - notEmpty;
        List<Node> childNodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer childVal = (Integer) queue.poll();
            System.out.println("childVal->" + childVal);
            childNodes.add(new Node(childVal));
        }
        if (node.getChildren() != null) {
            if (leave > 0) {
                for (Node node1 : node.getChildren()) {
                    leave = leave - childNodes.size();
                    node1.setChildren(childNodes);
                    //if (leave <= 0) {
                    break;
                    // }

                }
            }

        }
        return node;
    }


    public static void main(String[] args) {
        // TODO: 2021/12/3  多叉树创建和遍历
        MultiwayTreeDepth multiwayTreeDepth = new MultiwayTreeDepth();
        Integer[] arrays = {1, null, 2, 3, 4, 5, null, null, 6, 7, null, 8, null, 9, 10,
                null, null, 11, null, 12, null, 13, null, null, 14};
        Node root = multiwayTreeDepth.builderTest(arrays, new Node());
        System.out.println(root);

        //builder2
//        Node root = multiwayTreeDepth.builder2(arrays, new Node());
//        System.out.println(root);

//        List<Node> list1 = new ArrayList<>();
//        List<Node> list3 = new ArrayList<>();
//        list1.add(new Node(3, list3));
//        list1.add(new Node(2));
//        list1.add(new Node(4));
//        list3.add(new Node(5));
//        list3.add(new Node(6));

//        List<Integer> numbers = Arrays.stream(arrays).collect(Collectors.toList());

        //  List<Integer> numbers = Arrays.stream(arrays).collect(Collectors.toList());

        //
//        Node root = new Node(1, list1);
//        System.out.println(root);
//        System.out.println("===");
//        int maxDepth = multiwayTreeDepth.maxDepth(root);
//        System.out.println("maxDepth:" + maxDepth);
//        int nodeAmount = multiwayTreeDepth.nodeCount(root);
//        System.out.println("节点数：" + nodeAmount);
//        System.out.println("===");
//        multiwayTreeDepth.levelOrder(root);
//        Node root = null;
//        multiwayTreeDepth.buildTree(numbers);


    }
}
