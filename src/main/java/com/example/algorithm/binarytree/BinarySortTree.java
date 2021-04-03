package com.example.algorithm.binarytree;

import com.example.algorithm.queue.LinkedQueue;

/**
 * 二叉排序树
 *
 * @param <T>
 */
public class BinarySortTree<T extends Comparable<? super T>> {
    //根结点，三叉链表结点结构
    public TriNode<T> root;

    /**
     * 构造空二叉排序树
     */
    public BinarySortTree() {
        this.root = null;
    }

    /***构造二叉排序树，由values数组提供元素
     *
     * @param values
     */
    public BinarySortTree(T[] values) {
        this();             //构造空二叉排序树
        this.addAll(values);//插入values数组所有元素
    }

    /**
     * 判断是否空二叉树
     *
     * @return
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /***2.查找操作
     查找关键字为key元素，若查找成功，返回结点，否则返回null。非递归算法。
     若key==null，Java抛出空对象异常
     查找算法经过一条从根到结点的路径，非递归，查找不成功，遍历路径是从根到叶子。
     */
    public TriNode<T> searchNode(T key) {
        TriNode<T> p = this.root;
        while (p != null && key.compareTo(p.data) != 0) {
            if (key.compareTo(p.data) < 0) {
                //进入左子树
                p = p.left;
            } else {
                //进入右子树
                p = p.right;
            }

        }
        //若查找成功，返回结点，否则返回null
        return p != null ? p : null;
    }

    /**
     * 查找关键字为key元素，若查找成功，返回元素，否则返回null
     *
     * @param key
     * @return
     */
    public T search(T key) {
        TriNode<T> find = this.searchNode(key);
        return find != null ? find.data : null;
    }

    /**
     * 3、插入操作
     * 插入元素x结点，不插入关键字重复元素和空对象，返回插入与否状态。
     * 若x==null，Java抛出空对象异常
     **/
    public boolean add(T x) {
        if (this.root == null) {
            //创建根结点
            this.root = new TriNode<T>(x);
        } else {    //将x插入到以root为根的二叉排序树中
            TriNode<T> p = this.root, parent = null;
            //查找确定插入位置
            while (p != null) {
                //查找成功，不插入关键字重复的元素
                if (x.compareTo(p.data) == 0) {
                    return false;
                }
                parent = p;
                if (x.compareTo(p.data) < 0) {
                    p = p.left;
                } else {
                    p = p.right;
                }
            }
            //插入x叶子结点作为parent的左/右孩子
            if (x.compareTo(parent.data) < 0) {
                parent.left = new TriNode<T>(x, parent, null, null);
            } else {
                parent.right = new TriNode<T>(x, parent, null, null);
            }
        }
        return true;
    }

    /**
     * 思考题：能否直接调用查找算法确定插入位置？为什么？
     * <p>
     * 4.中根次序迭代遍历
     * 返回中根次序遍历二叉树所有结点的描述字符串，迭代遍历，非递归算法
     **/
    @Override
    public String toString() {
        String str = "[";
        //寻找第一个访问结点，最小值
        TriNode<T> p = this.first(this.root);
        while (p != null) {
            str += p.data.toString() + " ";
            //返回p在中根次序下的后继结点
            p = this.next(p);
        }
        return str + "]";
    }

    /**
     * //以中根次序遍历二叉树，输出所有结点元素，非递归算法
     */
    public void inorder() {
        System.out.print("[");
        //寻找第一个访问结点，最小值
        TriNode<T> p = this.first(this.root);
        while (p != null) {
            System.out.print(p.data.toString() + " ");
            //返回p在中根次序下的前驱结点
            p = this.next(p);
        }
        System.out.println("]");
    }

