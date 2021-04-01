package com.example.learn.liststudent.service;

import com.example.learn.liststudent.linklist.PersonLinkList;
import com.example.learn.liststudent.linklist.StudentClassesLinkList;

/**
 * 全局基础服务
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
     *
     */
    private StudentClassesLinkList studentClassesLinkList;

    /**
     * 学生信息链表
     */
    private PersonLinkList personLinkList;



    public BaseService() {
        studentClassesLinkList = new StudentClassesLinkList();
        personLinkList = new PersonLinkList();
    }

    /**
     * @param arrays
     */
    public void init(T[] arrays) {
        studentClassesLinkList = new StudentClassesLinkList(arrays);
    }


    public StudentClassesLinkList getStudentClassesLinkList() {
        return studentClassesLinkList;
    }

    public void setStudentClassesLinkList(StudentClassesLinkList studentClassesLinkList) {
        this.studentClassesLinkList = studentClassesLinkList;
    }

    public String getStudentFilePath() {
        return studentFilePath;
    }

    public PersonLinkList getPersonLinkList() {
        return personLinkList;
    }

    public void setPersonLinkList(PersonLinkList personLinkList) {
        this.personLinkList = personLinkList;
    }
}
