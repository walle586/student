package com.example.sims.repository;

import com.example.sims.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    @Query(value = "SELECT DISTINCT dept FROM student", nativeQuery = true)
    List<String> getDepts();

    @Query(value = "SELECT DISTINCT speciality FROM student WHERE dept = ?1", nativeQuery = true)
    List<String> getSpecialitysByDept(String dept);

    @Query(value = "SELECT DISTINCT class FROM student WHERE speciality = ?1", nativeQuery = true)
    List<String> getClassesBySpeciality(String speciality);

    @Query(value = "SELECT COUNT(DISTINCT student_id) FROM grade WHERE score < 60 AND course_code=?1", nativeQuery = true)
    int getFailCountByCourseCode(String courseCode);

    @Query(value = "SELECT COUNT(DISTINCT student_id) FROM grade WHERE course_code=?1", nativeQuery = true)
    int getTotalCountByCourseCode(String courseCode);

    Page<Student> findStudentsByDeptLikeAndSpecialityLikeAndTheClassLikeAndStudentNameLike(String dept, String speciality, String theClass, String name, Pageable pageable);

    List<Student> findStudentsByDeptLikeAndSpecialityLikeAndTheClassLikeAndStudentNameLike(String dept, String speciality, String theClass, String name);

    int deleteStudentByStudentId(String studentId);

    Student findStudentByStudentId(String id);
}
