package com.example.learn.liststudent;

import com.alibaba.fastjson.JSON;
import com.example.learn.liststudent.base.LinkNode;
import com.example.learn.liststudent.base.Person;
import com.example.learn.liststudent.base.Student;
import com.example.learn.liststudent.list.PersonLinkList;
import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StudentTest {
    //保存文件的路径
    private final String studentFilePath = "src/main/resources/student.txt";

    @Test
    public void testWriteFile() throws Exception {
        int stuSize = 5;
        Student[] students = new Student[stuSize];
        for (int i = 0; i < stuSize; i++) {
            students[i] = new Student("stuNo" + i, "stuName" + i);
        }
        //JSON.toJSONString
        String fileContent = JSON.toJSONString(students);
        //Person newPerson = JSON.parseObject(jsonObject, Person.class);
        //

        System.out.println("json->" + fileContent);
        FileWriter fileWriter = new FileWriter(studentFilePath);
        fileWriter.write(fileContent);
        fileWriter.close();

    }


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
//        System.out.println(linkList.listToString2());
        //del
        //linkList.remove(new Person("stuNo4", "stuName4"));
        //
        personLinkList.delete(new Person("stuNo0", "stuName0"));
        //
//        System.out.println(linkList.listToString2());
        len = personLinkList.size();
        System.out.println("len ->" + len);
    }


    @Test
    public void readFile() {
        try {
            FileReader fileReader = new FileReader(studentFilePath);
            int ch = 0;
            String context = "";
            while ((ch = fileReader.read()) != -1) {
                System.out.print((char) ch);
                context += String.valueOf((char) ch);
            }
            fileReader.close();
            System.out.println();
            System.out.println("context" + context);
            // JSON串转用户对象列表
            List<Student> users2 = JSON.parseArray(context, Student.class);
            for (Student student : users2) {
                System.out.println(student.toString());
            }
        } catch (IOException e) {
            System.out.println("异常：" + e.toString());
        } finally {
        }

    }


    public static void main(String[] args) {
        try {
            StudentService studentTest = new StudentService();
            //

            studentTest.initMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
