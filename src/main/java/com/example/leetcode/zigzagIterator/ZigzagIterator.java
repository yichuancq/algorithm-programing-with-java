package com.example.leetcode.zigzagIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 锯齿迭代器
 */
public class ZigzagIterator {

    List<List<Integer>> data = new ArrayList<>(); //所有数据
    int size; //数据列表的总数
    int cur;  //当前遍历到的list的序号
    List<Integer> index = new ArrayList(); //每个list中当前遍历到的位置index

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        data.add(v1);
        data.add(v2);
        size = 2; //目前只有两个列表，可扩展为k个
        cur = 0;  //从第0个list开始遍历
        index.add(0);
        index.add(0);
    }

    public int next() {
        int ans = data.get(cur).get(index.get(cur)); //取出当前的数据
        index.set(cur, index.get(cur) + 1); //更改位置到下一个数据，有效性有hasNext来保证
        cur = (cur + 1) % size;
        return ans;
    }

    public boolean hasNext() {
        int start = cur; //保存目前的序号，用来判断是否所有list都已经遍历到了最后一个位置
        while (index.get(cur) == data.get(cur).size()) {
            cur = (cur + 1) % size;

            /**
             运行到下一个if语句，说明所有的list都在最后一个位置，cur回到了最开始进入while循环的位置
             此时，所有数据都已经遍历完成，返回false
             如果没有这一句，会进入无限循环
             */
            if (cur == start) return false;
        }
        return true;
    }

}
