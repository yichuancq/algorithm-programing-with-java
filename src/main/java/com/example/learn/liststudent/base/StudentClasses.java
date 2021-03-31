package com.example.learn.liststudent.base;

/**
 * 学生班级信息记录
 */
public class StudentClasses {
    /**
     * 学生班级信息编码
     */
    public String scNumber;
    /**
     * 班级号
     */
    public String classesNumber;
    /**
     * 班级名称
     */
    public String classesName;
    /**
     * 学号
     */
    public String stuNumber;
    /**
     * 学生姓名
     */
    public String stuName;

    public StudentClasses() {
    }

    /**
     * @param scNumber
     * @param classesNumber
     * @param classesName
     * @param stuNumber
     * @param stuName
     */
    public StudentClasses(String scNumber, String classesNumber,
                          String classesName, String stuNumber, String stuName) {
        this.scNumber = scNumber;
        this.classesNumber = classesNumber;
        this.classesName = classesName;
        this.stuNumber = stuNumber;
        this.stuName = stuName;
    }

    @Override
    public String toString() {
        return "StudentClasses{" +
                "scNumber='" + scNumber + '\'' +
                ", classesNumber='" + classesNumber + '\'' +
                ", classesName='" + classesName + '\'' +
                ", stuNumber='" + stuNumber + '\'' +
                ", stuName='" + stuName + '\'' +
                '}';
    }
}
