package com.example.learn.liststudent.service;

import com.example.learn.liststudent.repository.*;

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
     * 保存文件的路径
     */
    public final String studentClassesFilePath = "src/main/resources/studentClasses.txt";

    /**
     * 保存文件的路径
     */
    public final String classesFilePath = "src/main/resources/classes.txt";

    public final String teacherFilePath = "src/main/resources/teacher.txt";

    //course
    public final String courseFilePath = "src/main/resources/course.txt";

    /**
     *
     */
    private StudentClassesRepository studentClassesRepository;

    /**
     * 学生信息链表
     */
    private PersonRepository personRepository;

    /**
     * 班级
     */
    private ClassesRepository classesRepository;

    /**
     * 教师
     */
    private TeacherRepository teacherRepository;

    private CourseRepository courseRepository;

    public BaseService() {
        studentClassesRepository = new StudentClassesRepository();
        personRepository = new PersonRepository();
        classesRepository = new ClassesRepository();
        teacherRepository=new TeacherRepository();
        courseRepository=new CourseRepository();
    }

    /**
     * @param arrays
     */
    public void init(T[] arrays) {
        studentClassesRepository = new StudentClassesRepository(arrays);
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

    public PersonRepository getPersonLinkList() {
        return personRepository;
    }

    public void setPersonLinkList(PersonRepository personRepository) {
        this.personRepository = personRepository;
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
