package com.example.sims.service;

import com.example.sims.domain.Student;
import com.example.sims.domain.Unit;
import com.example.sims.repository.GradeRepository;
import com.example.sims.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GradeRepository gradeRepository;

    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    public Page<Student> queryStudent(String dept, String speciality, String c, String q, int page_size, int page_index) {
        Pageable pageable = PageRequest.of(page_index, page_size);
        return studentRepository.findStudentsByDeptLikeAndSpecialityLikeAndTheClassLikeAndStudentNameLike(dept, speciality, c, q, pageable);
    }

    public List<Unit> getUnits() {
        List<Unit> units = new ArrayList<>();
        List<String> depts = studentRepository.getDepts();
        for (String dept : depts) {
            Unit deptUnit = new Unit(dept, dept);
            List<String> specialitys = studentRepository.getSpecialitysByDept(dept);
            for (String speciality : specialitys) {
                Unit specUnit = new Unit(speciality, speciality);
                List<String> classes = studentRepository.getClassesBySpeciality(speciality);
                for (String c : classes) {
                    Unit cUnit = new Unit(c, c);
                    specUnit.getChildren().add(cUnit);
                }
                deptUnit.getChildren().add(specUnit);
            }
            units.add(deptUnit);
        }
        return units;
    }

    public Student getStudent(String id) {
        Student student = studentRepository.findStudentByStudentId(id);
        return student;
    }

    public boolean addStudent(Student student) {
        try {
            studentRepository.save(student);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteStudentById(String id) {
        try {
//            gradeRepository.deleteStudent(id);
            int i = studentRepository.deleteStudentByStudentId(id);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
