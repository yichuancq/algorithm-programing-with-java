package com.example.learn.liststudent;

import com.alibaba.fastjson.JSON;
import com.example.learn.liststudent.base.Student;
import com.example.learn.liststudent.list.PersonLinkList;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StudentService {

    public StudentService() {
    }

    /**
     * 学生信息链表
     */
    private PersonLinkList personLinkList = new PersonLinkList();
    //保存文件的路径
    private final String filePath = "src/main/resources/student.txt";


    /**
     * 检查文件是否存在
     *
     * @return
     * @throws Exception
     */
    private boolean checkFile() throws Exception {
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        }
        System.out.println("请先录入数据!");
        return false;
    }


    /**
     * @return
     * @throws Exception
     */
    public Student[] readStudentInfoFromDisk() throws Exception {
        boolean flag = this.checkFile();
        if (!flag) {
            return null;
        }
        FileReader fileReader = new FileReader(filePath);
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
        Student[] students = this.readStudentInfoFromDisk();
        if (students == null || students.length == 0) {
            System.out.println("无信息，返回上一级");
            System.out.println("");
            this.showPersonMenu();
            return;
        }
        // TODO: 2021/3/31
        //如果存在记录同时加载到内存里面，给链表赋值
        //linkList
        personLinkList = new PersonLinkList(students);
        //print
        System.out.println("=====学生总人数如下=====");
        System.out.println("学生总人数：" + students.length);
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
        personLinkList.add(student);
        System.out.println("学生集合长度：" + personLinkList.size());
//      保存学生信息到文件
        this.saveStudentInfoToDisk();
        //返回上一步
        this.showPersonMenu();
    }

    /**
     * 保存学生信息到文件
     */
    private void saveStudentInfoToDisk() throws Exception {
        Student[] students = personLinkList.ListToArrays();
        if (students == null || students.length == 0) {
            System.out.println("无写入内容到磁盘!");
            return;
        }
        String fileContent = JSON.toJSONString(students);
        FileWriter fileWriter = new FileWriter(filePath);
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
                personLinkList.delete(new Student(stuNumber, ""));
                // 把内存数据删除的写入文件
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
                    // TODO: 2021/3/31  ClassesService
                    new ClassesService().showClassesMenu();
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
}
