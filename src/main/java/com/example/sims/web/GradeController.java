package com.example.sims.web;

import com.alibaba.fastjson.JSON;
import com.example.sims.domain.GradeInfo;
import com.example.sims.domain.GradeSubmitData;
import com.example.sims.domain.StatisticsData;
import com.example.sims.domain.Type;
import com.example.sims.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping(path = "addGrade")
    @ResponseBody
    public String addGrade(GradeSubmitData grade) {
        if (gradeService.addGrade(grade)) {

            return JSON.toJSONString("添加成功");
        } else {
            return JSON.toJSONString("添加失败");
        }
    }

    @RequestMapping(path = "getAllGrade")
    @ResponseBody
    public String getAllGrade() {
        List<GradeInfo> gradeInfos = new ArrayList<>();
        List<?> list = gradeService.getAllGrade();
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object[] o = (Object[]) iterator.next();
            GradeInfo info = new GradeInfo(o[0].toString(), o[1].toString(), o[2].toString(), o[3].toString(), o[4].toString(), o[5].toString(), o[6].toString());
            gradeInfos.add(info);
        }
        return JSON.toJSONString(gradeInfos);
    }

    @RequestMapping(path = "getGradeSpecs")
    @ResponseBody
    public String getGradeSpecs() {
        List<Type> types = new ArrayList<>();
        List<?> gradeSpecs = gradeService.getGradeSpecs();
        Iterator<?> iterator = gradeSpecs.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            Type type = new Type(next.toString(), next.toString());
            types.add(type);
        }
        return JSON.toJSONString(types);
    }

    @RequestMapping(path = "getGradeCourses")
    @ResponseBody
    public String getGradeCourses() {
        List<Type> types = new ArrayList<>();
        List<?> gradeSpecs = gradeService.getGradeCourses();
        Iterator<?> iterator = gradeSpecs.iterator();
        while (iterator.hasNext()) {
            Object[] o = (Object[]) iterator.next();
            Type type = new Type(o[0].toString(), o[1].toString());
            types.add(type);
        }
        return JSON.toJSONString(types);
    }

    @RequestMapping(path = "getGradeStatisticsDataBySpec")
    @ResponseBody
    public String getGradeStatisticsDataBySpec(String spec) {
        List<StatisticsData> list = new ArrayList<>();
        list.add(gradeService.getGradeStatisticsDataBySpec(spec));
        return JSON.toJSONString(list);
    }

    @RequestMapping(path = "getGradeStatisticsDataByCourse")
    @ResponseBody
    public String getGradeStatisticsDataByCourse(String course) {
        List<StatisticsData> list = new ArrayList<>();
        list.add(gradeService.getGradeStatisticsDataByCourse(course));
        return JSON.toJSONString(list);
    }
}
