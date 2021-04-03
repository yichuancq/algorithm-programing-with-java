package com.example.algorithm.liststudent.base;

/**
 * 学生班级
 */
public class Classes extends BaseEntity {
    /**
     * 班级号
     */
    public String classesNumber = "";
    /**
     * 班级名称
     */
    public String classesName;


    public Classes() {
    }

    public Classes(String classesNumber, String classesName) {
        this.classesName = classesName;
        this.classesNumber = classesNumber;
    }
}
