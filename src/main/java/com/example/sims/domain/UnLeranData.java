package com.example.sims.domain;

public class UnLeranData {

    private String courseCode;
    private String courseName;
    private String courseType;

    public UnLeranData(String courseCode, String courseName, String courseType) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseType = courseType;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
}
