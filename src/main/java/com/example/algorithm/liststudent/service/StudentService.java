package com.example.algorithm.liststudent.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.algorithm.liststudent.base.*;
import com.example.algorithm.liststudent.repository.StudentClassesRepository;
import com.example.algorithm.liststudent.repository.StudentRepository;
import com.example.algorithm.liststudent.utils.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author yichuan
 */
public class StudentService {


    private BaseService baseService;

    public StudentService() {
    }

    public StudentService(BaseService baseService) {
        this.baseService = baseService;
    }

    /**
     * @return
     * @throws Exception
     */
    public Student[] readStudentInfoFromDisk() throws Exception {
        //文件路径检查
        boolean flag = Utils.checkFile(baseService.studentFilePath);
        if (!flag) {
            return null;
        }
        FileReader fileReader = new FileReader(baseService.studentFilePath);
        int ch = 0;
        String context = "";
        while ((ch = fileReader.read()) != -1) {
            context += String.valueOf((char) ch);
        }
        fileReader.close();
        // JSON串转用户对象列表
        List<Student> studentList = JSON.parseArray(context, Student.class);
        if (studentList != null && studentList.size() > 0) {
            Student[] students = new Student[studentList.size()];
            for (int i = 0; i < studentList.size(); i++) {
                students[i] = studentList.get(i);
            }
            return students;
        }
        return null;
    }

