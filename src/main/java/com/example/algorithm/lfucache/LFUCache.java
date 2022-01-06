package com.example.algorithm.lfucache;

import java.util.HashMap;
import java.util.Map;

/***
 * LFU算法的设计原则：
 * 1）put 和 get 操作，都算是使用，freq计数都得加1
 * 2）如果达到容量限制，移除freq最小的那个Cache
 * 3）移除时，如果有多个Cache的freq相同，那么移除最久未使用的那个（LRU）
 *
 * 设计思路：
 * 1）使用 Map 来存储Cache，做到get 的O(1) 时间复杂度。
 * 2）内部使用双链表节点维护缓存队列，列表头部是最久未使用的节点，淘汰时直接淘汰头部节点
 * 3）增加一个Map，存储每一个freq数字的尾节点Node对象引用。
 * 每一次操作 get 和 put 都需要对节点进行移动，具体来讲，是需要把该节点移动到对应freq子列表的尾节点（即最新使用的）
 * 这样就做到了get 和 put 操作时，移动节点耗时的 O(1) 时间复杂度。

 */
public class LFUCache {
    /**
     * key=key, val=valNode
     */
    private Map<Integer, Node> valMap;
    /**
     * key=freqNum 也就是调用次数
     * val=该次数的队伍尾部node指针
     */
    private Map<Integer, Node> freqNumMap;
    /**
     * 双端队列
     */
    private Node head;
    private Node tail;
    private int capacity;


    public LFUCache(int capacity) {
        this.capacity = capacity;
        valMap = new HashMap<>(capacity);
        freqNumMap = new HashMap<>(capacity);
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = valMap.get(key);
        if (node == null) {
            return -1;
        }
        freqNode(node, node.freq + 1);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = valMap.get(key);
        if (node == null) {
            if (valMap.size() == capacity) {
                deleteHead();
            }
            if (valMap.size() < capacity) {
                addNewNode(key, value);
            }
        } else if (node.val != value) {
            node.val = value;
            freqNode(node, node.freq + 1);
        }

    }


    /**
     * 给node设置freq 并且 根据freq重置在双端列表的位置
     *
     * @param node
     * @param newFreq
     */
    private void freqNode(Node node, int newFreq) {
        System.out.println("重置Node:" + node + "到freq:" + newFreq);
        // 校验修改 freqNumMap 指针
        checkAndRemoveFreqTail(node);
        // 找到旧freq对应的Node
        Node freqNode = findFreqNode(newFreq);
        node.freq = newFreq;
        insertNode(freqNode, node);
        // 添加node到freq
        freqNumMap.put(newFreq, node);

    }

    private Node findFreqNode(int freq) {
        Node freqNode = freqNumMap.get(freq--);
        while (freqNode == null && freq > 0) {
            freqNode = freqNumMap.get(freq--);
        }

        return freqNode == null ? head : freqNode;
    }

    private void addNewNode(int key, int val) {
        Node newNode = new Node(key, val, 1, null, null);
        insertNode(head, newNode);
        freqNode(newNode, 1);
        valMap.put(key, newNode);
    }

    private void deleteHead() {
        Node firstNode = head.next;
        if (firstNode == tail) {  // 到头了，删没了
            return;
        }
        // 移除valMap 的值
        valMap.remove(firstNode.key);

        // 判断freqNumMap, 如果是对应freqNum的尾节点,则更换尾节点
        checkAndRemoveFreqTail(firstNode);

        // 将节点从列表中移除(连接前后节点, 删除节点引用)
        firstNode.pre.next = firstNode.next;
        firstNode.next.pre = firstNode.pre;
        firstNode.pre = null;
        firstNode.next = null;
        System.out.println("删除Node" + firstNode);
        return;
    }

    private void insertNode(Node before, Node node) {
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        Node after = before.next;
        before.next = node;
        after.pre = node;
        node.pre = before;
        node.next = after;
    }

    private void checkAndRemoveFreqTail(Node node) {
        int freq = node.freq;
        if (node == freqNumMap.get(freq)) {
            Node preNode = node.pre;
            if (preNode != head && preNode.freq == freq) {
                freqNumMap.put(freq, preNode);
            } else {
                freqNumMap.remove(freq);
            }
        }
    }

    public static void main(String[] args) {

        LFUCache lfuCache = new LFUCache(10);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        int n = lfuCache.get(1);
        System.out.println(n);

    }

}