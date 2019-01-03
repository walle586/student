package com.example.sims;

import com.example.sims.domain.*;
import com.example.sims.repository.AdministratorRepository;
import com.example.sims.repository.CourseRepository;
import com.example.sims.repository.GradeRepository;
import com.example.sims.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SIMSApplicationTests {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Test
    public void contextLoads() {
        Student student = new Student("201612211408", "朱高宇", "男", "软件工程", "计算机学院", "1644");
        studentRepository.save(student);
/*
        Course course = new Course("112110015", "计算机基础", "公共基础课", 2.0);
        courseRepository.save(course);

        GradeKey gradeKey = new GradeKey(student.getStudentId(), course.getCourseCode());
        Grade grade = new Grade();
        grade.setId(gradeKey);
        grade.setScore(89.0);
        grade.setXnd("2016-2017");
        grade.setXqd("1");
        gradeRepository.save(grade);**/
    }

    @Test
    public void test0() {
        Administrator administrator = new Administrator("root", "root");
        administratorRepository.save(administrator);
    }

    @Test
    public void test1() {
        List<String> depts = studentRepository.getDepts();
        for (String dept : depts) {
            System.out.println(dept);
            List<String> specialitys = studentRepository.getSpecialitysByDept(dept);
            for (String speciality : specialitys) {
                System.out.println(speciality);
                List<String> classes = studentRepository.getClassesBySpeciality(speciality);
                for (String c : classes) {
                    System.out.println(c);
                }
            }
        }
    }

    @Test
    public void test2() {
        GradeKey key = new GradeKey("201612211408", "211");
        Grade grade = new Grade();
        grade.setId(key);
        grade.setScore(80.0);
        grade.setXqd("2");
        grade.setXnd("2018");
        gradeRepository.save(grade);
    }

    @Test
    public void test3() {
        System.out.println(gradeRepository.getAvgGradeBySpec("软件工程"));
        System.out.println(gradeRepository.getMaxGradeBySpec("软件工程"));
        System.out.println(gradeRepository.getMinGradeBySpec("软件工程"));
        System.out.println(gradeRepository.getGradeSpecs());
    }

    @Test
    @Transactional
    public void test4() {
        Student student = studentRepository.findStudentByStudentId("201612211408");
        List<Grade> gradeList = student.getGradeList();
        for (Grade grade : gradeList) {
            System.out.println(grade.getCourse().getCourseName());
        }
    }

    @Test
    public void test5() {
        Student student = studentRepository.findStudentByStudentId("201612211408");
        studentRepository.delete(student);
    }

    @Test
    public void test6() {
        Course course = courseRepository.findCourseByCourseCode("112110015");
        courseRepository.delete(course);
    }

}

