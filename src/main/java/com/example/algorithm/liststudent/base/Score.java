package com.example.algorithm.liststudent.base;

/**
 * 学生成绩记录
 */
public class Score {

    /**
     * 成绩记录编码
     */
    public String scNumber;
    /**
     * 学号
     */
    public String stuNumber;
    /**
     * 学生姓名
     */
    public String stuName;
    /**
     * 课程编号
     */
    public String curseNumber;

    /**
     * 课程名称
     */
    public String curseName;

    /**
     * 班级编号
     */
    public String classNumber;

    /**
     * 任课教师编号
     */
    public String teacherNumber;

    /**
     * 任课教师姓名
     */
    public String teacherName;

    /**
     * 班级名称
     */
    public String className;
    /**
     * 成绩
     */
    public float score;

    public Score() {
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                ", stuNumber='" + stuNumber + '\'' +
                ", curseNumber='" + curseNumber + '\'' +
                ", score=" + score +
                '}';
    }
}
