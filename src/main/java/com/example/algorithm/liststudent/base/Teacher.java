package com.example.algorithm.liststudent.base;

import java.util.ArrayList;

/**
 * 教师实体
 * @author yichuan
 */
public class Teacher extends Person {
    /**
     * 课程编号和教师编号关联
     * 教师授课课程
     */
    public ArrayList<TeacherCourse> teacherCourses = new ArrayList();

    public Teacher() {
    }
    public Teacher(String number, String name) {
        super(number, name);
    }


    /**
     * 教师选课信息
     */
    public static class TeacherCourse {
        /**
         * 教师编号
         */
        public String courseNumber;
        /**
         * 课程编号
         */
        public String teacherNumber;

        public TeacherCourse(String teacherNumber, String courseNumber) {
            this.teacherNumber = teacherNumber;
            this.courseNumber = courseNumber;
        }

        public TeacherCourse() {
        }

        public String getCourseNumber() {
            return courseNumber;
        }

        public void setCourseNumber(String courseNumber) {
            this.courseNumber = courseNumber;
        }

        public String getTeacherNumber() {
            return teacherNumber;
        }

        public void setTeacherNumber(String teacherNumber) {
            this.teacherNumber = teacherNumber;
        }

        @Override
        public String toString() {
            return "TeacherCourse{" +
                    "courseNumber='" + courseNumber + '\'' +
                    ", teacherNumber='" + teacherNumber + '\'' +
                    '}';
        }
    }

    public ArrayList<TeacherCourse> getTeacherCourses() {
        return teacherCourses;
    }

    public void setTeacherCourses(ArrayList<TeacherCourse> teacherCourses) {
        this.teacherCourses = teacherCourses;
    }
}
