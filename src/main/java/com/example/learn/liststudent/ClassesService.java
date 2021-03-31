package com.example.learn.liststudent;


import com.alibaba.fastjson.JSON;
import com.example.learn.liststudent.base.Classes;
import com.example.learn.liststudent.list.ClassesLinkList;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;

/**
 * 班级信息
 *
 * @param <T>
 */
public class ClassesService<T> {
    /**
     *
     */
    public ClassesLinkList classesLinkList = new ClassesLinkList();

    //保存文件的路径
    private final String filePath = "src/main/resources/classes.txt";

    public ClassesService() {
    }

    /**
     * @param arrays
     */
    public ClassesService(T[] arrays) {
        classesLinkList = new ClassesLinkList(arrays);
    }

    /**
     * 显示链表的班级信息
     *
     * @return
     */
    public Classes[] showClassesInfo() {
        return classesLinkList.ListToArrays();
    }

    /**
     * 添加班级信息
     *
     * @throws Exception
     */
    private void addClasses() throws Exception {
        System.out.println("=====显示信息====");
        System.out.println("输入班级编号（字符+数字 如:cls）");
        System.out.println("");
        String classesNumber = "";
        String classesName = "";
        //用户输入
        Scanner scanner = new Scanner(System.in);
        classesNumber = scanner.nextLine();
        System.out.println("输入:" + classesNumber);
        System.out.println("=====显示信息====");
        System.out.println("输入班级名（字符如:计算机01）");
        System.out.println("");
        //用户输入
        scanner = new Scanner(System.in);
        classesName = scanner.nextLine();
        System.out.println("输入：" + classesName);
        Classes classes = new Classes(classesNumber, classesName);
        if (classesName.isEmpty() || classesNumber.isEmpty()) {
            System.out.println("录入不合法");
            return;
        }
        classesLinkList.add(classes);
        System.out.println("班级集合长度：" + classesLinkList.size());
//      保存信息到文件
        this.saveClassInfoToDisk();
        //返回上一步
        this.showClassesMenu();
    }

    /**
     * 保存信息到文件
     */
    private void saveClassInfoToDisk() throws Exception {

        Classes[] classes = classesLinkList.ListToArrays();
        if (classes == null || classes.length == 0) {
            System.out.println("无写入内容到磁盘!");
            return;
        }
        String fileContent = JSON.toJSONString(classes);
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(fileContent);
        fileWriter.close();
    }

    /**
     * 显示班级基本信息
     */
    public void showClassesInto() throws Exception {
        Classes[] classes = this.readInfoFromDisk();
        if (classes == null || classes.length == 0) {
            System.out.println("无信息，返回上一级");
            System.out.println("");
            this.showClassesMenu();
            return;
        }
        // TODO: 2021/3/31
        //如果存在记录同时加载到内存里面，给链表赋值
        //linkList
        classesLinkList = new ClassesLinkList(classes);
        //print
        System.out.println("=====班级数目如下=====");
        System.out.println("班级数目：" + classes.length);
        System.out.println("=====班级信息如下=====");
        for (Classes temp : classes) {
            System.out.println("班级编号：" + temp.classesNumber + "\t班级名称：" + temp.classesName);
        }
        System.out.println("======end =====");
        return;

    }

    /**
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
     * 读取磁盘数据
     *
     * @return
     * @throws Exception
     */
    private Classes[] readInfoFromDisk() throws Exception {
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
        List<Classes> classesList = JSON.parseArray(context, Classes.class);
        Classes[] classes = new Classes[classesList.size()];
        for (int i = 0; i < classesList.size(); i++) {
            classes[i] = classesList.get(i);
        }
        return classes;
    }

    /**
     * 显示班级信息
     */
    public void showClassesMenu() throws Exception {
        System.out.println("=====显示系统菜单====");
        System.out.println("\t 1.添加班级信息");
        System.out.println("\t 2.删除班级信息");
        System.out.println("\t 3.查看班级信息");
        System.out.println("\t 4.修改班级信息");
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
                    //todo 返回上一层
                    new StudentService().initMenu();
                    break;
                case 1:
                    System.out.println("添加班级信息.");
                    this.showClassesInto();
                    //
                    this.addClasses();
                    break;
                case 2:
                    System.out.println("删除班级信息.");
                    //delStudent();
                    break;
                case 3:
                    System.out.println("查看班级信息.");
                    showClassesInto();
                    break;
                case 4:
                    System.out.println("修改班级信息.");
                    break;
                default:
                    return;
            }
        }
    }


    public static void main(String[] args) {
        Classes[] classes = new Classes[]{
                new Classes("cs0001", "计算机01班级"),
                new Classes("cs0002", "计算机02班级"),
                new Classes("cs0003", "自动化01班级")};

        ClassesService service = new ClassesService(classes);
        Classes[] classesInfo = service.showClassesInfo();

        String jsonString = JSON.toJSONString(classesInfo);
        System.out.println(jsonString);

    }
}
