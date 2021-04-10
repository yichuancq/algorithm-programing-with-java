package com.example.algorithm.btree;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/***
 * btree
 * @param <K>
 * @param <V>
 */
public class BTree<K extends Comparable<K>, V> implements Serializable {
    //
    private static final long serialVersionUID = -6952311794625138776L;
    /**
     * 默认B-Tree度
     */
    private static final int DEFAULT_DEGREE = 3;
    /**
     * 度
     */
    public final int degree;
    //min Key
    public final int minKeys;
    //最大key
    public final int maxKeys;
    /**
     * 根节点
     */
    public Node<K, V> root;

    public BTree() {
        this(DEFAULT_DEGREE);
    }

    public BTree(int degree) {
        this.degree = degree;
        this.minKeys = degree - 1;
        this.maxKeys = 2 * degree - 1;
        this.root = new Node<>();
    }

    public V get(K key) {
        BPair<K, V> pair = this.search(key);
        return Optional.ofNullable(pair).map(BPair::getKey).map(Entry::getValue).orElse(null);
    }

    private BPair<K, V> search(K key) {
        return this.search(this.root, key);
    }

    /**
     * 打印树结点
     *
     * @param node
     */
    public void printTree(Node node) {
        if (node != null && !node.isLeaf()) {
            //获取孩子结点
            List<Node<K, V>> children = node.getChildren();
            //循环孩子结点
            for (Node tempNode : children) {
                //孩子结点集合的值
                List<Entry<K, V>> entryList = tempNode.getKeys();
                System.out.println(entryList);
                for (Entry entry : entryList) {
                    System.out.println("结点值:" + entry.value);
                }
                //递归
                printTree(tempNode);
            }
        }
    }

    /**
     * 检索节点
     */
    private BPair<K, V> search(Node<K, V> curNode, K key) {
        if (curNode == null || key == null) {
            return new BPair<>();
        }

        int slot = 0;
        for (int i = 0; i < curNode.keySize(); i++) {
            Entry<K, V> curKey = curNode.getKey(i);
            int compare = key.compareTo(curKey.key);
            if (compare == 0) {
                return new BPair<>(curNode, curKey);
            }
            if (compare < 0) {
                slot = i;
                break;
            } else if (compare > 0) {
                slot = i + 1;
            }
        }

        Node<K, V> findChild = curNode.getChild(slot);
        if (!curNode.isLeaf() && findChild != null) {
            return this.search(findChild, key);
        }
        return new BPair<>();
    }

    public V put(K key, V value) {
        return this.insert(this, new Entry<>(key, value));
    }

    /**
     * 插入节点；自上而下（根节点->叶子节点）查找要插入的位置，遇到满节点
     * 则进行分裂
     */
    private V insert(BTree<K, V> tree, Entry<K, V> key) {
        Objects.requireNonNull(tree, "tree can't be null");
        //一次分裂结果是分裂中间关键词提升到父节点位置，最终会提升至根节点位置
        //插入新数据时，根节点满的话，要提前分裂
        if (root.isFull()) {
            Node<K, V> newRoot = new Node<>();
            Node oldRoot = this.root;
            newRoot.addChild(oldRoot);
            this.root = newRoot;
            split(this.root, oldRoot);
        }
        return insertNonFull(this.root, key);
    }

    /**
     * 非满插入操作，根节点提前分裂，因此此方法插入不会导致根节点溢出
     *
     * @param curNode curNode
     */
    private V insertNonFull(Node<K, V> curNode, Entry<K, V> e) {
        Objects.requireNonNull(curNode, "node can't be null");
        Objects.requireNonNull(e, "key can't be null");

        if (curNode.isLeaf()) {
            curNode.addKey(e);
            return null;
        }

        //非叶子节点，要继续往下找到当前节点要要插入的子节点位置
        int slot = 0;
        for (int i = 0; i < curNode.keySize(); i++) {
            Entry<K, V> curKey = curNode.getKey(i);
            int compare = e.key.compareTo(curKey.key);
            if (compare == 0) {
                V oldValue = curKey.getValue();
                curKey.setValue(e.getValue());
                return oldValue;
            }
            if (compare < 0) {
                slot = i;
                break;
            } else if (compare > 0) {
                //比最后一个关键字还大
                slot = i + 1;
            }
        }

        Node<K, V> findChild = curNode.getChild(slot);
        if (findChild.isFull()) {
            //子节点是满节点要及时分裂
            split(curNode, findChild);
            //分裂后，需要重新计算插入关键字的位置
            return insertNonFull(curNode, e);
        } else {
            return insertNonFull(findChild, e);
        }
    }

