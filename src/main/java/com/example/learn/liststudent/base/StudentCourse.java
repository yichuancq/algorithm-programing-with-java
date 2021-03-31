package com.example.learn.liststudent.base;

/**
 * 学生选课记录
 */
public class StudentCourse {
    /**
     * 学生选课号
     */
    public String scNumber;
    /**
     * 学号
     */
    public String stuNumber;
    /**
     * 学生姓名
     */
    private String stuName;
    /**
     * 课程编号
     */
    public String curseNumber;
    /**
     * 课程名称
     */
    public String curseName;
    /**
     * 成绩
     */
    public float score;

    public StudentCourse() {
    }

    public StudentCourse(String scNumber, String stuNumber, String curseNumber, float score) {
        this.scNumber = scNumber;
        this.stuNumber = stuNumber;
        this.curseNumber = curseNumber;
        this.score = score;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "scNumber='" + scNumber + '\'' +
                ", stuNumber='" + stuNumber + '\'' +
                ", curseNumber='" + curseNumber + '\'' +
                ", score=" + score +
                '}';
    }
}
