package com.example.sims.web;

import com.alibaba.fastjson.JSON;
import com.example.sims.domain.ResponseData;
import com.example.sims.domain.Student;
import com.example.sims.service.StudentService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(path = "getAllStudent")
    @ResponseBody
    public String getAllStudent(@RequestParam(defaultValue = "1") int page_index,
                                @RequestParam(defaultValue = "15") int page_size,
                                @RequestParam(defaultValue = "%") String dept,
                                @RequestParam(defaultValue = "%") String speciality,
                                @RequestParam(defaultValue = "%") String c,
                                @RequestParam(defaultValue = "%") String q) {
        Page<Student> studentsPage = studentService.queryStudent(dept, speciality, c, "%" + q + "%", page_size, page_index - 1);
        ResponseData<List<Student>> responseData = new ResponseData<>();
        responseData.setPages(studentsPage.getTotalPages());
        responseData.setTotal(studentsPage.getTotalElements());
        responseData.setSize(studentsPage.getSize());
        responseData.setTable(studentsPage.getContent());
        return JSON.toJSONString(responseData);
    }

    @RequestMapping(path = "getStudent")
    @ResponseBody
    public String getStudent(String id) {
        Student student = studentService.getStudent(id);
        return JSON.toJSONString(student);
    }

    @PostMapping(path = "addStudent")
    @ResponseBody
    public String addStudent(Student student) {
        if (studentService.addStudent(student)) {
            return JSON.toJSONString("添加成功");
        } else {
            return JSON.toJSONString("添加失败");
        }
    }

    @RequestMapping(path = "updateStudent")
    @ResponseBody
    public String updateStudent(Student student) {
        if (studentService.addStudent(student)) {
            return JSON.toJSONString("保存成功");
        } else {
            return JSON.toJSONString("保存失败");
        }
    }

    @RequestMapping(path = "deleteStudent")
    @ResponseBody
    public String addStudent(String id) {
        if (studentService.deleteStudentById(id)) {
            return JSON.toJSONString("删除成功");
        } else {
            return JSON.toJSONString("删除失败");
        }
    }

    @RequestMapping(path = "getUnits")
    @ResponseBody
    public String getUnits() {
        Gson gson = new Gson();
        return gson.toJson(studentService.getUnits()).replace(",\"children\":[]", "");
    }
/*
    @RequestMapping(path = "getSpecialitysByDept")
    @ResponseBody
    public String getSpecialitysByDept(@RequestParam(name = "dept") String dept) {
        return gson.toJson(studentService.getSpecialitysByDept(dept));
    }

    @RequestMapping(path = "getClassBySpeciality")
    @ResponseBody
    public String getClassBySpeciality(String speciality) {
        return gson.toJson(studentService.getClassBySpeciality(speciality));
    }*/
}