    /**
     * @param parent
     * @param child
     */
    private void split(Node<K, V> parent, Node<K, V> child) {
        final int keySize = child.keySize();
        final int childSize = child.childSize();

        List<Entry<K, V>> leftKeys = new ArrayList<>(child.keys.subList(0, keySize / 2));
        List<Node<K, V>> leftNodes = new ArrayList<>(child.children.subList(0, childSize / 2));
        Node<K, V> leftSon = new Node<K, V>(parent, leftKeys, leftNodes);

        int rightKeySplitStartIndex = keySize > 0 ? keySize / 2 : 0;
        if (keySize % 2 == 1) {
            rightKeySplitStartIndex += 1;
        }
        int rightNodeSplitStartIndex = childSize > 0 ? childSize / 2 : 0;
        if (childSize % 2 == 1) {
            rightNodeSplitStartIndex += 1;
        }
        List<Entry<K, V>> rightKeys = new ArrayList<>(
                child.keys.subList(rightKeySplitStartIndex, keySize));
        List<Node<K, V>> rightNodes = new ArrayList<>(
                child.children.subList(rightNodeSplitStartIndex, childSize));
        Node<K, V> rightSon = new Node<K, V>(parent, rightKeys, rightNodes);

        Entry<K, V> middleKey = child.getKey(keySize / 2);
        int indexOfChild = parent.indexOfNode(child);
        parent.addKey(middleKey);
        parent.setNode(indexOfChild, leftSon);
        parent.addChild(rightSon);
    }

    /**
     * @param key
     * @return
     */
    public boolean remove(K key) {
        if (key == null) {
            return false;
        }
        Entry<K, V> out = remove(this.root, key, RmType.REMOVE_KEY);
        if (this.root.keySize() == 0 && this.root.childSize() > 0) {
            this.root = this.root.getChild(0);
        }
        return out != null;
    }

    /**
     * @param curNode
     * @param key
     * @param type
     * @return
     */
    private Entry<K, V> remove(Node<K, V> curNode, K key, RmType type) {
        if (curNode == null) {
            return null;
        }
        int i = 0;
        boolean found = false;

        switch (type) {
            case REMOVE_MAX: {
                if (curNode.childSize() == 0) {
                    return curNode.popKey();
                }
                i = curNode.keySize();
            }
            case REMOVE_MIN: {
                if (curNode.childSize() == 0) {
                    Entry<K, V> first = curNode.firstKey();
                    curNode.removeKey(0);
                    return first;
                }
                i = 0;
            }
            case REMOVE_KEY: {
                FindResult findResult = this.find(curNode.getKeys(), key);
                i = findResult.getIndex();
                found = findResult.getFound();
                if (curNode.childSize() == 0) {
                    if (found) {
                        return curNode.removeKey(i);
                    }
                    return null;
                }
            }
        }

        if (curNode.getChild(i).keySize() <= minKeys) {
            return this.growChildAndRemove(curNode, key, i, type);
        }

        Node<K, V> child = curNode.getChild(i);
        if (found) {
            Entry<K, V> out = curNode.getKey(i);
            Entry<K, V> rmMaxKey = remove(child, null, RmType.REMOVE_MAX);
            curNode.setKey(i, rmMaxKey);
            return out;
        }
        return remove(child, key, type);
    }

