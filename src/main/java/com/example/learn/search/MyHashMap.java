package com.example.learn.search;

/***
 * 数据结构之哈希表
 * @param <K>
 * @param <V>
 */
public class MyHashMap<K, V> implements MyMap<K, V> {

    /**
     * 存储所有的键值对
     */
    private EntryNode<K, V>[] nodes;

    /**
     * 当前哈希表的大小
     */
    private int size;

    /**
     * 负载因子，负载因子决定了hash表何时进行resize（扩容）操作
     */
    private final float loadFactor;

    /**
     * 默认的哈希表容量
     */
    private final static int DEFAULT_CAPACITY = 16;

    /**
     * 扩容翻倍的基数
     */
    private final static int REHASH_BASE = 2;

    /**
     * 默认的负载因子
     */
    private final static float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 使用默认容量初始化
     */
    public MyHashMap() {
        this.size = 0;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        nodes = new EntryNode[DEFAULT_CAPACITY];
    }

    /**
     * 指定初始化的容量大小
     *
     * @param capacity 初始化的容量
     */
    public MyHashMap(int capacity) {
        this.size = 0;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        nodes = new EntryNode[capacity];
    }

    /**
     * 通过hash计算获取数组的下标
     *
     * @param key key
     * @return hash桶坐标
     */
    private int getIndex(K key) {
        return getIndex(key, this.nodes);
    }

    /**
     * 通过hash计算获取数组的下标
     *
     * @param key   key
     * @param nodes 节点
     * @return hash桶坐标
     */
    private int getIndex(K key, EntryNode<K, V>[] nodes) {
        if (key == null) {
            return 0;
        } else {
            int hashCode = key.hashCode();
            // 异或运算，获得最终的hash映射，减少碰撞的几率
            int finalHashCode = hashCode ^ (hashCode >>> 16);
            return (nodes.length - 1) & finalHashCode;
        }
    }

    /**
     * 试图获得目标节点，获取不到则返回链表的最后一个节点
     *
     * @param currentNode 链表的第一个节点
     * @param key         key
     * @return EntryNode
     */
    private EntryNode<K, V> getTargetEntryNode(EntryNode<K, V> currentNode, K key) {
        // 不匹配
        EntryNode<K, V> nextNode = currentNode.next;
        // 遍历当前桶后面的所有节点
        while (true) {
            if (nextNode == null || currentNode.key.equals(key)) {
                return currentNode;
            }
            currentNode = nextNode;
            nextNode = currentNode.next;
        }
    }

    @Override
    public V put(K k, V v) {
        //创建键值对
        EntryNode<K, V> entryNode = new EntryNode<>(k, v);
        //通过key获取数组的下标
        int index = getIndex(k);
        EntryNode<K, V> firstNode = this.nodes[index];

        //若是此节点不存在数据，则直接插入该数据作为第一个节点
        if (firstNode == null) {
            this.nodes[index] = entryNode;
            this.size++;
            if (this.size > this.nodes.length * this.loadFactor) {
                this.resize();
            }
            return v;
        }

        // 若是此桶的第一个节点就是当前的key，就将该key对应的value替换掉，并且返回此oldValue
        if (firstNode.equalsKey(k)) {
            V oldValue = firstNode.value;
            firstNode.value = v;
            return oldValue;
            //若是不匹配，则去链表的下一个节点查找，若是全部不匹配，则新增一个节点放在链表的末尾
        } else {
            EntryNode<K, V> targetEntryNode = getTargetEntryNode(firstNode, k);
            // 若是查找到了key相同的节点，则替换节点的value
            if (targetEntryNode.key.equals(k)) {
                V oldValue = targetEntryNode.value;
                targetEntryNode.value = v;
                return oldValue;
                //未查询到匹配的节点
            } else {
                targetEntryNode.next = entryNode;
                this.size++;
                if (this.size > this.nodes.length * this.loadFactor) {
                    this.resize();
                }
                return v;
            }
        }
    }

    @Override
    public V remove(K k) {

        int index = getIndex(k);
        EntryNode<K, V> firstNode = this.nodes[index];

        if (null == firstNode) {
            return null;
        }

        // 链表第一个节点即为要移除的节点
        if (firstNode.key.equals(k)) {
            this.nodes[index] = firstNode.next;
            System.out.println("移除了元素：" + k);
            this.size--;
            return firstNode.value;
            // 链表第一个节点不是要移除的节点
        } else {
            // 从第二个节点开始遍历
            EntryNode<K, V> preEntryNode = firstNode;
            EntryNode<K, V> curEntryNode = firstNode.next;
            while (null != curEntryNode) {
                if (curEntryNode.key.equals(k)) {
                    break;
                }
                // 若是下一个节点为空，则说明链表遍历完成，但是也没有匹配到对应的节点
                if (null == curEntryNode.next) {
                    return null;
                }
                preEntryNode = curEntryNode;
                curEntryNode = curEntryNode.next;
            }

            // 将其之前的节点连接到他的下一个节点上
            if (null != curEntryNode) {
                System.out.println("移除了元素：" + k);
                this.size--;
                preEntryNode.next = curEntryNode.next;
                return curEntryNode.value;
            }
            return null;
        }
    }

    @Override
    public V get(K k) {
        int index = getIndex(k);
        EntryNode<K, V> node = this.nodes[index];
        while (null != node) {
            if (node.key.equals(k)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public boolean containsKey(K k) {
        int index = getIndex(k);
        EntryNode<K, V> node = this.nodes[index];
        while (null != node) {
            if (node.key.equals(k)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(V v) {
        for (EntryNode<K, V> node : this.nodes) {
            while (null != node) {
                if (node.value.equals(v)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        for (EntryNode<K, V> node : this.nodes) {
            if (null != node) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void clear() {
        this.nodes = new EntryNode[this.nodes.length];
    }

    private void resize() {
        EntryNode<K, V>[] resizeArray = this.nodes;
        this.nodes = new EntryNode[this.nodes.length * REHASH_BASE];
        this.size = 0;
        for (EntryNode<K, V> node : resizeArray) {
            while (null != node) {
                this.put(node.key, node.value);
                node = node.next;
            }
        }
        System.out.println("hash表已经扩容，目前容量：" + this.nodes.length);
    }
}
