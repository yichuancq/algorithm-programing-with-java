package com.example.learn.liststudent.service;

import java.util.Scanner;

/**
 * 课程服务类
 */
public class CourseService {
    /**
     * 基础服务类
     */
    public BaseService baseService;

    public CourseService(BaseService baseService) {
        this.baseService = baseService;
    }
    private void delCourse() throws Exception {
    }

    private void addCourse() throws Exception {
    }

    private void showCourseInto() throws Exception {
    }
    /**
     * 显示课程信息菜单
     */
    public void showCourseMenu() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\r\n=====显示课程信息菜单====\r\n");
        stringBuilder.append("1.添加课程信息\r\n");
        stringBuilder.append("2.删除课程信息\r\n");
        stringBuilder.append("3.查看课程信息\r\n");
        stringBuilder.append("4.修改课程信息\r\n");
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
                    System.out.println("添加课程信息.");
                    //显示信息
                    this.showCourseInto();
                    //
                    this.addCourse();
                    //show menu
                    System.out.println(disInfo);
                    break;
                case 2:
                    System.out.println("删除课程信息");
                    delCourse();
                    System.out.println(disInfo);
                    break;
                case 3:
                    System.out.println("查看课程信息.");
                    //显示信息
                    this.showCourseInto();
                    System.out.println(disInfo);
                    break;
                case 4:
                    System.out.println("修改课程信息");
                    break;
                default:
                    return;
            }
        }
    }


}
