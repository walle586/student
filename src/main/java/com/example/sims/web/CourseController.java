package com.example.sims.web;

import com.alibaba.fastjson.JSON;
import com.example.sims.domain.Course;
import com.example.sims.domain.ResponseData;
import com.example.sims.domain.Type;
import com.example.sims.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {

    @Resource
    private CourseService courseService;

    @RequestMapping(path = "getAllCourse")
    @ResponseBody
    public String getAllCourse(@RequestParam(defaultValue = "1") int page_index,
                               @RequestParam(defaultValue = "15") int page_size,
                               @RequestParam(defaultValue = "%") String q,
                               @RequestParam(defaultValue = "%") String type) {
        Page<Course> coursePage = courseService.queryCourse(type, "%" + q + "%", page_size, page_index - 1);
        ResponseData<List<Course>> responseData = new ResponseData<>();
        responseData.setPages(coursePage.getTotalPages());
        responseData.setTotal(coursePage.getTotalElements());
        responseData.setSize(coursePage.getSize());
        responseData.setTable(coursePage.getContent());
        return JSON.toJSONString(responseData);
    }

    @RequestMapping(path = "searchCourse")
    @ResponseBody
    public String searchCourse(String q) {
        List<Type> types = new ArrayList<>();
        List<Course> courses = courseService.searchCourse("%" + q + "%");
        for (Course course : courses) {
            Type type = new Type(course.getCourseCode(), course.getCourseName());
            types.add(type);
        }
        return JSON.toJSONString(types);
    }

    @PostMapping(path = "addCourse")
    @ResponseBody
    public String addCourse(Course course) {
        if (courseService.addCourse(course)) {
            return JSON.toJSONString("添加成功");
        } else {
            return JSON.toJSONString("添加失败");
        }
    }

    @RequestMapping(path = "getCourse")
    @ResponseBody
    public String getCourse(String code) {
        Course course = courseService.getCourseByCode(code);
        return JSON.toJSONString(course);
    }

    @RequestMapping(path = "updateCourse")
    @ResponseBody
    public String updateCourse(Course course) {
        if (courseService.addCourse(course)) {
            return JSON.toJSONString("保存成功");
        } else {
            return JSON.toJSONString("保存失败");
        }
    }

    @RequestMapping(path = "deleteCourse")
    @ResponseBody
    public String deleteCourse(String code) {
        if (courseService.deleteCourseByCode(code)) {
            return JSON.toJSONString("删除成功");
        } else {
            return JSON.toJSONString("删除失败");
        }
    }

    @RequestMapping(path = "getTypes")
    @ResponseBody
    public String getTypes() {
        List<Type> types = courseService.getTypes();
        return JSON.toJSONString(types);
    }

}
