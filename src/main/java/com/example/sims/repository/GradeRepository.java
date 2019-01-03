package com.example.sims.repository;

import com.example.sims.domain.Grade;
import com.example.sims.domain.GradeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, GradeKey> {

    @Query(value = "SELECT c.student_id,c.course_code,student_name,course_name,xnd,xqd,score FROM student a,course b,grade c WHERE a.student_id=c.student_id AND b.course_code=c.course_code", nativeQuery = true)
    List<?> getGrade();

    @Query(value = "SELECT AVG(score) FROM student a,course b,grade c WHERE a.student_id=c.student_id AND b.course_code=c.course_code AND a.speciality=?1", nativeQuery = true)
    Float getAvgGradeBySpec(String spec);

    @Query(value = "SELECT MAX(score) FROM student a,course b,grade c WHERE a.student_id=c.student_id AND b.course_code=c.course_code AND a.speciality=?1", nativeQuery = true)
    String getMaxGradeBySpec(String spec);

    @Query(value = "SELECT MIN(score) FROM student a,course b,grade c WHERE a.student_id=c.student_id AND b.course_code=c.course_code AND a.speciality=?1", nativeQuery = true)
    String getMinGradeBySpec(String spec);

    @Query(value = "SELECT AVG(score) FROM student a,course b,grade c WHERE a.student_id=c.student_id AND b.course_code=c.course_code AND b.course_code=?1", nativeQuery = true)
    Float getAvgGradeByCourseCode(String course);

    @Query(value = "SELECT MAX(score) FROM student a,course b,grade c WHERE a.student_id=c.student_id AND b.course_code=c.course_code AND b.course_code=?1", nativeQuery = true)
    String getMaxGradeByCourseCode(String course);

    @Query(value = "SELECT MIN(score) FROM student a,course b,grade c WHERE a.student_id=c.student_id AND b.course_code=c.course_code AND b.course_code=?1", nativeQuery = true)
    String getMinGradeByCourseCode(String course);

    @Query(value = "SELECT DISTINCT speciality FROM student a,course b,grade c WHERE a.student_id=c.student_id AND b.course_code=c.course_code", nativeQuery = true)
    List<?> getGradeSpecs();

    @Query(value = "SELECT DISTINCT c.course_code,course_name FROM student a,course b,grade c WHERE a.student_id=c.student_id AND b.course_code=c.course_code", nativeQuery = true)
    List<?> getGradeCourses();

    @Query(value = "DELETE FROM grade WHERE student_id=?1", nativeQuery = true)
    int deleteStudent(String id);
}
