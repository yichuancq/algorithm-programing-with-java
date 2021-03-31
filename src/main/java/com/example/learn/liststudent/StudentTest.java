package com.example.learn.liststudent;

import com.example.learn.liststudent.base.LinkNode;
import com.example.learn.liststudent.base.Person;
import com.example.learn.liststudent.base.Student;
import com.example.learn.liststudent.list.LinkList;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

public class StudentTest {

    /**
     * 学生信息链表
     */
    private LinkList linkList = new LinkList();


    @Test
    public void testPerson() {

        int stuSize = 5;
        Student[] students = new Student[stuSize];
        for (int i = 0; i < stuSize; i++) {
            students[i] = new Student("stuNo" + i, "stuName" + i);
        }

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
        System.out.println(linkList.listToString2());
        //del
        //linkList.remove(new Person("stuNo4", "stuName4"));
        //
        linkList.delete(new Person("stuNo0", "stuName0"));
        //
        System.out.println(linkList.listToString2());
        len = linkList.size();
        System.out.println("len ->" + len);
    }

    /**
     * 显示学生基本信息
     */
    public void showStudentInto() throws Exception {
        int len = linkList.size();
        if (len <= 0) {
            System.out.println("无学生信息，返回上一级");
            System.out.println("");
            this.showPersonMenu();

        } else {
            linkList.printNode();
        }

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
        //返回上一步
        this.showPersonMenu();
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
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String stuNumber = scanner.nextLine();
            if (stuNumber.equals("exit")) {
                //返回上一步
                this.showPersonMenu();
            }
            if (!stuNumber.isEmpty()) {
                //del
                linkList.delete(new Student(stuNumber, ""));
                //显示学生信息
                showStudentInto();
                //返回上一步
                this.showPersonMenu();
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
