package com.example.sims.service;

import com.example.sims.domain.Grade;
import com.example.sims.domain.GradeKey;
import com.example.sims.domain.GradeSubmitData;
import com.example.sims.domain.StatisticsData;
import com.example.sims.repository.CourseRepository;
import com.example.sims.repository.GradeRepository;
import com.example.sims.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.List;

@Service
@Transactional
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public boolean addGrade(GradeSubmitData data) {
        GradeKey gradeKey = new GradeKey(data.getStudentId(), data.getCourseCode());
        Grade grade = new Grade();
        grade.setId(gradeKey);
        grade.setXnd(data.getXnd());
        grade.setXqd(data.getXqd());
        grade.setScore(data.getScore());
        try {
            gradeRepository.save(grade);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<?> getAllGrade() {
        return gradeRepository.getGrade();
    }

    public List<?> getGradeSpecs() {
        return gradeRepository.getGradeSpecs();
    }

    public List<?> getGradeCourses() {
        return gradeRepository.getGradeCourses();
    }

    public StatisticsData getGradeStatisticsDataBySpec(String spec) {
        Float avg = gradeRepository.getAvgGradeBySpec(spec);
        String max = gradeRepository.getMaxGradeBySpec(spec);
        String min = gradeRepository.getMinGradeBySpec(spec);
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        StatisticsData data = new StatisticsData(numberFormat.format(avg), max, min, null);
        return data;
    }

    public StatisticsData getGradeStatisticsDataByCourse(String courseCode) {
        Float avg = gradeRepository.getAvgGradeByCourseCode(courseCode);
        String max = gradeRepository.getMaxGradeByCourseCode(courseCode);
        String min = gradeRepository.getMinGradeByCourseCode(courseCode);
        Float failCount = (float) studentRepository.getFailCountByCourseCode(courseCode);
        Float totalCount = (float) studentRepository.getTotalCountByCourseCode(courseCode);
        Float rate = (totalCount - failCount) / totalCount;
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(2);
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        return new StatisticsData(numberFormat.format(avg), max, min, percentFormat.format(rate));
    }

}