    /**
     * @param curNode
     * @param key
     * @param i
     * @param type
     * @return
     */
    private Entry<K, V> growChildAndRemove(Node<K, V> curNode, K key, int i, RmType type) {
        if (i > 0 && curNode.getChild(i - 1).keySize() > minKeys) {
            Node<K, V> child = curNode.getChild(i);
            Node<K, V> stealForm = curNode.getChild(i - 1);
            Entry<K, V> stolenKey = stealForm.popKey();
            child.addKey(curNode.getKey(i - 1));
            curNode.setKey(i - 1, stolenKey);
            if (stealForm.childSize() > 0) {
                child.addChild(stealForm.popChild());
            }
        } else if (i < curNode.keySize() && curNode.getChild(i + 1).keySize() > minKeys) {
            Node<K, V> child = curNode.getChild(i);
            Node<K, V> stealForm = curNode.getChild(i + 1);
            Entry<K, V> stolenKey = stealForm.firstKey();
            stealForm.removeKey(0);
            child.addKey(curNode.getKey(i));
            curNode.setKey(i, stolenKey);
            if (stealForm.childSize() > 0) {
                Node<K, V> firstNode = stealForm.firstNode();
                stealForm.removeChild(0);
                child.addChild(firstNode);
            }
        } else {
            if (i >= curNode.keySize()) {
                i--;
            }
            Node<K, V> child = curNode.getChild(i);
            Entry<K, V> mergeKey = curNode.removeKey(i);
            Node<K, V> mergeChild = curNode.getChild(i + 1);

            List<Entry<K, V>> mergeKeys = new ArrayList<>(maxKeys);
            mergeKeys.addAll(child.getKeys());
            mergeKeys.add(mergeKey);
            mergeKeys.addAll(mergeChild.getKeys());
            mergeKeys.sort(Comparator.comparing(e -> e.getKey()));

            child.setKeys(mergeKeys);
            child.addChildren(mergeChild.getChildren());

            curNode.removeChild(mergeChild);
        }

        return remove(curNode, key, type);
    }

    /***
     *
     * @param keys
     * @param key
     * @return
     */
    private FindResult find(List<Entry<K, V>> keys, K key) {
        if (keys == null || keys.size() == 0 || key == null) {
            return new FindResult(0, false);
        }
        List<K> keyList = keys.stream().map(Entry::getKey).collect(Collectors.toList());
        final int keySize = keyList.size();
        int i = 0;
        for (; i < keySize; i++) {
            int compare = key.compareTo(keyList.get(i));
            if (compare == 0) {
                return new FindResult(i, true);
            } else if (compare < 0) {
                return new FindResult(i, false);
            }
        }
        return new FindResult(i, false);
    }


    /**
     * B树节点
     *
     * @since 1.0.0
     */
    public class Node<K extends Comparable<K>, V> {

        private List<Entry<K, V>> keys;
        private List<Node<K, V>> children;
        private Node<K, V> parent;


        public void setChildren(List<Node<K, V>> children) {
            this.children = children;
        }

        public void setParent(Node<K, V> parent) {
            this.parent = parent;
        }

        private Node() {
            this(null);
        }

        private Node(Node<K, V> parent) {
            this(parent, new ArrayList<>(), new ArrayList<>());
        }

        private Node(Node<K, V> parent, List<Entry<K, V>> keys,
                     List<Node<K, V>> children) {
            this.parent = parent;
            this.keys = keys;
            this.children = children;
        }

        private List<Entry<K, V>> getKeys() {
            return keys;
        }

        private List<Node<K, V>> getChildren() {
            return children;
        }

        private Node<K, V> getParent() {
            return parent;
        }

        private int getH() {
            return this.parent == null ? 1 : this.parent.getH() + 1;
        }

        private boolean isLeaf() {
            return children == null || children.size() == 0;
        }

        private boolean isFull() {
            return keys.size() >= 2 * degree - 1;
        }

        private Entry<K, V> getKey(int index) {
            if (index < keys.size() && index >= 0) {
                return keys.get(index);
            }
            return null;
        }

        private Entry<K, V> firstKey() {
            final int size = keys.size();
            if (size == 0) {
                return null;
            }
            return keys.get(0);
        }

