package com.example.leetcode.hashset;

/**
 * 设计hashset
 */
public class MyHashSet {
    private Node root;

    public MyHashSet() {
        root = null;
    }

    public void add(int key) {
        root = add(root, key % 10, key);
    }

    public void remove(int key) {
        root = remove(root, key % 10, key);
    }

    public boolean contains(int key) {
        Node node = findNode(root, key % 10);
        if (node != null && node.value.contains(key)) {
            return true;
        }
        return false;
    }

    //获取高度
    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    //获取平衡因子
    private int getBalancedFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    //右旋转
    private Node rightRotate(Node T) {
        Node x = T.left;
        Node y = x.right;
        x.right = T;
        T.left = y;
        //更新高度
        T.height = Math.max(getHeight(T.left), getHeight(T.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    //左旋转
    private Node leftRotate(Node T) {
        Node x = T.right;
        Node y = x.left;
        x.left = T;
        T.right = y;
        //更新高度
        T.height = Math.max(getHeight(T.left), getHeight(T.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    //添加的辅助函数
    private Node add(Node root, int key, int value) {
        if (root == null) {
            return new Node(key, value);
        }
        if (key - root.key > 0) {
            root.right = add(root.right, key, value);
        } else if (key - root.key < 0) {
            root.left = add(root.left, key, value);
        } else {
            if (!root.value.contains(value)) {
                root.value.add(value);
            }
        }
        //更新高度
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        //计算平衡因子
        int balance = getBalancedFactor(root);
        //LL情况
        if (balance > 1 && getBalancedFactor(root.left) >= 0) {
            //右旋 差为0或1都为平衡
            return rightRotate(root);
            //RR情况
        } else if (balance < -1 && getBalancedFactor(root.right) <= 0) {
            //左旋
            return leftRotate(root);
            //LR情况
        } else if (balance > 1 && getBalancedFactor(root.left) < 0) {
            root.left = leftRotate(root.left);//左旋
            return rightRotate(root);//右旋
            //RL情况
        } else if (balance < -1 && getBalancedFactor(root.right) > 0) {
            root.right = rightRotate(root.right);//左旋
            return leftRotate(root);//右旋
        }
        return root;
    }

    //删除的辅助函数
    private Node remove(Node root, int key, int value) {
        if (root == null) {
            return null;
        }
        Node res = null;
        if (key - root.key > 0) {
            root.right = remove(root.right, key, value);
            res = root;
        } else if (key - root.key < 0) {
            root.left = remove(root.left, key, value);
            res = root;
        } else {
            if (root.value.size() > 1) {
                if (root.value.contains(value)) {
                    for (int i = 0; i < root.value.size(); i++) {
                        if (root.value.get(i) == value) {
                            root.value.remove(i);
                        }
                    }
                }
                res = root;
            } else if (root.left == null && root.value.contains(value)) {
                Node node = root.right;
                root.right = null;
                res = node;
            } else if (root.right == null && root.value.contains(value)) {
                Node node = root.left;
                root.left = null;
                res = node;
            } else if (root.value.contains(value)) {
                Node successer = findMin(root.right);
                successer.left = root.left;
                for (int i = 1; i < successer.value.size(); i++) {
                    successer.value.remove(i);
                }
                successer.right = remove(root.right, successer.key, successer.value.get(0));
                root.left = root.right = null;
                res = successer;
            } else {
                res = root;
            }
        }
        if (res == null) {
            return null;
        }
        //更新高度
        res.height = Math.max(getHeight(res.left), getHeight(res.right)) + 1;
        //计算平衡因子
        int balance = getBalancedFactor(res);
        //LL情况
        if (balance > 1 && getBalancedFactor(res.left) >= 0) {
            //右旋 差为0或1都为平衡
            return rightRotate(res);
            //RR情况
        } else if (balance < -1 && getBalancedFactor(res.right) <= 0) {
            //左旋
            return leftRotate(res);
            //LR情况
        } else if (balance > 1 && getBalancedFactor(res.left) < 0) {
            root.left = leftRotate(res.left);//右旋
            return rightRotate(res);//左旋
            //RL情况
        } else if (balance < -1 && getBalancedFactor(res.right) > 0) {
            root.right = rightRotate(res.right);//左旋
            return leftRotate(res);//右旋
        }
        return res;
    }

    //找到右子树最小的节点
    private Node findMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    //辅助函数寻找被删节点
    private Node findNode(Node root, int key) {
        if (root == null) {
            return null;
        }
        if (key - root.key > 0) {
            return findNode(root.right, key);
        } else if (key - root.key < 0) {
            return findNode(root.left, key);
        } else {
            return root;
        }
    }

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);
        //true
        System.out.println(myHashSet.contains(1));
        myHashSet.remove(1);
        //false
        System.out.println(myHashSet.contains(1));
    }
}
