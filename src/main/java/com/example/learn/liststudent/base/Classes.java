package com.example.learn.liststudent.base;

/**
 * 学生班级
 */
public class Classes {
    /**
     * 班级号
     */
    public String classesNumber;
    /**
     * 班级名称
     */
    public String classesName;
    /**
     * 班级的学生编号
     */
    public String[] stuNumbers;
    /**
     * 班级人数
     */
    public int studentAmount;


    public Classes() {
    }

    public Classes(String classesNumber, String classesName) {
        this.classesName = classesName;
        this.classesNumber = classesNumber;
    }
}