    /**
     * @return
     * @throws Exception
     */
    public Student[] readLineStudentInfoFromDisk() throws Exception {
        //文件路径检查
        boolean flag = Utils.checkFile(baseService.studentFilePath);
        if (!flag) {
            return null;
        }
        FileReader fileReader = new FileReader(baseService.studentFilePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String content = null;
        //按行读取
        List<Student> studentList = new ArrayList<>();
        while ((content = bufferedReader.readLine()) != null) {
            //判断为空
            if (!content.isEmpty()) {
                JSONObject jsonObject = JSONObject.parseObject(content);
                //将JSONObject对象转为Bean实体对象
                Student student = JSON.toJavaObject(jsonObject, Student.class);
                System.out.println(student.getName());
                studentList.add(student);
            }

        }
        fileReader.close();
        if (studentList.size() > 0) {
            Student[] students = new Student[studentList.size()];
            for (int i = 0; i < studentList.size(); i++) {
                students[i] = studentList.get(i);
            }
            return students;
        }
        return null;
    }

    public Student[] loadStudentInfo() throws Exception {
        Student[] students = this.readLineStudentInfoFromDisk();
        if (students == null || students.length == 0) {
            System.out.println("无信息，返回上一级");
            System.out.println("");
            this.showPersonMenu();
            return students;
        }
        //如果存在记录同时加载到内存里面，给链表赋值
        baseService.setPersonRepository(new StudentRepository(students));
        return students;

    }

    /**
     * 显示学生基本信息
     */
    public void showStudentInto() throws Exception {
        Student[] students = this.readLineStudentInfoFromDisk();
        if (students == null || students.length == 0) {
            System.out.println("无信息，返回上一级");
            System.out.println("");
            this.showPersonMenu();
        }
        //print
        System.out.println("=====学生总人数如下=====");
        System.out.println("学生总人数：" + students.length);
        System.out.println("=====学生信息如下=====");
        for (Student student : students) {
            String createTime = Utils.getStringFormatDate(student.getCreateTime());
            String updateTime = Utils.getStringFormatDate(student.getUpdateTime());
            System.out.println("学号：" + student.getNumber() + "\t姓名：" + student.getName() +
                    "\t" + "添加日期：" + createTime + "\t修改日期：" + updateTime);
        }
        System.out.println("======end =====");
        return;

    }

    /**
     * 显示学生选课信息
     */
    public void showStudentCourseInto() throws Exception {
        Student[] students = this.loadStudentInfo();
        if (students == null || students.length == 0) {
            System.out.println("无信息，返回上一级");
            System.out.println("");
            this.showPersonMenu();
        }
        System.out.println("=====学生总人数如下=====");
        System.out.println("学生总人数：" + students.length);
        System.out.println("=====学生信息如下=====");
        CourseService courseService = new CourseService(baseService);
        //加载信息
        courseService.loadData();
        int i = 0;
        for (Student student : students) {
            //本学生选课
            String createTime = Utils.getStringFormatDate(student.getCreateTime());
            String updateTime = Utils.getStringFormatDate(student.getUpdateTime());
            for (Student.StudentCourse studentCourse : student.getStudentCourses()) {
                ++i;
                System.out.println("===row :" + i + "===");
                System.out.println("学号：" + student.getNumber() + "\t姓名：" + student.getName() + "\t" + "添加日期：" + createTime + "\t修改日期：" + updateTime);
                System.out.println("本学生选课信息如下：");
                System.out.println("课程编码:" + studentCourse.courseNumber);
                Course queryKey = new Course();
                //查询课程服务类
                queryKey.curseNumber = studentCourse.courseNumber;
                //find course info
                Course course = courseService.searchByKey(queryKey);
                if (course != null) {
                    System.out.println("课程名称:" + course.curseName + "\t 课程学分:" + course.gradePoint);
                }
            }
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
        String number = Utils.getInnerId("STU");
        System.out.println("系统生成的学生编号:" + number);
        String name = "";
        //用户输入
        System.out.println("=====显示信息====");
        System.out.println("输入学生姓名（字符如:yichuan）");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
        System.out.println("输入：" + name);
        Student student = new Student(number, name);
        if (name.isEmpty() || number.isEmpty()) {
            System.out.println("录入不合法");
            return;
        }
        //是否提示名称重复？
        //添加到内存链表
        this.baseService.getPersonRepository().add(student);
        StudentRepository studentRepository = this.baseService.getPersonRepository();
        System.out.println("学生集合长度：" + studentRepository.size());
        //保存学生信息到文件
        this.saveStudentInfoToDisk(student);
        //返回上一步
        //保存后重新加载到内存链表
    }

    /**
     * 保存学生信息到文件
     */
    public void saveStudentInfoToDisk(Student student) throws Exception {
        if (student == null) {
            System.out.println("无写入内容到磁盘!");
            return;
        }
        //
        FileWriter fileWriter = new FileWriter(baseService.studentFilePath, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        String fileContent = JSON.toJSONString(student);
        //
        bufferedWriter.write(fileContent);
        bufferedWriter.newLine();
        bufferedWriter.flush(); //将数据更新至文件
        bufferedWriter.close();
        fileWriter.close();
    }

    /**
     *
     */
    public void showPersonMenu() throws Exception {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\r\n=====显示系统菜单====");
        stringBuilder.append("\r\n1.添加学生信息");
        stringBuilder.append("\r\n2.删除学生信息");
        stringBuilder.append("\r\n3.查看学生信息");
        stringBuilder.append("\r\n4.修改(编辑)学生信息");
        stringBuilder.append("\r\n0.返回上一层");
        stringBuilder.append("\r\n===================");
        stringBuilder.append("\r\n请选择?(0-4)\r\n");
        String inflows = stringBuilder.toString();
        System.out.println(inflows);
        //用户输入信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int orderNumber = scanner.nextInt();
            switch (orderNumber) {
                case 0:
                    System.out.println("返回上一层.");
                    this.initMenu();
                    break;
                case 1:
                    System.out.println("添加学生信息.");
                    this.addStudent();
                    System.out.println(inflows);
                    break;
                case 2:
                    System.out.println("删除学生信息.");
                    delStudent();
                    System.out.println(inflows);
                    break;
                case 3:
                    System.out.println("查看学生信息.");
                    showStudentInto();
                    System.out.println(inflows);
                    break;
                case 4:
                    System.out.println("修改学生信息.");
                    editStudent();
                    System.out.println(inflows);
                    break;
                default:
                    System.out.println("录入非法");
                    System.out.println(inflows);
                    return;
            }
        }
    }

    /***
     * 查询人员信息
     * @param personNumber
     * @param classesNumber
     * @throws Exception
     */
    private void searchPerson(String personNumber, String classesNumber) throws Exception {
        StudentClasses studentClasses = null;
        //通过对象查找元素
        Person personKey = new Person(personNumber, "");
        //查询学生信息
        LinkNode<Person> personLinkNode = baseService.getPersonRepository().search(personKey);
        if (personLinkNode == null) {
            System.out.println("查询失败， 无此记录~");
            //返回上一层
            this.editStudent();
            return;
        }
        Classes classesKey = new Classes(classesNumber, "");
        //查询班级信息
        Classes classes = new ClassesService(baseService).searchByKey(classesKey);
        if (classes == null) {
            System.out.println("查询失败， 无此记录~");
            //返回上一层
            this.editStudent();
            return;
        }
        if (personLinkNode.data != null) {
            Student student = (Student) personLinkNode.data;
            //自定义编码
            String scNumber = "";
            studentClasses = new StudentClasses(scNumber, classes.classesNumber,
                    classes.classesName, student.getNumber(), student.getName());
            this.saveStudentClassesInfoToDisk(studentClasses);
            //返回上一层
            this.editStudent();
            return;
        }
        return;
    }

    /**
     * 读取磁盘数据
     *
     * @return
     * @throws Exception
     */
    private StudentClasses[] readStudentClassesInfoFromDisk() throws Exception {
        boolean flag = Utils.checkFile(baseService.studentClassesFilePath);
        if (!flag) {
            return null;
        }
        //注意文件路径
        FileReader fileReader = new FileReader(baseService.studentClassesFilePath);
        int ch = 0;
        String context = "";
        while ((ch = fileReader.read()) != -1) {
            context += String.valueOf((char) ch);
        }
        fileReader.close();
        // JSON串转用户对象列表
        List<StudentClasses> studentClasses = JSON.parseArray(context, StudentClasses.class);
        StudentClasses[] sClasses = new StudentClasses[studentClasses.size()];
        for (int i = 0; i < studentClasses.size(); i++) {
            sClasses[i] = studentClasses.get(i);
        }
        return sClasses;
    }


    /**
     * @throws Exception
     */
    private void loadStudentClassesInfoFromDisk() throws Exception {
        StudentClasses[] arrays = readStudentClassesInfoFromDisk();
        if (arrays == null || arrays.length == 0) {
            System.out.println("文件无数据，请继续操作~");
            return;
        }
        //如果存在记录同时加载到内存里面，给链表赋值
        baseService.setPersonRepository(new StudentRepository(arrays));
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
        //加载文件内容到内存
        this.loadStudentClassesInfoFromDisk();
        //添加到链表中，加载到内存
        baseService.getStudentClassesLinkList().add(studentClasses);
        //集合长度
        int size = baseService.getStudentClassesLinkList().size();
        System.out.println("集合长度：" + size);
        //保存学生信息到文件
        this.saveStudentClassesLinkNodesInfoToDisk();
    }

    /**
     * 学生班级信息集合保存到磁盘
     *
     * @throws Exception
     */
    public void saveStudentClassesLinkNodesInfoToDisk() throws Exception {
        StudentClasses[] classes = baseService.getStudentClassesLinkList().listToArrays();
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
        /**
         * 显示学生信息
         */
        this.showStudentInto();
        /***
         *显示班级信息
         */
        new ClassesService(baseService).showClassesInto();
        /**
         * 编辑前显示已经有的数据
         */
        this.showStudentClassesInfo();
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
            return;
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
            if (orderNumber < 0 || orderNumber > 3) {
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
                    //编辑前显示已经有的数据
                    this.showStudentClassesInfo();
                    //修改学生姓名信息
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
     * 查询学生班级信息
     *
     * @param studentNumber
     * @return
     */
    public StudentClasses findStudentClassesByStuNumber(String studentNumber) throws Exception {
        // TODO: 2021/4/7
        StudentClasses studentClasses = null;
        if (studentNumber == null || studentNumber.isEmpty()) {
            return studentClasses;
        }

        //加载文件内容到内存
        StudentClasses[] studentClassesArrays = this.readStudentClassesInfoFromDisk();
        if (studentClassesArrays == null || studentClassesArrays.length == 0) {
            System.out.println("无信息，返回上一级");
            return studentClasses;
        }
        //如果存在记录同时加载到内存里面，给链表赋值
        baseService.setStudentClassesRepository(new StudentClassesRepository(studentClassesArrays));
        StudentClasses studentClassesKey = new StudentClasses();
        studentClassesKey.setStuNumber(studentNumber);

        studentClasses = this.baseService.getStudentClassesLinkList().search(studentClassesKey);
        return studentClasses;
    }

    /**
     * 显示学生班级信息关系记录
     *
     * @throws Exception
     */
    private void showStudentClassesInfo() throws Exception {
        //加载文件内容到内存
        StudentClasses[] studentClasses = this.readStudentClassesInfoFromDisk();
        if (studentClasses == null || studentClasses.length == 0) {
            System.out.println("无信息，返回上一级");
            return;
        }
        //如果存在记录同时加载到内存里面，给链表赋值
        baseService.setStudentClassesRepository(new StudentClassesRepository(studentClasses));
        int size = baseService.getStudentClassesLinkList().size();
        //print
        System.out.println("=====学生班级信息记录数如下=====");
        System.out.println("记录数：" + size);
        System.out.println("=====学生班级信息如下=====");
        for (StudentClasses sClasses : studentClasses) {
            System.out.println("班级号：" + sClasses.classesNumber +
                    "\t班级名称：" + sClasses.classesName
                    + "\t学号: " + sClasses.stuNumber +
                    "\t学生姓名:" + sClasses.stuName);
        }
        System.out.println("======end =====");
    }

    /**
     * 删除学生信息
     */
    private void delStudent() throws Exception {
        System.out.println("=====显示系统菜单====");
        System.out.println("\t 删除学生信息");
        System.out.println("\t 输入删除的学生编号");
        System.out.println("\t 输入0返回");
        System.out.println("===================");
        //显示学生信息
        showStudentInto();
        //用户输入
        System.out.println("录入学号:");
        Scanner scanner = new Scanner(System.in);
        String stuNumber = "";
        if (scanner.hasNext()) {
            stuNumber = scanner.nextLine();
        }
        if ("0".equals(stuNumber)) {
            //返回上一步
            return;
        }
        StudentRepository studentRepository = this.baseService.getPersonRepository();
        if (!stuNumber.isEmpty() && studentRepository != null && studentRepository.size() > 0) {
            //执行删除动作
            studentRepository.delete(new Student(stuNumber, ""));
            // 把内存数据删除的写入文件
            Utils.removeLineForLineContent(this.baseService.studentFilePath, "number", stuNumber, false);
            //显示学生信息
            this.showStudentInto();
            //返回上一步
        }
    }

    /**
     * @param studentNumber
     * @return
     */
    public Student findStudentByNumber(String studentNumber) {
        Student student = null;
        if (studentNumber == null || studentNumber.isEmpty()) {
            return student;
        }
        Student studentQuery = new Student();
        studentQuery.setNumber(studentNumber);
        LinkNode linkNode = baseService.getPersonRepository().search(studentQuery);
        if (linkNode != null && linkNode.data != null) {
            student = (Student) baseService.getPersonRepository().search(studentQuery).data;
        }
        return student;
    }


    /**
     * @throws IOException
     */
    public void initMenu() throws Exception {
        System.out.println("=====显示系统菜单====");
        System.out.println("1.学生信息管理");
        System.out.println("2.教师信息管理");
        System.out.println("3.班级信息管理");
        System.out.println("4.课程信息管理");
        System.out.println("5.成绩信息管理");
        System.out.println("0.exit");
        System.out.println("===================");
        System.out.println("请选择?(0-4)");
        //用户输入信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int orderNumber = scanner.nextInt();
            switch (orderNumber) {
                case 0:
                    System.out.println("退出系统");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("学生信息管理");
                    showPersonMenu();
                    break;
                case 2:
                    System.out.println("教师信息管理");
                    //显示教师管理菜单
                    new TeacherService(baseService).showTeacherMenu();
                    break;
                case 3:
                    System.out.println("班级信息管理");
                    new ClassesService(baseService).showClassesMenu();
                    break;
                case 4:
                    System.out.println("课程信息管理");
                    new CourseService(baseService).showCourseMenu();
                    break;
                case 5:
                    System.out.println("成绩信息管理");
                    new ScoreService(baseService).showScoreMenu();
                    break;
                default:
                    System.out.println("录入非法");
                    break;
            }
        }
    }


}
