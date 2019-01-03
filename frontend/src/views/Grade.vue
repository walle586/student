<template>
  <div>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="studentId" label="学号"></el-table-column>
      <el-table-column prop="courseCode" label="课程代码"></el-table-column>
      <el-table-column prop="studentName" label="学生姓名"></el-table-column>
      <el-table-column prop="courseName" label="课程名称"></el-table-column>
      <el-table-column prop="xnq" label="学年"></el-table-column>
      <el-table-column prop="xqd" label="学期"></el-table-column>
      <el-table-column prop="score" label="成绩"></el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button
            type="danger"
            icon="el-icon-delete"
            circle
            size="mini"
            @click="handleDelete(scope.$index, scope.row)"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return { tableData: null };
  },
  methods: {},
  mounted: function() {
      axios
      .get("/mock/getAllGrade")
      .then(response => (this.tableData = response.data));
  }
};
</script>
