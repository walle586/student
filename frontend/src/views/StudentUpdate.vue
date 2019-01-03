<template>
  <el-row type="flex" justify="center">
    <el-col :span="12">
      <el-form
        :model="studentInfo"
        label-width="100px"
        :rules="rules"
        ref="studentInfo"
        action="/mock/api"
      >
      <el-form-item label="学号" prop="studentId">
          <el-input v-model="studentInfo.studentId" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="studentName">
          <el-input v-model="studentInfo.studentName"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="studentInfo.sex">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="学院" prop="dept">
          <el-input v-model="studentInfo.dept"></el-input>
        </el-form-item>
        <el-form-item label="专业" prop="speciality">
          <el-input v-model="studentInfo.speciality"></el-input>
        </el-form-item>
        <el-form-item label="班级" prop="theClass">
          <el-input v-model="studentInfo.theClass"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('studentInfo')">保存信息</el-button>
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
      studentInfo: {
        studentName: "",
        studentId: "",
        sex: "",
        dept: "",
        speciality: "",
        theClass: ""
      },
      rules: {
        studentName: [
          { required: true, message: "请输入学生姓名", trigger: "blur" },
          { min: 2, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" }
        ],
        studentId: [
          { required: true, message: "请输入学号", trigger: "blur" },
          { min: 12, max: 12, message: "请输入12位学号", trigger: "blur" }
        ],
        sex: [{ required: true }],
        dept: [{ required: true, message: "请输入学院名称", trigger: "blur" }],
        speciality: [
          { required: true, message: "请输入专业名称", trigger: "blur" }
        ],
        theClass: [{ required: true, message: "请输入班级" }]
      }
    };
  },
  methods: {
    open3() {
      this.$notify({
        title: "成功",
        message: "这是一条成功的提示消息",
        type: "success"
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //   console.log(this.studentInfo);
          //   axios.get("/mock/addStudent", {
          //     params: this.studentInfo
          //   });
          postRequest("/mock/updateStudent", this.studentInfo).then(response => {
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
      .get("/mock/getStudent", { params: { id: this.$route.params.id } })
      .then(response => (this.studentInfo = response.data));
  }
};
</script>
