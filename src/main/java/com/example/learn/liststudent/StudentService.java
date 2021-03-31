package com.example.learn.liststudent;

import com.alibaba.fastjson.JSON;
import com.example.learn.liststudent.base.*;
import com.example.learn.liststudent.list.PersonLinkList;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    /**
     * 学生信息链表
     */
    private PersonLinkList personLinkList = new PersonLinkList();
    //保存文件的路径
    private final String filePath = "src/main/resources/student.txt";
    //
    private BaseService baseService;

    public StudentService() {
    }

    public StudentService(BaseService baseService) {
        this.baseService = baseService;
    }

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
        //添加到内存链表
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
        System.out.println("\t 4.修改(编辑)学生信息");
        System.out.println("\t 0.返回上一层");
        System.out.println("===================");
        System.out.println("请选择?(0-4)");
        //用户输入信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int orderNumber = scanner.nextInt();
            if (orderNumber < 0 || orderNumber > 4) {
                System.out.println("录入非法,exit...");
                System.exit(0);
            }
            switch (orderNumber) {
                case 0:
                    System.out.println("返回上一层.");
                    this.initMenu();
                    break;
                case 1:
                    System.out.println("添加学生信息.");
                    this.showStudentInto();
                    this.addStudent();
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
                    editStudent();
                    break;
                default:
                    System.out.println("非法数字");
                    return;
            }
        }
    }

    private void searchPerson(String personNumber, String classesNumber) throws Exception {

        StudentClasses studentClasses = null;
        //通过对象查找元素
        Person personKey = new Person(personNumber, "");
        //查询学生信息
        LinkNode<Person> personLinkNode = personLinkList.search(personKey);
        if (personLinkNode == null) {
            System.out.println("查询失败， 无此记录~");
            //返回上一层
            this.editStudent();
            return;
        }
        Classes classesKey = new Classes(classesNumber, "");
        //查询班级信息
        Classes classes = new ClassesService().searchByKey(classesKey);
        if (classes == null) {
            System.out.println("查询失败， 无此记录~");
            //返回上一层
            this.editStudent();
            return;
        }//
        Student student = (Student) personLinkNode.data;
        String scNumber = ""; //自定义编码
        studentClasses = new StudentClasses(scNumber, classes.classesNumber,
                classes.classesName, student.getNumber(), student.getName());
        this.saveStudentClassesInfoToDisk(studentClasses);
        //返回上一层
        this.editStudent();
        return;

    }

    /**
     * 保存学生班级信息到磁盘
     * <p>
     * 1、添加到内存链表
     * 2、显示添加后链表长度
     * 3、保存到文件存档
     *
     * @param studentClasses
     */
    private void saveStudentClassesInfoToDisk(StudentClasses studentClasses) throws Exception {
        if (studentClasses == null) {
            return;
        }
        //添加到链表中，加载到内存
        baseService.getStudentClassesLinkList().add(studentClasses);
        //集合长度
        int size = baseService.getStudentClassesLinkList().size();
        System.out.println("集合长度：" + size);
//      保存学生信息到文件
        this.saveStudentClassesLinkNodesInfoToDisk();
    }


    /**
     * 集合保存到磁盘
     *
     * @throws Exception
     */
    private void saveStudentClassesLinkNodesInfoToDisk() throws Exception {
        StudentClasses[] classes = baseService.getStudentClassesLinkList().ListToArrays();
        if (classes == null || classes.length == 0) {
            System.out.println("无写入内容到磁盘!");
            return;
        }
        String fileContent = JSON.toJSONString(classes);
        FileWriter fileWriter = new FileWriter(baseService.studentClassesFilePath);
        fileWriter.write(fileContent);
        fileWriter.close();
    }


    /**
     * 编辑学生班级信息
     */
    public void editStudentClass() throws Exception {
        System.out.println("=====编辑学生班级信息,班级信息如下====");
        /**
         * 显示学生信息
         */
        this.showStudentInto();
        /***
         *显示班级信息
         */
        new ClassesService().showClassesInto();
        /***
         * 1用户录入学号，录入班级编号
         */
        System.out.println("请选择需要编辑的学号:");
        //学号
        String stuNumber = "";
        //班级编号
        String classesNumber = "";
        //用户输入
        Scanner scanner = new Scanner(System.in);
        stuNumber = scanner.nextLine();
        System.out.println("输入:" + stuNumber);
        System.out.println("=====显示信息====");
        System.out.println("输入班级编号");
        //用户输入
        scanner = new Scanner(System.in);
        classesNumber = scanner.nextLine();
        System.out.println("输入：" + classesNumber);
        if (stuNumber.isEmpty() || classesNumber.isEmpty()) {
            System.out.println("输入信息不完整！");
            //返回上一层
            this.editStudent();
        }
        //查询学生信息
        this.searchPerson(stuNumber, classesNumber);
        /***
         *1、通过学号和班级编号查询信息
         *2、生成学生班级实体信息，保存到文件内
         *3、显示成功记录到用户界面
         */
        this.editStudent();
        return;
    }

    /***
     * 修改学生信息
     *1、通过学号和班级编号查询信息
     *2、生成学生班级实体信息，保存到文件内
     *3、显示成功记录到用户界面
     */
    private void editStudent() throws Exception {
        System.out.println("=====显示修改学生信息菜单====");
        System.out.println("\t1 修改学生姓名信息");
        System.out.println("\t2 编辑学生班级信息");
        System.out.println("\t3 显示学生班级信息");
        System.out.println("\t0 返回上一层");
        System.out.println("===================");
        /**
         * 1 加载班级信息
         * 2 通过学号编辑姓名，如果不存在学生返回失败
         * 3 编辑学生班级信息，如果班级存在
         */
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int orderNumber = scanner.nextInt();
            if (orderNumber < 0 || orderNumber > 2) {
                System.out.println("录入非法.");
                break;
            }
            switch (orderNumber) {
                case 0:
                    System.out.println("返回上一层");
                    showPersonMenu();
                    break;
                case 1:
                    System.out.println("修改学生姓名信息");
                    //showPersonMenu();
                    break;
                case 2:
                    System.out.println("编辑学生班级信息");
                    this.editStudentClass();
                    break;
                case 3:
                    System.out.println("显示学生班级信息");
                    this.showStudentClassesInfo();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 显示学生班级信息关系记录
     *
     * @throws Exception
     */
    private void showStudentClassesInfo() throws Exception {
        // TODO: 2021/4/1 显示学生班级信息关系记录
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
            if (!stuNumber.isEmpty() && personLinkList.size() > 0) {
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
        while (scanner.hasNextInt()) {
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
