package com.example.sims.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {@Index(columnList = "studentId")})
public class Student {

    @Id
    @Column(length = 12)
    private String studentId;

    private String studentName;

    private String sex;

    private String speciality;

    private String dept;

    @Column(name = "class")
    private String theClass;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    @JSONField(serialize = false)
    private List<Grade> gradeList = new ArrayList<>();

    public Student() {
    }

    public Student(String studentId, String studentName, String sex, String speciality, String dept, String theClass) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.sex = sex;
        this.speciality = speciality;
        this.dept = dept;
        this.theClass = theClass;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getTheClass() {
        return theClass;
    }

    public void setTheClass(String theClass) {
        this.theClass = theClass;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

}
