<template>
  <div>
    <el-row>
      <el-col :span="18">
        <el-row :gutter="18">
          <el-col :span="6">
            <el-cascader
              clearable
              change-on-select
              expand-trigger="hover"
              :options="options"
              v-model="selectedOptions2"
              @change="handleChange"
            ></el-cascader>
          </el-col>
          <el-col :span="8">
            <el-input clearable placeholder="输入学生姓名" prefix-icon="el-icon-search" v-model="input21"></el-input>
          </el-col>
          <el-col :span="8">
            <el-button round @click="do_search">搜索学生</el-button>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="6">
        <el-row type="flex" justify="end">
          <el-col :span="18">
            <el-button round type="primary" @click="to_student_add">添加学生</el-button>
            <el-button round type="danger">批量删除</el-button>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-table
      :data="tableData"
      stripe
      style="width: 100%"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="studentId" label="学号" width="180"></el-table-column>
      <el-table-column prop="studentName" label="姓名"></el-table-column>
      <el-table-column prop="sex" label="性别"></el-table-column>
      <el-table-column prop="speciality" label="专业" width="200"></el-table-column>
      <el-table-column prop="theClass" label="班级"></el-table-column>
      <el-table-column prop="dept" label="所在系"></el-table-column>
      <el-table-column fixed="right" label="操作" width="200">
        <template slot-scope="scope">
          <el-button
            type="success"
            icon="el-icon-plus"
            circle
            size="mini"
            @click="handleGradeAdd(scope.$index, scope.row)"
          ></el-button>
          <el-button
            type="primary"
            icon="el-icon-edit"
            circle
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
          ></el-button>
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
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[2,100, 200, 300, 400]"
        :page-size="page_size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="page_total"
      ></el-pagination>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      options: [],
      selectedOptions2: [],
      tableData: [],
      input21: null,
      currentPage: 1,
      page_size: 100,
      page_total: 0
    };
  },
  methods: {
    handleSelectionChange(val) {},
    to_student_add() {
      this.$router.push("student_add");
    },
    handleGradeAdd(index, row) {
      this.$router.push({
        name: "studentGradeAdd",
        params: { id: row.studentId }
      });
    },
    handleEdit(index, row) {
      this.$router.push({
        name: "studentUpdate",
        params: { id: row.studentId }
      });
      console.log(index, row);
    },
    handleDelete(index, row) {
      axios
        .get("/mock/deleteStudent", {
          params: { id: row.studentId }
        })
        .then(response => {
          this.$notify({
            title: "成功",
            message: response.data,
            type: "success"
          });
        })
        .then(this.tableData.splice(index, 1));
      console.log(row.studentId);
      console.log(index, row);
    },
    handleSizeChange(val) {
      this.page_size = val;
      this.do_search();
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.do_search();
      console.log(val);
    },
    do_search() {
      axios
        .get("/mock/getAllStudent", {
          params: {
            page_size: this.page_size,
            page_index: this.currentPage,
            dept: this.selectedOptions2[0],
            speciality: this.selectedOptions2[1],
            c: this.selectedOptions2[2],
            q: this.input21
          }
        })
        .then(response => {
          this.tableData = response.data.table;
          this.page_size = response.data.size;
          this.page_total = response.data.total;
        })
        .then((this.input21 = ""));
    },
    handleChange(value) {}
  },
  mounted: function() {
    axios
      .get("/mock/getUnits")
      .then(response => (this.options = response.data));
    this.do_search();
  }
};
</script>
<style scoped>
</style>
