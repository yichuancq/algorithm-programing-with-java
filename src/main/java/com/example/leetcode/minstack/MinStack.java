package com.example.leetcode.minstack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x)——将元素 x 推入栈中。
 * pop()——删除栈顶的元素。
 * top()——获取栈顶元素。
 * getMin()——检索栈中的最小元素。
 * <p>
 * example:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
public class MinStack {
    private List<Integer> integerList = new ArrayList<>();

    public MinStack() {
        if (!integerList.isEmpty()) {
            integerList.clear();
        }
    }

    /**
     * 压栈
     *
     * @param val
     */
    public void push(int val) {
        integerList.add(val);
    }

    /**
     * 出栈
     */
    public void pop() {
        if (integerList.isEmpty()) {
            return;
        }
        int index = integerList.size();
        integerList.remove(index - 1);
    }

    /**
     * 获取栈顶元素
     *
     * @return
     */
    public int top() {
        if (integerList.isEmpty()) {
            return 0;
        }
        int index = integerList.size();
        return integerList.get(index - 1);

    }

    /**
     * 获取最小栈元素
     *
     * @return
     */
    public int getMin() {
        if (integerList.isEmpty()) {
            return 0;
        }
        Collections.sort(integerList);
        return integerList.get(0);
    }

//    public int getMin() {
//        if (integerList.isEmpty()) {
//            return 0;
//        }
//        int minVal = integerList.get(0);
//        for (Integer integerElement : integerList) {
//            if (integerElement <= minVal) {
//                minVal = integerElement;
//            }
//        }
//        return minVal;
//    }

    public int stackSize() {
        return integerList.size();
    }

    public static void main(String[] args) {

        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(-99);
        minStack.push(2);
        minStack.push(3);
        System.out.println(String.format("stack size:%d", minStack.stackSize()));
        minStack.pop();
        System.out.println(String.format("stack size:%d", minStack.stackSize()));
        int top = minStack.top();
        System.out.println(String.format("stack top element:%d", top));
        //
        System.out.println(String.format("stack min element:%d", minStack.getMin()));

        /**
         * Your MinStack object will be instantiated and called as such:
         * MinStack obj = new MinStack();
         * obj.push(val);
         * obj.pop();
         * int param_3 = obj.top();
         * int param_4 = obj.getMin();
         */
    }


}
