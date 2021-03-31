package com.example.learn.liststudent;

import com.alibaba.fastjson.JSON;
import com.example.learn.liststudent.base.LinkNode;
import com.example.learn.liststudent.base.Person;
import com.example.learn.liststudent.base.Student;
import com.example.learn.liststudent.list.LinkList;
import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StudentTest {

    /**
     * 学生信息链表
     */
    private LinkList linkList = new LinkList();
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
        LinkList linkList = new LinkList(students);
        //new LinkList(students);
        linkList.printNode();
        int len = linkList.size();
        System.out.println("len ->" + len);
        //查找结点
        LinkNode linkNode = linkList.search(4);

        if (linkNode != null) {
            System.out.println(linkNode.data);
        }
        len = linkList.size();
        System.out.println("len ->" + len);
//        System.out.println(linkList.listToString2());
        //del
        //linkList.remove(new Person("stuNo4", "stuName4"));
        //
        linkList.delete(new Person("stuNo0", "stuName0"));
        //
//        System.out.println(linkList.listToString2());
        len = linkList.size();
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

    /**
     * @return
     * @throws Exception
     */
    public Student[] readStudentInfoFromDisk() throws Exception {
        FileReader fileReader = new FileReader(studentFilePath);
        int ch = 0;
        String context = "";
        while ((ch = fileReader.read()) != -1) {
            context += String.valueOf((char) ch);
        }
        fileReader.close();
        // JSON串转用户对象列表
        List<Student> studentList = JSON.parseArray(context, Student.class);
        Student[] students = new Student[studentList.size()];
        for (int i = 0; i < studentList.size(); i++) {
            students[i] = studentList.get(i);
        }
        return students;
    }

    /**
     * 显示学生基本信息
     */
    public void showStudentInto() throws Exception {
        //
        // Student[] students = linkList.ListToArrays();
        Student[] students = this.readStudentInfoFromDisk();
        if (students == null || students.length == 0) {
            System.out.println("无学生信息，返回上一级");
            System.out.println("");
            this.showPersonMenu();
            return;
        }
        // TODO: 2021/3/31  
        //如果存在记录同时加载到内存里面，给链表赋值
        //linkList
        linkList = new LinkList(students);
        //print
        System.out.println("=====学生信息如下=====");
        for (Student student : students) {
            System.out.println("学号：" + student.getNumber() + "\t姓名：" + student.getName());
        }
        System.out.println("======end =====");
        return;

    }

    /**
     * 添加学生
     *
     * @throws Exception
     */
    private void addStudent() throws Exception {
        System.out.println("添加学生信息.");
        System.out.println("=====显示信息====");
        System.out.println("输入学生编号（字符+数字 如:stu1）");
        System.out.println("");
        String number = "";
        String name = "";
        //用户输入
        Scanner scanner = new Scanner(System.in);
        number = scanner.nextLine();
        System.out.println("输入：" + number);
        System.out.println("=====显示信息====");
        System.out.println("输入学生姓名（字符如:yichuan）");
        System.out.println("");
        //用户输入
        scanner = new Scanner(System.in);
        name = scanner.nextLine();
        System.out.println("输入：" + name);
        Student student = new Student(number, name);
        if (name.isEmpty() || number.isEmpty()) {
            System.out.println("录入不合法");
            return;
        }
        linkList.add(student);
        System.out.println("学生集合长度：" + linkList.size());
//      保存学生信息到文件
        this.saveStudentInfoToDisk();
        //返回上一步
        this.showPersonMenu();
    }

    /**
     * 保存学生信息到文件
     */
    private void saveStudentInfoToDisk() throws Exception {
        Student[] students = linkList.ListToArrays();
        if (students == null || students.length == 0) {
            System.out.println("无写入内容到磁盘!");
            return;
        }
        String fileContent = JSON.toJSONString(students);
        FileWriter fileWriter = new FileWriter(studentFilePath);
        fileWriter.write(fileContent);
        fileWriter.close();
    }

    /**
     *
     */
    public void showPersonMenu() throws Exception {
        System.out.println("=====显示系统菜单====");
        System.out.println("\t 1.添加学生信息");
        System.out.println("\t 2.删除学生信息");
        System.out.println("\t 3.查看学生信息");
        System.out.println("\t 4.修改学生信息");
        System.out.println("\t 0.返回上一层");
        System.out.println("===================");
        System.out.println("请选择?(0-4)");
        //用户输入信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int orderNumber = scanner.nextInt();
            if (orderNumber < 0 || orderNumber > 4) {
                System.out.println("录入非法,exit...");
                System.exit(0);
            }
            switch (orderNumber) {
                case 0:
                    System.out.println("返回上一层.");
                    GOTO:
                    initMenu();
                    break;
                case 1:
                    System.out.println("添加学生信息.");
                    addStudent();
                    break;
                case 2:
                    System.out.println("删除学生信息.");
                    delStudent();
                    break;
                case 3:
                    System.out.println("查看学生信息.");
                    showStudentInto();
                    break;
                case 4:
                    System.out.println("修改学生信息.");
                    break;
                default:
                    return;
            }
        }
    }

    /**
     * 删除学生信息
     */
    private void delStudent() throws Exception {
        System.out.println("=====显示系统菜单====");
        System.out.println("\t 删除学生信息");
        System.out.println("\t 输入删除的学生编号");
        System.out.println("\t 输入 exit 返回");
        System.out.println("===================");
        //显示学生信息
        showStudentInto();
        //用户输入
        System.out.println("录入学号:");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String stuNumber = scanner.nextLine();
            if (stuNumber.equals("exit")) {
                //返回上一步
                this.showPersonMenu();
                return;
            }
            if (!stuNumber.isEmpty()) {
                //del
                linkList.delete(new Student(stuNumber, ""));

                // 把内存数据删除的写入文件
                // TODO: 2021/3/31 把内存数据删除的写入文件
                this.saveStudentInfoToDisk();
                //显示学生信息
                this.showStudentInto();
                //返回上一步

                this.showPersonMenu();

                break;
            }
        }

    }

    /**
     * @throws IOException
     */
    public void initMenu() throws Exception {
        System.out.println("=====显示系统菜单====");
        System.out.println("\t 1.人员信息管理");
        System.out.println("\t 2.班级信息管理");
        System.out.println("\t 3.课程信息管理");
        System.out.println("\t 4.成绩信息管理");
        System.out.println("\t 0.exit");
        System.out.println("===================");
        System.out.println("请选择?(0-4)");
        //用户输入信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int orderNumber = scanner.nextInt();
            if (orderNumber < 0 || orderNumber > 4) {
                System.out.println("录入非法,exit...");
                System.exit(0);
            }
            switch (orderNumber) {
                case 0:
                    System.out.println("退出系统.");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("人员信息管理.");
                    showPersonMenu();
                    break;
                case 2:
                    System.out.println("班级信息管理.");
                    break;
                case 3:
                    System.out.println("课程信息管理.");
                    break;
                case 4:
                    System.out.println("成绩信息管理.");
                    break;
                default:
                    System.out.println("录入非法");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            StudentTest studentTest = new StudentTest();
            studentTest.initMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
