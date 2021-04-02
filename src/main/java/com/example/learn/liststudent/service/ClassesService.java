package com.example.learn.liststudent.service;


import com.alibaba.fastjson.JSON;
import com.example.learn.liststudent.base.Classes;
import com.example.learn.liststudent.base.LinkNode;
import com.example.learn.liststudent.repository.ClassesRepository;
import com.example.learn.liststudent.utils.Utils;

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

    public BaseService baseService;

    public ClassesService() {
    }

    public ClassesService(BaseService baseService) {
        this.baseService = baseService;
    }

    /**
     * 添加班级信息
     *
     * @throws Exception
     */
    private void addClasses() throws Exception {
        System.out.println("=====显示信息====");
        String classesNumber = Utils.getInnerId("CLS");
        System.out.println("系统生成的班级编号:" + classesNumber);
        String classesName = "";
        //用户输入
        System.out.println("=====显示信息====");
        System.out.println("输入班级名（字符如:计算机01）");
        System.out.println("");
        //用户输入
        Scanner  scanner = new Scanner(System.in);
        classesName = scanner.nextLine();
        System.out.println("输入：" + classesName);
        if (classesName.isEmpty()) {
            System.out.println("录入不合法");
            return;
        }
        Classes classes = new Classes(classesNumber, classesName);
        this.baseService.getClassesRepository().add(classes);
        System.out.println("班级集合长度：" + this.baseService.getClassesRepository().size());
//      保存信息到文件
        this.saveClassInfoToDisk();

    }

    /**
     * 保存信息到文件
     */
    private void saveClassInfoToDisk() throws Exception {
        Classes[] classes = this.baseService.getClassesRepository().listToArrays();
        if (classes == null || classes.length == 0) {
            System.out.println("无写入内容到磁盘!");
            return;
        }
        String fileContent = JSON.toJSONString(classes);
        FileWriter fileWriter = new FileWriter(baseService.classesFilePath);
        fileWriter.write(fileContent);
        fileWriter.close();
    }


    /**
     * 加载磁盘数据填充链表
     *
     * @throws Exception
     */
    private void loadData() throws Exception {
        Classes[] classes = this.readInfoFromDisk();
        if (classes == null || classes.length == 0) {
            System.out.println("无信息，返回上一级");
            return;
        }
        baseService.setClassesRepository(new ClassesRepository(classes));
    }

    /**
     * 查询单个信息
     *
     * @param classesKey
     * @return
     */
    public Classes searchByKey(Classes classesKey) throws Exception {
        this.loadData();
        Classes classes = null;
        LinkNode<Classes> classesNode = baseService.getClassesRepository().search(classesKey);
        if (classesNode == null) {
            return classes;
        }
        return classesNode.data;
    }

    /**
     * 显示班级基本信息
     * <p>
     * 1、先加载磁盘数据
     * 2、如果存在记录同时加载到内存里面，给链表赋值
     * 3、显示到用户界面
     */
    public void showClassesInto() throws Exception {
        Classes[] classes = this.readInfoFromDisk();
        if (classes == null || classes.length == 0) {
            System.out.println("无信息，返回上一级");
            System.out.println("");
            this.showClassesMenu();
            return;
        }
        //如果存在记录同时加载到内存里面，给链表赋值
        baseService.setClassesRepository(new ClassesRepository(classes));
        //print
        System.out.println("=====班级数目如下=====");
        System.out.println("班级数目：" + classes.length);
        System.out.println("=====班级信息如下=====");
        for (Classes temp : classes) {
            String createTime = Utils.getStringFormatDate(temp.getCreateTime());
            String updateTime = Utils.getStringFormatDate(temp.getUpdateTime());
            System.out.println("班级编号：" + temp.classesNumber + "\t班级名称：" + temp.classesName+
                    "\t" + "添加日期：" + createTime + "\t修改日期：" + updateTime);
        }
        System.out.println("======end=====");

    }

    /**
     * 读取磁盘数据
     *
     * @return
     * @throws Exception
     */
    private Classes[] readInfoFromDisk() throws Exception {
        boolean flag = Utils.checkFile(baseService.classesFilePath);
        if (!flag) {
            return null;
        }
        FileReader fileReader = new FileReader(baseService.classesFilePath);
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
     * 显示班级信息菜单
     */
    public void showClassesMenu() throws Exception {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\r\n=====显示系统菜单====\r\n");
        stringBuilder.append("1.添加班级信息\r\n");
        stringBuilder.append("2.删除班级信息\r\n");
        stringBuilder.append("3.查看班级信息\r\n");
        stringBuilder.append("4.修改班级信息\r\n");
        stringBuilder.append("0.返回上一层\r\n");
        stringBuilder.append("请选择?(0-4)\r\n");
        stringBuilder.append("===================\r\n");
        String disInfo = stringBuilder.toString();
        System.out.println(disInfo);
        //用户输入信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int orderNumber = scanner.nextInt();
            if (orderNumber < 0 || orderNumber > 4) {
                System.out.println("录入非法,exit...");
                return;
            }
            switch (orderNumber) {
                case 0:
                    System.out.println("返回上一层.");
                    new StudentService(baseService).initMenu();
                    break;
                case 1:
                    System.out.println("添加班级信息.");
                   // this.showClassesInto();
                    //
                    this.addClasses();
                    //show menu
                    System.out.println(disInfo);
                    break;
                case 2:
                    System.out.println("删除班级信息.");
                    break;
                case 3:
                    System.out.println("查看班级信息.");
                    //显示信息
                    this.showClassesInto();
                    System.out.println(disInfo);
                    break;
                case 4:
                    System.out.println("修改班级信息.");
                    break;
                default:
                    return;
            }
        }
    }
}
