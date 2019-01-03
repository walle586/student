package com.example.sims.domain;

import java.io.Serializable;

public class GradeInfo implements Serializable {

    private String studentId;
    private String courseCode;
    private String studentName;
    private String courseName;
    private String xnq;
    private String xqd;
    private String score;

    public GradeInfo() {
    }

    public GradeInfo(String studentId, String courseCode, String studentName, String courseName, String xnq, String xqd, String score) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.studentName = studentName;
        this.courseName = courseName;
        this.xnq = xnq;
        this.xqd = xqd;
        this.score = score;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getXnq() {
        return xnq;
    }

    public void setXnq(String xnq) {
        this.xnq = xnq;
    }

    public String getXqd() {
        return xqd;
    }

    public void setXqd(String xqd) {
        this.xqd = xqd;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
