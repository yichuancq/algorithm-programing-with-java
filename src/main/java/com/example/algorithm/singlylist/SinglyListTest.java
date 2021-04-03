package com.example.algorithm.singlylist;

import org.junit.Test;

public class SinglyListTest {


    @Test
    public void test() {
        String[] strings = {"a", "b", "c", "d", "e", "f", "g"};
        SinglyList singlyList = new SinglyList(strings);
        boolean isEmpty = singlyList.isEmpty();
//        Object data = singlyList.getElement(1);
//        System.out.println("data:" + data);
//        System.out.println("isEmpty:" + isEmpty);
//        int listSize = singlyList.singleListSize();
//        System.out.println("listSize:" + listSize);
//        System.out.println("遍历显示结点");
//        singlyList.showNodes();
//        System.out.println("替换元素");
//        singlyList.replaceElement(2, "cc");
//        System.out.println("遍历显示结点");
//        singlyList.showNodes();
//        System.out.println("递归显示结点");
//        singlyList.getChildNode(singlyList.head);
//        System.out.println("遍历打印元素方式1");
//        System.out.println(singlyList.listToString());
//        System.out.println("遍历打印元素方式2");
//        System.out.println(singlyList.listToString2());
//        System.out.println("插入结点元素");
//        //插入结点元素
//        singlyList.insertHead("ss");
//        System.out.println("遍历显示结点");
//        singlyList.showNodes();
//        System.out.println("遍历打印元素方式2");
//        //尾部插入
//        singlyList.insertTail("z");
//        System.out.println("遍历打印元素方式2");
//        System.out.println(singlyList.listToString2());
//        listSize = singlyList.singleListSize();
//        System.out.println("listSize:" + listSize);
//        //
//        singlyList.insert(2, "new");
//        System.out.println("遍历打印元素方式2");
//        System.out.println(singlyList.listToString2());
//        //移除指定索引的元素


//        singlyList.removeElement(3);
////        singlyList.removeElement2(3);
//        //
//        System.out.println("遍历打印元素方式2");
//        System.out.println(singlyList.listToString2());
//        //删除元素
//        System.out.println("删除匹配值相同的结点元素");
//        singlyList.removeElement("cc");
//        System.out.println("遍历打印元素方式2");
//        System.out.println(singlyList.listToString2());
//        //获取某个元素
//        int index = singlyList.getElementIndex("ss");
//        System.out.println("index" + index);
//        singlyList.removeElement2(index);
//        System.out.println("遍历打印元素方式2");
//        System.out.println(singlyList.listToString2());
        //反转链表
//        System.out.println("反转链表");
//        singlyList.reverse();
        System.out.println("遍历打印元素方式2");
        System.out.println(singlyList.listToString2());
        ////
//        System.out.println("反转链表");
//        singlyList.reverse2();

        //清空链表
        System.out.println("删除结点元素");
        singlyList.remove("g");
        System.out.println("遍历打印元素方式2");
        System.out.println(singlyList.listToString2());
        singlyList.clear();
    }
}
