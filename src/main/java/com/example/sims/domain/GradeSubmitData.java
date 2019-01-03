package com.example.sims.domain;

public class GradeSubmitData {
    private String studentId;
    private String courseCode;
    private String xnd;
    private String xqd;
    private Double score;

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

    public String getXnd() {
        return xnd;
    }

    public void setXnd(String xnd) {
        this.xnd = xnd;
    }

    public String getXqd() {
        return xqd;
    }

    public void setXqd(String xqd) {
        this.xqd = xqd;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "GradeSubmitData{" +
                "studentId='" + studentId + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", xnd='" + xnd + '\'' +
                ", xqd='" + xqd + '\'' +
                ", score=" + score +
                '}';
    }
}
