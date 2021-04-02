package com.example.learn.liststudent.base;

import java.math.BigDecimal;

/**
 * 课程
 */
public class Course {
    /**
     * 课程编号
     */
    public String curseNumber;
    /**
     * 课程名称
     */
    public String curseName;
    /**
     * 平均分
     */
    public BigDecimal avgScore;
    /**
     * 参加课程人数
     */
    public int studentAmount;
    /**
     * 平均绩点
     */
    public BigDecimal avgGradePointAverage;

    public Course() {
    }

    /**
     * @param curseNumber
     * @param curseName
     */
    public Course(String curseNumber, String curseName) {
        this.curseNumber = curseNumber;
        this.curseName = curseName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "curseNumber='" + curseNumber + '\'' +
                ", curseName='" + curseName + '\'' +
                ", avgScore=" + avgScore +
                ", studentAmount=" + studentAmount +
                '}';
    }
}