    /**
     * 在以p为根的子树中，返回中根次序下第一个访问结点，即是根的最左边的子孙结点，最小值
     */
    public TriNode<T> first(TriNode<T> p) {
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
        }
        return p;
    }

    /**
     * 返回p在中根次序下的后继结点
     *
     * @param p
     * @return
     */
    public TriNode<T> next(TriNode<T> p) {
        if (p != null) {
            //若p有右孩子，
            if (p.right != null) {
                //则p的后继是其右子树第一个访问结点
                return this.first(p.right);
            }
            //若p没有右孩子，向上寻找某个祖先结点
            while (p.parent != null) {
                //若p是其父母的左孩子，则p的后继是其父母
                if (p.parent.left == p) {
                    return p.parent;
                }
                p = p.parent;
            }
        }
        return null;
    }

    /**
     * 思考题8-4】
     * 中根次序遍历二叉树（逆序），输出所有结点元素。
     * 迭代算法从中根次序下最后一个访问结点（最大值）开始，依次到达前驱结点再访问。
     */
    public void inorderPrevious() {
        System.out.print("[");
        for (TriNode<T> p = this.last(this.root); p != null; p = this.previous(p)) {
            System.out.print(p.data.toString() + " ");
        }
        System.out.println("]");
    }

    /**
     * 在以p为根的子树中，返回中根次序下最后一个访问结点，即是根的最右边的子孙结点，最大值
     *
     * @param p
     * @return
     */
    public TriNode<T> last(TriNode<T> p) {
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
        }
        return p;
    }

    /***返回p在中根次序下的前驱结点
     *
     * @param p
     * @return
     */
    public TriNode<T> previous(TriNode<T> p) {
        if (p != null) {
            //若p有左孩子，则p的前驱是其左子树最后一个访问结点
            if (p.left != null) {
                return this.last(p.left);
            }
            //若p没有左孩子，向上寻找某个祖先结点
            while (p.parent != null) {
                //若p是其父母的右孩子，则p的前驱是其父母
                if (p.parent.right == p) {
                    return p.parent;
                }
                p = p.parent;
            }
        }
        return null;
    }

    /**
     * //判断是否包含关键字为key元素
     *
     * @param key
     * @return
     */
    public boolean contains(T key) {
        return this.searchNode(key) != null;
    }

    /**
     * //插入values数组所有元素
     *
     * @param values
     */
    public void addAll(T[] values) {
        for (int i = 0; i < values.length; i++)
            //插入元素
            this.add(values[i]);
    }

    /**
     * 删除所有元素
     */
    public void clear() {
        this.root = null;
    }

    /**
     * 返回元素个数
     *
     * @return
     */
    public int size() {
        return 0;//??this.set.size();
    }

    /***【例8.4】  使用二叉排序树表示互异的排序集合。
     6.删除操作
     删除关键字为key结点，返回被删除元素；若没找到则不删除，返回null。//非递归算法，若key==null，Java抛出空对象异常
     **/
    public T remove(T key) {
        TriNode<T> p = this.searchNode(key);
        //查找关键字为key元素，若查找成功，返回结点，否则返回null
        //找到待删除结点p，若p是2度结点
        if (p != null && p.left != null && p.right != null) {
            //寻找p在中根次序下的后继结点insucc
            TriNode<T> insucc = this.first(p.right);
            //交换待删除元素，作为返回值
            T temp = p.data;
            //以后继结点值替换p结点值
            p.data = insucc.data;
            insucc.data = temp;
            //转化为删除insucc，删除1、0度结点
            p = insucc;
        }
        //p是1度或叶子结点，删除根结点，p.parent==null
        if (p != null && p == this.root) {
            if (this.root.left != null) {
                //以p的左孩子顶替作为新的根结点
                this.root = p.left;
            } else {
                //以p的右孩子顶替作为新的根结点
                this.root = p.right;
            }
            if (this.root != null) {
                this.root.parent = null;
            }
            //返回被删除根结点元素
            return p.data;
        }
        //p是1度或叶子结点，p是父母的左孩子
        if (p != null && p == p.parent.left) {
            if (p.left != null) {
                //以p的左孩子顶替
                p.parent.left = p.left;
                //p的左孩子的parent域指向p的父母
                p.left.parent = p.parent;
            } else {
                //以p的右孩子顶替
                p.parent.left = p.right;
                if (p.right != null) {
                    p.right.parent = p.parent;
                }
            }
        }
        //p是1度或叶子结点，p是父母的右孩子
        if (p != null && p == p.parent.right) {
            if (p.left != null) {
                //以p的左孩子顶替
                p.parent.right = p.left;
                p.left.parent = p.parent;
            } else {
                //以p的右孩子顶替
                p.parent.right = p.right;
                if (p.right != null) {
                    p.right.parent = p.parent;
                }
            }
        }
        return p != null ? p.data : null;
    }

    /**
     * 删除根结点，返回原根结点元素
     *
     * @return
     */
    public T removeRoot() {
        return this.remove(this.root.data);
    }

    /**
     * 【实验题8-2】
     * 输出平均查找长度ASL（查找成功）及计算公式，二叉树的层次遍历算法
     */
    public void printASL() {
        System.out.print("ASL成功=(");
        //创建空队列
        LinkedQueue<TriNode<T>> que = new LinkedQueue<TriNode<T>>();
        int asl = 0, n = 0, count = 0, level = 1;
        //按层次遍历二叉树，根结点没有入队
        for (TriNode<T> p = this.root; p != null; p = que.poll()) {
            //若p结点层次为当前层次，则计数
            if (level(p) == level) {
                n++;    //当前层的结点个数
            } else {
                //输出上一层的计算公式
                System.out.print((asl == 0 ? "" : "+") + level + "*" + n);
                asl += level * n;
                //二叉树的结点个数
                count += n;
                //深一层
                level++;
                n = 1;
            }
            //p的左孩子结点入队
            if (p.left != null) {
                que.add(p.left);
            }
            //p的右孩子结点入队
            if (p.right != null) {
                que.add(p.right);
            }
        }
        if (count == 0) {
            System.out.println(") = 0");
        } else {
            //最后一层
            System.out.print("+" + level + "*" + n);
            asl += level * n;
            count += n;
            System.out.println(")/" + count + " =" + asl + "/" + count + " =" + ((asl + 0.0) / count));
        }
    }

    /**
     * 返回p结点所在的层次，令根结点的层次为1，若空树或未查找到x返回0
     *
     * @param p
     * @return
     */
    private int level(TriNode<T> p) {
        int level = 0;
        while (p != null) {
            level++;
            p = p.parent;
        }
        return level;
    }
}