package com.example.sims.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GradeKey implements Serializable {

    @Column(name = "student_id", length = 12)
    private String studentId;

    @Column(name = "course_code", length = 9)
    private String courseCode;

    public GradeKey() {
    }

    public GradeKey(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeKey gradeKey = (GradeKey) o;
        return Objects.equals(studentId, gradeKey.studentId) &&
                Objects.equals(courseCode, gradeKey.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseCode);
    }
}
