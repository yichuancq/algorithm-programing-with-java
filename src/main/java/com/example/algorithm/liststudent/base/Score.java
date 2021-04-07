package com.example.algorithm.liststudent.base;

/**
 * 学生成绩记录
 *
 * @author yichuan
 */
public class Score extends BaseEntity {

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

    public Score(String scNumber, String stuNumber, String stuName, String
            curseNumber, String curseName, String classNumber, String className,
                 String teacherNumber, String teacherName, float score) {
        this.scNumber = scNumber;
        this.stuNumber = stuNumber;
        this.stuName = stuName;
        this.curseNumber = curseNumber;
        this.curseName = curseName;
        this.classNumber = classNumber;
        this.teacherNumber = teacherNumber;
        this.teacherName = teacherName;
        this.className = className;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "scNumber='" + scNumber + '\'' +
                ", stuNumber='" + stuNumber + '\'' +
                ", stuName='" + stuName + '\'' +
                ", curseNumber='" + curseNumber + '\'' +
                ", curseName='" + curseName + '\'' +
                ", classNumber='" + classNumber + '\'' +
                ", teacherNumber='" + teacherNumber + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", className='" + className + '\'' +
                ", score=" + score +
                '}';
    }

    public String getScNumber() {
        return scNumber;
    }

    public void setScNumber(String scNumber) {
        this.scNumber = scNumber;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getCurseNumber() {
        return curseNumber;
    }

    public void setCurseNumber(String curseNumber) {
        this.curseNumber = curseNumber;
    }

    public String getCurseName() {
        return curseName;
    }

    public void setCurseName(String curseName) {
        this.curseName = curseName;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
