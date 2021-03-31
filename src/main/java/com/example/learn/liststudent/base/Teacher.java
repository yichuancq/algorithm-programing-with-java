package com.example.learn.liststudent.base;

/**
 * 教师实体
 */
public class Teacher extends Person {

    /**
     * 教师授课课程
     */
    public String[] csNumber;

    public Teacher() {
    }

    public Teacher(String number, String name) {
        super(number, name);
    }

    public Teacher(String number, String name, String[] courses) {
        super(number, name);
        this.csNumber = courses;
    }
}
