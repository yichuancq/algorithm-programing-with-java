package com.example.algorithm.sortedsingllist;

import com.example.algorithm.singlylist.Node;

/**
 * 求平均值等对排序单链表的操作
 */
public class SortedSinglyLists {

    /**
     * 返回list所有元素的平均值，输出计算公式和结果，每项格式为“值×值相同的元素个数”
     * 第8章用 二分法查找等算法，显示ASL成功公式
     *
     * @param list
     * @return
     */
    public static double average(SortedSinglyList<Integer> list) {
        if (list.isEmpty())
            //抛出无效参数异常
            throw new IllegalArgumentException("不能对空单链表计算平均值。");
        System.out.print("(");
        Node<Integer> p = list.head.next;
        //记录前驱结点元素，判断后继元素是否相同
        int value = p.data;
        //count记录值相同的元素个数
        int count = 1, sum = 0, length = 1;
        //遍历单链表
        for (p = p.next; p != null; p = p.next) {
            //若与前一个结点元素值相同
            if (p.data == value)
                count++;
            else {
                sum += value * count;
                //元素值求和
                System.out.print(value + "×" + count + "+");
                value = p.data;
                count = 1;
            }
            //统计单链表元素个数
            length++;
        }
        sum += value * count;
        //计算平均值；实数除，预处理，避免了除数为0错误
        double aver = (sum + 0.0) / length;
        System.out.println(value + "×" + count + ")/" + length + " = " + sum + "/" + length + " = " + aver);
        //返回平均值
        return aver;
    }

    public static void main(String[] args) {
        Integer[] values = {1, 2, 2, 3, 3, 3, 3, 4};              //图8.2 二叉判定树
        average(new SortedSinglyList<Integer>(values));
    }
}
