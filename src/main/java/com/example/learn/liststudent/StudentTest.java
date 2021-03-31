package com.example.learn.liststudent;

import com.alibaba.fastjson.JSON;
import com.example.learn.liststudent.base.LinkNode;
import com.example.learn.liststudent.base.Person;
import com.example.learn.liststudent.base.Student;
import com.example.learn.liststudent.list.PersonLinkList;
import org.junit.Test;

public class StudentTest {

    @Test
    public void testPerson() {

        int stuSize = 5;
        Student[] students = new Student[stuSize];
        for (int i = 0; i < stuSize; i++) {
            students[i] = new Student("stuNo" + i, "stuName" + i);
        }
        String person = JSON.toJSONString(students);
        //
        System.out.println("json->" + person);
        PersonLinkList personLinkList = new PersonLinkList(students);
        //new LinkList(students);
        personLinkList.printNode();
        int len = personLinkList.size();
        System.out.println("len ->" + len);
        //查找结点
        LinkNode linkNode = personLinkList.search(4);

        if (linkNode != null) {
            System.out.println(linkNode.data);
        }
        len = personLinkList.size();
        System.out.println("len ->" + len);
        personLinkList.delete(new Person("stuNo0", "stuName0"));
        len = personLinkList.size();
        System.out.println("len ->" + len);
    }

    public static void main(String[] args) {
        try {
            StudentService studentTest = new StudentService();
            studentTest.initMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
