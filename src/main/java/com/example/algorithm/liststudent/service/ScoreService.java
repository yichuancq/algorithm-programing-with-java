package com.example.algorithm.liststudent.service;

import java.util.Scanner;

/**
 * 学生选课信息服务类
 */
public class ScoreService {

    /**
     * 基础服务类
     */
    public BaseService baseService;

    public ScoreService(BaseService baseService) {
        this.baseService = baseService;
    }

    private void addScore() throws Exception {
    }

    private void showScoreInto() throws Exception {
    }

    private void delScore() throws Exception {
    }

    /**
     * 显示成绩信息信息菜单
     */
    public void showScoreMenu() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\r\n=====显示成绩信息信息菜单====\r\n");
        stringBuilder.append("1.添加成绩信息(教师)\r\n");
        stringBuilder.append("2.删除成绩信息(教师)\r\n");
        stringBuilder.append("3.查看成绩信息(教师、学生)\r\n");
        stringBuilder.append("4.修改课程成绩信息(教师)\r\n");
        stringBuilder.append("5.统计成绩信息(教师、学生)\r\n");
        stringBuilder.append("0.返回上一层\r\n");
        stringBuilder.append("请选择?(0-4)\r\n");
        stringBuilder.append("===================\r\n");
        String disInfo = stringBuilder.toString();
        System.out.println(disInfo);
        //用户输入信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int orderNumber = scanner.nextInt();
            switch (orderNumber) {
                case 0:
                    System.out.println("返回上一层.");
                    new StudentService(baseService).initMenu();
                    break;
                case 1:
                    System.out.println("添加成绩信息");
                    //显示信息
                    this.showScoreInto();
                    //
                    this.addScore();
                    //show menu
                    System.out.println(disInfo);
                    break;
                case 2:
                    System.out.println("删除成绩信息");
                    delScore();
                    System.out.println(disInfo);
                    break;
                case 3:
                    System.out.println("查看成绩信息");
                    //显示信息
                    this.showScoreInto();
                    System.out.println(disInfo);
                    break;
                case 4:
                    System.out.println("修改课程成绩信息");
                    break;
                case 5:
                    System.out.println("统计成绩信息");
                    break;
                default:
                    System.out.println("录入非法");
                    System.out.println(disInfo);
                    break;
            }
        }
    }


}
