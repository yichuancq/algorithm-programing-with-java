package com.example.algorithm.liststudent.base;

import java.util.ArrayList;

/**
 * 学生实体
 */
public class Student extends Person {


    /**
     * 课程编号和教师编号关联
     * 教师授课课程
     */
    public ArrayList<Student.StudentCourse> studentCourses = new ArrayList();


    public Student() {
    }

    public Student(String number, String name) {
        super(number, name);
    }


    /**
     * 学生选课信息
     */
    public static class StudentCourse {
        /**
         * 学生编号
         */
        public String studentNumber;
        /**
         * 课程编号
         */
        public String courseNumber;

        public StudentCourse(String studentNumber, String courseNumber) {
            this.studentNumber = studentNumber;
            this.courseNumber = courseNumber;
        }

        @Override
        public String toString() {
            return "StudentCourse{" +
                    "studentNumber='" + studentNumber + '\'' +
                    ", courseNumber='" + courseNumber + '\'' +
                    '}';
        }

        public StudentCourse() {
        }

        public String getStudentNumber() {
            return studentNumber;
        }

        public void setStudentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
        }

        public String getCourseNumber() {
            return courseNumber;
        }

        public void setCourseNumber(String courseNumber) {
            this.courseNumber = courseNumber;
        }
    }

    public ArrayList<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(ArrayList<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }
}
