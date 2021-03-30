# java-learning
>java 数据结构，oop，算法，学习笔记

>线性表

```java
/**
 * 线性表
 */
public class SequenceList<T> {
    private Object[] elementData;
    //默认大小
    private int defaultSize = 0;
    //数组大小
    private int size = 0;
    //数组容量
    private int capacity;

    /**
     * 初始化
     */
    public SequenceList() {
        capacity = this.defaultSize;
        elementData = new Object[capacity];
    }

    public SequenceList(T element) {
        this();
        elementData[0] = element;
        size++;
    }
}
```

>栈和队列
```java
public interface Stack<T> {
    /**
     * 判断栈是否空
     *
     * @return
     */
    public abstract boolean isEmpty();

    /**
     * 元素x入栈
     */
    public abstract void push(T x);

    /**
     * 返回栈顶元素，未出栈
     *
     * @return
     */

    public abstract T peek();

    /**
     * 出栈，返回栈顶元素
     * @return
     */
    public abstract T pop();
}

```

>树和二叉树

```java
/**
 * 树的根节点
 *
 * @param <T>
 */
public class Node<T> {
    /**
     * 结点的值
     */
    private T data;
    /**
     * 左子结点
     */
    private Node<T> rightChild;
    /**
     * 右子结点
     */
    private Node<T> leftChild;


    public Node(T data) {
        this.data = data;
    }

    /**
     * @param data
     * @param leftChild
     * @param rightChild
     */
    public Node(T data, Node<T> leftChild, Node<T> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}
```
> 图

```java
public class graph {
    //点个数
    public int vertexNum;
    //边个数
    public int edgeLength;
    public Vertex[] vertexList; //存放点的集合
    public graph(int vertexNum){
        this.vertexNum=vertexNum;
        vertexList=new Vertex[vertexNum];
    }

    public void initVertext(){
        for(int i=0;i<vertexNum;i++){
            Vertex vertext=new Vertex();
            vertext.firstEdge=null;
            vertexList[i]=vertext;
            //System.out.println("i"+vertexList[i]);
        }
    }
    //针对x节点添加边节点y
    public void addEdge(int x,int y,int weight){
        Edge edge=new Edge();
        edge.setVertexId(y);
        edge.setWeight(weight);
        //第一个边节点
        if(null==vertexList[x].firstEdge){
            vertexList[x].firstEdge=edge;
            edge.setNext(null);
        }
        //不是第一个边节点,则采用头插法
        else{
            edge.next=vertexList[x].firstEdge;
            vertexList[x].firstEdge=edge;
        }
    }
}

```
> 查找和排序

```java
 public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        /**
         *查找 当前链表的 “中点”
         */
        ListNode fastNode = head;
        ListNode slowHead = head;
        while (fastNode.next != null && fastNode.next.next != null) {
            slowHead = slowHead.next;
            fastNode = fastNode.next.next;
        }

        ListNode midNode = slowHead.next;
        slowHead.next = null;   // 将 两半链表 断开
        ListNode leftHead = sortList(head);
        ListNode rightHead = sortList(midNode);
        return merge(leftHead, rightHead);
 }
```
> leetcode 算法题解

```java

```