        private Entry<K, V> lastKey() {
            final int size = keys.size();
            if (size == 0) {
                return null;
            }
            return keys.get(size - 1);
        }

        private Entry<K, V> popKey() {
            Entry<K, V> last = lastKey();
            this.removeKey(keySize() - 1);
            return last;
        }

        private Node<K, V> getChild(int index) {
            if (index < children.size() && index >= 0) {
                return children.get(index);
            }
            return null;
        }

        private Node<K, V> firstNode() {
            final int size = children.size();
            if (size == 0) {
                return null;
            }
            return children.get(0);
        }

        private Node<K, V> lastNode() {
            final int size = children.size();
            if (size == 0) {
                return null;
            }
            return children.get(size - 1);
        }

        private Node<K, V> popChild() {
            Node<K, V> last = lastNode();
            this.removeChild(keySize() - 1);
            return last;
        }

        private K getMaxKey() {
            if (keys == null || keys.size() == 0) {
                return null;
            }
            final int size = keys.size();
            return keys.get(size - 1).key;
        }

        private V getValue(int index) {
            Entry<K, V> entry = this.getKey(index);
            return entry == null ? null : entry.value;
        }

        private int keySize() {
            return keys == null ? 0 : keys.size();
        }

        private int childSize() {
            return children == null ? 0 : children.size();
        }

        private int indexOfKey(K key) {
            if (keys == null || keys.size() == 0 || key == null) {
                return -1;
            }
            List<K> kList = keys.stream().map(Entry::getKey).collect(Collectors.toList());
            return kList.indexOf(key);
        }

        private int indexOfKey(Entry<K, V> key) {
            if (keys != null && keys.size() > 0 && key != null) {
                return keys.indexOf(key);
            }
            return -1;
        }

        private int indexOfNode(Node<K, V> child) {
            if (children != null && children.size() > 0 && child != null) {
                return children.indexOf(child);
            }
            return -1;
        }

        private void setKey(int index, Entry<K, V> key) {
            keys.set(index, key);
        }

        private void setKeys(List<Entry<K, V>> keys) {
            this.keys = keys;
        }

        private void setNode(int index, Node<K, V> node) {
            children.set(index, node);
        }

        private void addKey(Entry<K, V> e) {
            if (e == null) {
                return;
            }
            this.keys.add(e);
            this.sortKeys();
        }

        private void addKeys(List<Entry<K, V>> eList) {
            if (eList == null || eList.size() == 0) {
                return;
            }
            this.keys.addAll(eList);
            this.sortKeys();
        }

        private void addChild(Node<K, V> child) {
            if (child == null) {
                return;
            }
            this.children.add(child);
            this.sortChildren();
        }

        private void addChildren(List<Node<K, V>> childList) {
            if (childList == null || childList.size() == 0) {
                return;
            }
            this.children.addAll(childList);
            this.sortChildren();
        }

        private Entry<K, V> removeKey(int index) {
            if (index >= 0 && index < keys.size()) {
                return keys.remove(index);
            }
            return null;
        }

        private Entry<K, V> removeKey(K key) {
            if (key == null) {
                return null;
            }
            int found = -1;
            for (int i = 0; i < keys.size(); i++) {
                if (key.compareTo(keys.get(i).key) == 0) {
                    found = i;
                    break;
                }
            }
            if (found != -1) {
                return removeKey(found);
            }
            return null;
        }

        private boolean removeKey(Entry<K, V> e) {
            if (e == null) {
                return false;
            }
            return keys.remove(e);
        }

        private Node<K, V> removeChild(int index) {
            if (index >= 0 && index < children.size()) {
                return children.remove(index);
            }
            return null;
        }

        private boolean removeChild(Node<K, V> child) {
            if (child == null) {
                return false;
            }
            return this.children.remove(child);
        }

        private void sortKeys() {
            if (keys != null && keys.size() > 0) {
                keys.sort(Comparator.comparing(o -> o.key));
            }
        }

        private void sortChildren() {
            if (children != null && children.size() > 0) {
                children.sort(Comparator.comparing(o -> o.getMaxKey()));
            }
        }

    }


}