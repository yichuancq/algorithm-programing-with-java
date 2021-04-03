package com.example.algorithm.liststudent.base;


/**
 * 课程
 */
public class Course  extends BaseEntity{
    /**
     * 课程编号
     */
    public String curseNumber;
    /**
     * 课程名称
     */
    public String curseName;
    /**
     * 学分数
     */
    public float gradePoint;

    public Course() {
    }

    /**
     * @param curseNumber
     * @param curseName
     */
    public Course(String curseNumber, String curseName, float gradePoint) {
        this.curseNumber = curseNumber;
        this.curseName = curseName;
        this.gradePoint = gradePoint;
    }

    @Override
    public String toString() {
        return "Course{" +
                "curseNumber='" + curseNumber + '\'' +
                ", curseName='" + curseName + '\'' +
                '}';
    }
}
