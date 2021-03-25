package com.example.learn.stack;

import org.junit.Test;

public class LinkedStackTest {

    @Test
    public void test2() {
        SeqStack<String> stack1 = new SeqStack<String>(20); //构造空栈
        System.out.print("Push: ");
        char ch = 'a';
        for (int i = 0; i < 5; i++) {
            String str = (char) (ch + i) + "";
            stack1.push(str);
            System.out.print(str + "  ");
        }

        LinkedStack<Integer> stack2 = new LinkedStack<Integer>();
        System.out.print("\nPush: ");
        for (int i = 1; i <= 5; i++) {
            Integer intobj = new Integer(i);
            stack2.push(intobj);
            System.out.print(intobj + "  ");
        }

        System.out.println("\nStack: " + stack2.toString());
        System.out.print("Pop : ");
        while (!stack2.isEmpty())                  //全部出栈
            System.out.print(stack2.pop().toString() + "  ");
        System.out.println();
    }


    @Test
    public void test() {
        LinkedStack linkedStack = new LinkedStack();
        //压入元素
        linkedStack.push("A");
        //压入元素
        linkedStack.push("B");
        //压入元素
        linkedStack.push("C");
        System.out.println("" + linkedStack.isEmpty());
        linkedStack.pop();
        System.out.println("top val:" + linkedStack.peek());
        //出栈
        linkedStack.pop();
        System.out.println("top val:" + linkedStack.peek());

    }
}
