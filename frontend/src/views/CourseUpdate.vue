<template>
  <el-row type="flex" justify="center">
    <el-col :span="12">
      <el-form
        :model="courseInfo"
        label-width="100px"
        :rules="rules"
        ref="courseInfo"
        action="/mock/api"
      >
        <el-form-item label="课程号" prop="courseCode">
          <el-input v-model="courseInfo.courseCode"></el-input>
        </el-form-item>
        <el-form-item label="课程名" prop="courseName">
          <el-input v-model="courseInfo.courseName"></el-input>
        </el-form-item>
        <el-form-item label="课程类型" prop="courseType">
          <el-select v-model="courseInfo.courseType" placeholder="请选择课程类型">
            <el-option label="专业必修课" value="专业必修课"></el-option>
            <el-option label="公共基础课" value="公共基础课"></el-option>
            <el-option label="公共选修课" value="公共选修课"></el-option>
            <el-option label="体育项目课" value="体育项目课"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学分" prop="credit">
          <el-input v-model="courseInfo.credit"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('courseInfo')">立即保存</el-button>
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
      courseInfo: {
        courseCode: "",
        courseName: "",
        courseType: "",
        credit: 0
      },
      rules: {
        courseCode: [
          { required: true, message: "请输入课程号", trigger: "blur" },
          { min: 9, max: 9, message: "长度在 3 到 5 个字符", trigger: "blur" }
        ],
        courseName: [
          { required: true, message: "请输入课程名", trigger: "blur" }
        ],
        courseType: [{ required: true }],
        credit: [{ required: true, message: "请输入课程学分", trigger: "blur" }]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //   console.log(this.studentInfo);
          //   axios.get("/mock/addStudent", {
          //     params: this.studentInfo
          //   });
          postRequest("/mock/updateCourse", this.courseInfo).then(response => {
            this.$notify({
              title: "成功",
              message: response.data,
              type: "success"
            });
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    }
  },
  mounted: function() {
    axios
      .get("/mock/getCourse", { params: { code: this.$route.params.code } })
      .then(response => (this.courseInfo = response.data));
  }
};
</script>
