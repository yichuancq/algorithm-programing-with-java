package com.example.learn.liststudent;

import com.example.learn.liststudent.list.StudentClassesLinkList;

/**
 * 全局基础服务
 */
public class BaseService {
    //保存文件的路径
    public final String studentClassesFilePath = "src/main/resources/studentClasses.txt";

    /**
     *
     */
    private StudentClassesLinkList studentClassesLinkList;

    public BaseService() {
        this.init();
    }

    /**
     *
     */
    private void init() {
        studentClassesLinkList = new StudentClassesLinkList();

    }

    public StudentClassesLinkList getStudentClassesLinkList() {
        return studentClassesLinkList;
    }

    public void setStudentClassesLinkList(StudentClassesLinkList studentClassesLinkList) {
        this.studentClassesLinkList = studentClassesLinkList;
    }
}
