package com.example.sims.repository;

import com.example.sims.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {

    @Query(value = "SELECT DISTINCT course_type FROM course", nativeQuery = true)
    List<String> getTypes();

    int deleteCourseByCourseCode(String code);

    Course findCourseByCourseCode(String code);

    Page<Course> findCoursesByCourseTypeLikeAndCourseNameLike(String type, String name, Pageable pageable);

    List<Course> findCoursesByCourseNameLike(String q);
}
