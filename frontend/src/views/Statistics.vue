<template>
  <div>
    <el-row :gutter="18">
      <el-col :span="8">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <el-row :gutter="20">
              <el-col :span="12">
                <el-select v-model="specType" placeholder="请选择专业" clearable>
                  <el-option
                    v-for="item in specOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-col>
              <el-col :span="8">
                <el-button round @click="queryGradeStatisticsDataBySpec">按专业查</el-button>
              </el-col>
            </el-row>
          </div>
          <el-table :data="specData" style="width: 100%">
            <el-table-column prop="avg" label="平均分"></el-table-column>
            <el-table-column prop="max" label="最高分"></el-table-column>
            <el-table-column prop="min" label="最低分"></el-table-column>
            <el-table-column prop="rate" label="及格率"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <el-row :gutter="20">
              <el-col :span="10">
                <el-select v-model="courseType" placeholder="请选择课程" clearable>
                  <el-option
                    v-for="item in courseOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
              </el-col>
              <el-col :span="10">
                <el-button round @click="queryGradeStatisticsDataByCourse">按课程查</el-button>
              </el-col>
            </el-row>
          </div>
          <el-table :data="courseData" style="width: 100%">
            <el-table-column prop="avg" label="平均分"></el-table-column>
            <el-table-column prop="max" label="最高分"></el-table-column>
            <el-table-column prop="min" label="最低分"></el-table-column>
            <el-table-column prop="rate" label="及格率"></el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <!-- <el-col :span="8">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <el-row :gutter="20">
              <el-col :span="10">
                <el-input placeholder="请输入学生学号"></el-input>
              </el-col>
              <el-col :span="10">
                <el-button round @click="queryunlearnDataByStudentId">未选修查询</el-button>
              </el-col>
            </el-row>
          </div>
          <el-table :data="unlearnData" style="width: 100%">
            <el-table-column prop="courseCode" label="课程号"></el-table-column>
            <el-table-column prop="courseName" label="课程名"></el-table-column>
            <el-table-column prop="courseType" label="课程类型"></el-table-column>
          </el-table>
        </el-card>
      </el-col> -->
    </el-row>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      specType: "",
      courseType: "",
      studentId: "",
      specOptions: [],
      courseOptions: [],
      courseData: [],
      specData: [],
      unlearnData: []
    };
  },
  methods: {
    queryGradeStatisticsDataByCourse() {
      axios
        .get("/mock/getGradeStatisticsDataByCourse", {
          params: { course: this.courseType }
        })
        .then(response => (this.courseData = response.data));
    },
    queryGradeStatisticsDataBySpec() {
      axios
        .get("/mock/getGradeStatisticsDataBySpec", {
          params: { spec: this.specType }
        })
        .then(response => (this.specData = response.data));
    },
    queryunlearnDataByStudentId(){
      axios
        .get("/mock/getGradeStatisticsDataBySpec", {
          params: { id: this.studentId }
        })
        .then(response => (this.unlearnData = response.data));
    }
  },
  mounted: function() {
    axios
      .get("/mock/getGradeSpecs")
      .then(response => (this.specOptions = response.data));
    axios
      .get("/mock/getGradeCourses")
      .then(response => (this.courseOptions = response.data));
  }
};
</script>
<style>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 480px;
}
</style>
