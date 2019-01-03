package com.example.sims.domain;

import javax.persistence.*;

@Entity
public class Grade {

    @EmbeddedId
    private GradeKey id;

    @MapsId("student_id")
    @ManyToOne
    @JoinColumn(name = "student_id", columnDefinition = "varchar(12)")
    private Student student;

    @MapsId("course_code")
    @ManyToOne
    @JoinColumn(name = "course_code", columnDefinition = "varchar(9)")
    private Course course;

    private Double score;

    private String xnd;

    private String xqd;

    public GradeKey getId() {
        return id;
    }

    public void setId(GradeKey id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
