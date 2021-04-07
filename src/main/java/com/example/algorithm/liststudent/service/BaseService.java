package com.example.algorithm.liststudent.service;

import com.example.algorithm.liststudent.repository.*;

/**
 * 全局基础服务
 *
 * @author yichuan
 */
public class BaseService<T> {

    /**
     * 保存文件的路径
     */
    public final String studentFilePath = "src/main/resources/student.txt";

    /**
     * 学生班级关系
     */
    public final String studentClassesFilePath = "src/main/resources/studentClasses.txt";

    /**
     * 班级
     */
    public final String classesFilePath = "src/main/resources/classes.txt";

    /**
     * 教师
     */
    public final String teacherFilePath = "src/main/resources/teacher.txt";
    /**
     * 课程
     */
    //course
    public final String courseFilePath = "src/main/resources/course.txt";

    /**
     * 成绩
     */
    //course
    public final String scoreFilePath = "src/main/resources/score.txt";

    /**
     *
     */
    private StudentClassesRepository studentClassesRepository;

    /**
     * 学生信息链表
     */
    private StudentRepository studentRepository;

    /**
     * 班级
     */
    private ClassesRepository classesRepository;

    /**
     * 教师
     */
    private TeacherRepository teacherRepository;

    private CourseRepository courseRepository;

    private  ScoreRepository scoreRepository;

    public BaseService() {
        studentClassesRepository = new StudentClassesRepository();
        studentRepository = new StudentRepository();
        classesRepository = new ClassesRepository();
        teacherRepository=new TeacherRepository();
        courseRepository=new CourseRepository();
        scoreRepository=new ScoreRepository();

    }


    public ScoreRepository getScoreRepository() {
        return scoreRepository;
    }

    public void setScoreRepository(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public StudentClassesRepository getStudentClassesLinkList() {
        return studentClassesRepository;
    }

    public void setStudentClassesRepository(StudentClassesRepository studentClassesRepository) {
        this.studentClassesRepository = studentClassesRepository;
    }

    public String getStudentFilePath() {
        return studentFilePath;
    }

    public StudentRepository getPersonRepository() {
        return studentRepository;
    }

    public void setPersonRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ClassesRepository getClassesRepository() {
        return classesRepository;
    }

    public void setClassesRepository(ClassesRepository classesRepository) {
        this.classesRepository = classesRepository;
    }

    public TeacherRepository getTeacherRepository() {
        return teacherRepository;
    }

    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public CourseRepository getCourseRepository() {
        return courseRepository;
    }

    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
}
