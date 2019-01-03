<template>
  <el-row type="flex" justify="center">
    <el-col :span="12">
      <el-form
        :model="studentGradeInfo"
        label-width="100px"
        :rules="rules"
        ref="studentGradeInfo"
        action="/mock/api"
      >
        <el-form-item label="学号" prop="studentId">
          <el-input v-model="studentGradeInfo.studentId" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="课程" prop="courseCode">
          <el-select
            v-model="studentGradeInfo.courseCode"
            filterable
            remote
            reserve-keyword
            placeholder="请输入关键词查找"
            :remote-method="searchCourse"
            :loading="loading"
          >
            <el-option
              v-for="item in waitingOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学年" prop="xnd">
          <el-input v-model="studentGradeInfo.xnd"></el-input>
        </el-form-item>
        <el-form-item label="学期" prop="xqd">
          <el-select v-model="studentGradeInfo.xqd" placeholder="请选择学期">
            <el-option label="第1学期" value="1"></el-option>
            <el-option label="第2学期" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分数" prop="score">
          <el-input v-model="studentGradeInfo.score"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('studentGradeInfo')">立即添加</el-button>
          <el-button @click="resetForm('studentGradeInfo')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>
<script>
import axios from "axios";
import { postRequest } from "../utils/api";
export default {
  data() {
    return {
      loading: false,
      waitingOptions: [],
      studentGradeInfo: {
        studentId: "",
        courseCode: "",
        xnd: "",
        xqd: "",
        score: ""
      },
      rules: {}
    };
  },
  methods: {
    searchCourse(query) {
      this.loading = true;
      axios
        .get("/mock/searchCourse", {
          params: { q: query }
        })
        .then(response => (this.waitingOptions = response.data))
        .then((this.loading = false));
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //   console.log(this.studentInfo);
          //   axios.get("/mock/addStudent", {
          //     params: this.studentInfo
          //   });
          postRequest("/mock/addGrade", this.studentGradeInfo).then(
            response => {
              this.$notify({
                title: "成功",
                message: response.data,
                type: "success"
              });
            }
          );
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.studentGradeInfo.studentId = this.$route.params.id;
    }
  },
  mounted: function() {
    this.studentGradeInfo.studentId = this.$route.params.id;
  }
};
</script>
