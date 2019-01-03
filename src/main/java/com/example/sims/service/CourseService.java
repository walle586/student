package com.example.sims.service;

import com.example.sims.domain.Course;
import com.example.sims.domain.Type;
import com.example.sims.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CourseService {
    @Resource
    private CourseRepository courseRepository;

    public List<Course> findAllCourse() {
        return courseRepository.findAll();
    }

    public boolean addCourse(Course course) {
        try {
            courseRepository.save(course);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Course> searchCourse(String q) {
        return courseRepository.findCoursesByCourseNameLike(q);
    }

    public Course getCourseByCode(String code) {
        return courseRepository.findCourseByCourseCode(code);
    }

    public List<Type> getTypes() {
        List<Type> types = new ArrayList<>();
        List<String> strings = courseRepository.getTypes();
        for (String s : strings) {
            types.add(new Type(s, s));
        }
        return types;
    }

    public boolean deleteCourseByCode(String code) {
        int i = courseRepository.deleteCourseByCourseCode(code);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Page<Course> queryCourse(String type, String q, int page_size, int page_index) {
        Pageable pageable = PageRequest.of(page_index, page_size);
        return courseRepository.findCoursesByCourseTypeLikeAndCourseNameLike(type, q, pageable);
    }
}
