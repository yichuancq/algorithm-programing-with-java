package com.example.algorithm.liststudent.base;

import java.util.ArrayList;

/**
 * 教师实体
 */
public class Teacher extends Person {
    /**
     * 课程编号和教师编号关联
     * 教师授课课程
     */
    public ArrayList csNumbers = new ArrayList();

    public Teacher() {
    }

    public Teacher(String number, String name) {
        super(number, name);
    }

    public Teacher(String number, String name, ArrayList csNumbers) {
        super(number, name);
        this.csNumbers = csNumbers;
    }
}
