package com.example.algorithm.liststudent.service;

import com.alibaba.fastjson.JSON;
import com.example.algorithm.liststudent.base.Course;
import com.example.algorithm.liststudent.repository.CourseRepository;
import com.example.algorithm.liststudent.utils.Utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
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

    /**
     * 删除课程信息
     *
     * @throws Exception
     */
    private void delCourse() throws Exception {
        System.out.println("=====显示系统菜单====");
        System.out.println("删除课程信息");
        System.out.println("输入课程的编号");
        System.out.println("输入0返回");
        System.out.println("===================");
        //显示信息
        showCourseInto();
        //用户输入
        System.out.println("录入课程编号:");
        Scanner scanner = new Scanner(System.in);
        String courseNumber = "";
        if (scanner.hasNext()) {
            courseNumber = scanner.nextLine();
        }
        if ("0".equals(courseNumber)) {
            //返回上一步
            return;
        }
        CourseRepository courseRepository = this.baseService.getCourseRepository();
        if (!courseNumber.isEmpty() && courseRepository != null && courseRepository.size() > 0) {
            //执行删除动作
            courseRepository.delete(new Course(courseNumber, "", 0.0f));
            // 把内存数据删除的写入文件
            this.saveCourseInfoToDisk();
            //显示学生信息
            this.showCourseInto();
        }

    }

    /**
     * 添加课程信息
     *
     * @throws Exception
     */
    private void addCourse() throws Exception {
        System.out.println("=====显示信息====");
        String curseNumber = Utils.getInnerId("CRS");
        System.out.println("系统生成的课程编号:" + curseNumber);
        String curseName = "";
        //用户输入
        System.out.println("=====显示信息====");
        System.out.println("输入课程名（字符如:Java程序设计）");
        System.out.println("");
        //用户输入
        Scanner scanner = new Scanner(System.in);
        curseName = scanner.nextLine();
        System.out.println("输入：" + curseName);
        if (curseName.isEmpty()) {
            System.out.println("录入不合法");
            return;
        }
        float point = 4.0f;
        Course course = new Course(curseNumber, curseName, point);
        baseService.getCourseRepository().add(course);
        System.out.println("集合长度：" + baseService.getCourseRepository().size());
        this.saveCourseInfoToDisk();
    }

    /**
     * @throws Exception
     */
    private void saveCourseInfoToDisk() throws Exception {
        Course[] courses = this.baseService.getCourseRepository().listToArrays();
        if (courses == null || courses.length == 0) {
            System.out.println("无写入内容到磁盘!");
            return;
        }
        String fileContent = JSON.toJSONString(courses);
        FileWriter fileWriter = new FileWriter(baseService.courseFilePath);
        fileWriter.write(fileContent);
        fileWriter.close();
    }


    /**
     * 读取磁盘数据
     *
     * @return
     */
    private Course[] readInfoFromDisk() throws Exception {
        boolean flag = Utils.checkFile(baseService.courseFilePath);
        if (!flag) {
            return null;
        }
        FileReader fileReader = new FileReader(baseService.courseFilePath);
        int ch = 0;
        String context = "";
        while ((ch = fileReader.read()) != -1) {
            context += String.valueOf((char) ch);
        }
        fileReader.close();
        // JSON串转用户对象列表
        List<Course> courseList = JSON.parseArray(context, Course.class);
        // list to array
        if (courseList == null || courseList.size() == 0) {
            return null;
        }
        Course[] courses = new Course[courseList.size()];
        for (int i = 0; i < courseList.size(); i++) {
            courses[i] = courseList.get(i);
        }
        return courses;

    }


    private void showCourseInto() throws Exception {

        Course[] courses = this.readInfoFromDisk();
        if (courses == null || courses.length == 0) {
            System.out.println("无信息，返回上一级");
            return;
        }
        //如果存在记录同时加载到内存里面，给链表赋值
        baseService.setCourseRepository(new CourseRepository(courses));
        //print
        System.out.println("=====课程数如下=====");
        System.out.println("课程数目：" + courses.length);
        System.out.println("=====教师信息如下=====");

        for (Course course : courses) {
            String createTime = Utils.getStringFormatDate(course.getCreateTime());
            String updateTime = Utils.getStringFormatDate(course.getUpdateTime());
            System.out.println("课程编号：" + course.curseNumber + "\t课程名称：" + course.curseName + "\t"
                    + "学分数:" + course.gradePoint +
                    "\t添加日期：" + createTime + "\t修改日期：" + updateTime);
        }
        System.out.println("======end=====");


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
            switch (orderNumber) {
                case 0:
                    System.out.println("返回上一层.");
                    new StudentService(baseService).initMenu();
                    break;
                case 1:
                    System.out.println("添加课程信息.");
                    //显示信息
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
