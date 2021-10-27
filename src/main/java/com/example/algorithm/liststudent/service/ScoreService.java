package com.example.algorithm.liststudent.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.algorithm.liststudent.base.*;
import com.example.algorithm.liststudent.repository.ScoreRepository;
import com.example.algorithm.liststudent.utils.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * 添加成绩信息
     * 1、显示教师信息
     * 2、显示课程信息
     * 3、显示学生成绩
     * 4、保存成绩信息到磁盘
     *
     * @throws Exception
     */
    private void addScore() throws Exception {
        System.out.println("添加成绩信息");
        //显示教师选课信息
        new TeacherService(baseService).showTeacherCourseInto();
        //显示学生信息
        new StudentService(baseService).showStudentInto();
        //录入课程编码
        //录入学号
        //输入教师和课程编号完成选择课程
        System.out.println("请教师输入自己教师编号");
        //教师编码
        String teacherNumber = "";
        //课程编码
        String courseNumber = "";
        //学生编码
        String studentNumber = "";
        //
        float scoreValue = 0.0f;
        //用户输入
        Scanner scanner = new Scanner(System.in);
        //输入教师编号
        teacherNumber = scanner.nextLine();
        System.out.println("输入：" + teacherNumber);
        System.out.println("输入的教师编号：" + teacherNumber);
        //输入课程
        System.out.println("输入选择上课的课程编码");
        scanner = new Scanner(System.in);
        courseNumber = scanner.nextLine();
        System.out.println("输入的课程号：" + courseNumber);
        //输入学号
        System.out.println("输入本课程的学号");
        scanner = new Scanner(System.in);
        studentNumber = scanner.nextLine();
        System.out.println("输入学号：" + studentNumber);
        // 录入成绩信息
        System.out.println("输入成绩");
        scanner = new Scanner(System.in);
        scoreValue = scanner.nextFloat();
        System.out.println("输入成绩：" + scoreValue);
        //输入合法性判断
        if (teacherNumber.isEmpty() || courseNumber.isEmpty() || studentNumber.isEmpty() || scoreValue == 0) {
            System.out.println("录入不合法,请完善录入信息！");
            return;
        }
        this.addStudentScore(courseNumber.trim(), teacherNumber.trim(), studentNumber.trim(), scoreValue);
    }

    /**
     * 添加课程信息
     *
     * @param courseNumber
     * @param teacherNumber
     * @param studentNumber
     */
    private void addStudentScore(final String courseNumber, final String teacherNumber, final String studentNumber,
                                 float scoreValue) throws Exception {
        CourseService courseService = new CourseService(baseService);
        StudentService studentService = new StudentService(baseService);
        TeacherService teacherService = new TeacherService(baseService);
        //加载信息
        courseService.loadData();
        //studentService
        studentService.loadStudentInfo();
        //
        teacherService.LoadTeacherInfo();
        //课程成绩编码系统自动生成
        String scNumber = Utils.getInnerId("SC");
        System.out.println("系统生成的课程成绩编号:" + scNumber);
        //学生名
        String stuName = "";
        //课程名称
        String curseName = "";
        //班级编码
        String classNumber = "";
        //教师名称
        String teacherName = "";
        //班级名称
        String className = "";
        float scoreVal = scoreValue;
        //学生信息
        Student studentDb = studentService.findStudentByNumber(studentNumber);
        if (studentDb == null) {
            System.out.println("学生信息不存在");
            return;
        }
        Teacher teacherDb = teacherService.findTeacherByNumber(teacherNumber);
        if (teacherDb == null) {
            System.out.println("教师信息不存在");
            return;
        }//
        Course queryKey = new Course();
        //查询课程服务类
        queryKey.setCurseNumber(courseNumber);
        //find course info
        Course courseDb = courseService.searchByKey(queryKey);
        if (courseDb == null) {
            System.out.println("课程信息不存在");
            return;
        }
        StudentClasses studentClassesDB = studentService.findStudentClassesByStuNumber(studentNumber);
        if (studentClassesDB == null) {
            System.out.println("学生班级信息不存在");
            return;
        }
        //班级名称
        className = studentClassesDB.getClassesName();
        //课程名称
        curseName = courseDb.getCurseName();
        //学生名称
        stuName = studentDb.getName();
        //班级编号
        classNumber = studentClassesDB.getClassesNumber();
        //教师名称
        teacherName = teacherDb.getName();
        Score score = new Score(scNumber, studentNumber, stuName, courseNumber, curseName, classNumber,
                className, teacherNumber, teacherName, scoreVal);
        //添加到内存链表
        this.baseService.getScoreRepository().add(score);
        //
        ScoreRepository scoreRepository = this.baseService.getScoreRepository();
        System.out.println("成绩信息集合长度：" + scoreRepository.size());
        //保存学生信息到文件
        this.saveStudentScoreInfoToDisk(score);
        //show info
        this.showScoreInto();
        //返回上一步
        return;
    }

    /**
     * @throws Exception
     */
    public void saveStudentScoreInfoToDisk(final Score score) throws Exception {
        if (score == null) {
            System.out.println("无写入内容到磁盘!");
            return;
        }

        FileWriter fileWriter = new FileWriter(baseService.scoreFilePath, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String fileContent = JSON.toJSONString(score);
        bufferedWriter.write(fileContent);
        bufferedWriter.newLine();
        bufferedWriter.flush(); //将数据更新至文件
        bufferedWriter.close();
        fileWriter.close();
    }

    /**
     * 载入信息
     *
     * @return
     * @throws Exception
     */
    private Score[] LoadScoreInfo() throws Exception {
        Score[] scores = this.readInfoFromDisk();
        if (scores == null || scores.length == 0) {
            System.out.println("无信息，返回上一级");
            System.out.println("");
            return scores;
        }
        //如果存在记录同时加载到内存里面，给链表赋值
        baseService.setScoreRepository(new ScoreRepository(scores));
        return scores;

    }

    /**
     * 显示成绩信息
     *
     * @throws Exception
     */
    private void showScoreInto() throws Exception {
        Score[] scores = LoadScoreInfo();
        if (scores == null || scores.length == 0) {
            System.out.println("无信息，返回上一级");
            return;
        }
        System.out.println("=====成绩数如下=====");
        System.out.println("成绩数目：" + scores.length);
        System.out.println("=====成绩信息如下=====");
        int row = 0;
        for (Score t : scores) {
            ++row;
            System.out.println("=====row:" + row + "=====");
            String createTime = Utils.getStringFormatDate(t.getCreateTime());
            String updateTime = Utils.getStringFormatDate(t.getUpdateTime());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("成绩编码:" + t.getScNumber() + "\r\n");
            stringBuilder.append("学号：" + t.getStuNumber());
            stringBuilder.append("\t学生姓名：" + t.getStuName() + "\r\n");
            stringBuilder.append("课程编码:" + t.getCurseNumber());
            stringBuilder.append("\t课程名称：" + t.getCurseName() + "\r\n");
            stringBuilder.append("班级编码:" + t.getClassNumber());
            stringBuilder.append("\t班级名称:" + t.getClassName() + "\r\n");
            stringBuilder.append("教师编码:" + t.getTeacherNumber());
            stringBuilder.append("\t教师名称：" + t.getTeacherName() + "\r\n");
            stringBuilder.append("添加日期:" + createTime + "\t修改日期：" + updateTime);
            System.out.println(stringBuilder);
        }
        System.out.println("======end=====");
    }


    /**
     * 读取磁盘数据
     *
     * @return
     * @throws Exception
     */
    private Score[] readInfoFromDisk() throws Exception {
        boolean flag = Utils.checkFile(baseService.scoreFilePath);
        if (!flag) {
            return null;
        }
        FileReader fileReader = new FileReader(baseService.scoreFilePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String content = null;
        //按行读取
        List<Score> scoreList = new ArrayList<>();
        while ((content = bufferedReader.readLine()) != null) {
            //判断为空
            if (!content.isEmpty()) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                //将JSONObject对象转为Bean实体对象
                Score temp = JSON.toJavaObject(jsonObject, Score.class);
                scoreList.add(temp);
            }

        }
        fileReader.close();
        Score[] scores = new Score[scoreList.size()];
        for (int i = 0; i < scoreList.size(); i++) {
            scores[i] = scoreList.get(i);
        }
        return scores;

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
